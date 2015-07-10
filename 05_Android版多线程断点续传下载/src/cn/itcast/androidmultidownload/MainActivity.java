package cn.itcast.androidmultidownload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	int threadCount = 3;
	int finishedThreadCount = 0;
	//记录进度条的当前进度
	int currentProgress = 0;
	String path = "http://172.17.63.1/suprGao.mp3";
	private ProgressBar pb;
	private TextView tv;
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			//当前进度除以最大进度，得到下载进度的百分比
			tv.setText((long)pb.getProgress() * 100 / pb.getMax() + "%");
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pb = (ProgressBar) findViewById(R.id.pb);
		tv = (TextView) findViewById(R.id.tv);
	}

	public void click(View v){
		Thread t = new Thread(){
			@Override
			public void run() {
				try {
					URL url = new URL(path);
					//打开连接对象，做初始化设置
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(8000);
					conn.setReadTimeout(8000);
					
					if(conn.getResponseCode() == 200){
						//获取要下载的目标文件的总长度
						int length = conn.getContentLength();
						
						//设定进度条的最大值
						pb.setMax(length);
						
						//计算每条线程要下载的长度
						int size = length / threadCount;
						System.out.println("size:" + size);
						
						//创建一个与目标文件大小一致的临时文件
						File file = new File(Environment.getExternalStorageDirectory(), getFileNameFromPath(path));
						RandomAccessFile raf = new RandomAccessFile(file, "rwd");
						//设置临时文件的大小
						raf.setLength(length);
						
						//计算每条线程下载的开始位置和结束位置
						for(int threadId = 0; threadId < threadCount; threadId++){
							int startIndex = threadId * size;
							int endIndex = (threadId + 1) * size - 1;
							//如果是最后一条线程，那么需要把余数也一块下载
							if(threadId == threadCount - 1){
								endIndex = length - 1;
							}
//							System.out.println("线程" + threadId + "下载区间为：" + startIndex + "~" + endIndex);
							DownLoadThread dt = new DownLoadThread(threadId, startIndex, endIndex);
							dt.start();
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t.start();
	}
	
	public String getFileNameFromPath(String path){
		int index = path.lastIndexOf("/");
		return path.substring(index + 1);
	}
	
	class DownLoadThread extends Thread{
		int threadId;
		int startIndex;
		int endIndex;
		
		public DownLoadThread(int threadId, int startIndex, int endIndex) {
			super();
			this.threadId = threadId;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		@Override
		public void run() {
			try {
				int lastProgress = 0;
				//在下载之前，先判断进度临时文件是否存在
				File fileProgress = new File(Environment.getExternalStorageDirectory(), threadId + ".txt");
				if(fileProgress.exists()){
					FileInputStream fis = new FileInputStream(fileProgress);
					BufferedReader br = new BufferedReader(new InputStreamReader(fis));
					//读取进度临时文件中的值
					lastProgress = Integer.parseInt(br.readLine());
					//把上一次下载的进度加到下载开始位置
					startIndex += lastProgress;
					
					//如果开始位置大于或等于endIndex，说明上一次下载中，此线程就已经下载完了
					if(startIndex >= endIndex){
						finishedThreadCount++;
					}
					
					//如果上一次下载过,把上次下载的进度加到当前进度中
					currentProgress += lastProgress;
					pb.setProgress(currentProgress);
					
					//发送消息,让主线程刷新文本进度
					handler.sendEmptyMessage(1);
					fis.close();
				}
				
				System.out.println("线程" + threadId + "下载区间为：" + startIndex + "~" + endIndex);
				
				URL url = new URL(path);
				//打开连接对象，做初始化设置
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(8000);
				conn.setReadTimeout(8000);
				//指定请求数据的区间，要请求的不是所有数据
				conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);
				
				if(conn.getResponseCode() == 206){
					InputStream is = conn.getInputStream();
					
					//打开临时文件的io流
					File file = new File(Environment.getExternalStorageDirectory(), getFileNameFromPath(path));
					RandomAccessFile raf = new RandomAccessFile(file, "rwd");
					//修改写入临时文件的开始位置
					raf.seek(startIndex);
					
					byte[] b = new byte[1024];
					int len = 0;
					//当前线程下载的总进度
					int total = lastProgress;
					while((len = is.read(b)) != -1){
						//把读取到的字节写入临时文件中
						raf.write(b, 0, len);
						//把每次while循环下载的字节数加到总进度中
						total += len;
						System.out.println("线程" + threadId + "下载的进度为：" + total);
						
						//创建一个进度临时文件，保存下载进度
						RandomAccessFile rafProgress = new RandomAccessFile(fileProgress, "rwd");
						rafProgress.write((total + "").getBytes());
						rafProgress.close();
						
						currentProgress += len;
						//设置进度条当前进度
						pb.setProgress(currentProgress);
						
						//发送消息,让主线程刷新文本进度
						handler.sendEmptyMessage(1);
					}
					raf.close();
					System.out.println("线程" + threadId + "下载完毕---------------------");
					
					//在所有线程都下载完毕后，一起删除所有进度临时文件
					finishedThreadCount++;
					
					synchronized (path) {
						if(finishedThreadCount == 3){
							//删除所有进度临时文件
							for(int i = 0; i < threadCount; i++){
								File f = new File(Environment.getExternalStorageDirectory(), i + ".txt");
								f.delete();
							}
							finishedThreadCount = 0;
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
