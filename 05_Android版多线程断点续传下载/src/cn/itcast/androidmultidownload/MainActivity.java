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
	//��¼�������ĵ�ǰ����
	int currentProgress = 0;
	String path = "http://172.17.63.1/suprGao.mp3";
	private ProgressBar pb;
	private TextView tv;
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			//��ǰ���ȳ��������ȣ��õ����ؽ��ȵİٷֱ�
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
					//�����Ӷ�������ʼ������
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(8000);
					conn.setReadTimeout(8000);
					
					if(conn.getResponseCode() == 200){
						//��ȡҪ���ص�Ŀ���ļ����ܳ���
						int length = conn.getContentLength();
						
						//�趨�����������ֵ
						pb.setMax(length);
						
						//����ÿ���߳�Ҫ���صĳ���
						int size = length / threadCount;
						System.out.println("size:" + size);
						
						//����һ����Ŀ���ļ���Сһ�µ���ʱ�ļ�
						File file = new File(Environment.getExternalStorageDirectory(), getFileNameFromPath(path));
						RandomAccessFile raf = new RandomAccessFile(file, "rwd");
						//������ʱ�ļ��Ĵ�С
						raf.setLength(length);
						
						//����ÿ���߳����صĿ�ʼλ�úͽ���λ��
						for(int threadId = 0; threadId < threadCount; threadId++){
							int startIndex = threadId * size;
							int endIndex = (threadId + 1) * size - 1;
							//��������һ���̣߳���ô��Ҫ������Ҳһ������
							if(threadId == threadCount - 1){
								endIndex = length - 1;
							}
//							System.out.println("�߳�" + threadId + "��������Ϊ��" + startIndex + "~" + endIndex);
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
				//������֮ǰ�����жϽ�����ʱ�ļ��Ƿ����
				File fileProgress = new File(Environment.getExternalStorageDirectory(), threadId + ".txt");
				if(fileProgress.exists()){
					FileInputStream fis = new FileInputStream(fileProgress);
					BufferedReader br = new BufferedReader(new InputStreamReader(fis));
					//��ȡ������ʱ�ļ��е�ֵ
					lastProgress = Integer.parseInt(br.readLine());
					//����һ�����صĽ��ȼӵ����ؿ�ʼλ��
					startIndex += lastProgress;
					
					//�����ʼλ�ô��ڻ����endIndex��˵����һ�������У����߳̾��Ѿ���������
					if(startIndex >= endIndex){
						finishedThreadCount++;
					}
					
					//�����һ�����ع�,���ϴ����صĽ��ȼӵ���ǰ������
					currentProgress += lastProgress;
					pb.setProgress(currentProgress);
					
					//������Ϣ,�����߳�ˢ���ı�����
					handler.sendEmptyMessage(1);
					fis.close();
				}
				
				System.out.println("�߳�" + threadId + "��������Ϊ��" + startIndex + "~" + endIndex);
				
				URL url = new URL(path);
				//�����Ӷ�������ʼ������
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(8000);
				conn.setReadTimeout(8000);
				//ָ���������ݵ����䣬Ҫ����Ĳ�����������
				conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);
				
				if(conn.getResponseCode() == 206){
					InputStream is = conn.getInputStream();
					
					//����ʱ�ļ���io��
					File file = new File(Environment.getExternalStorageDirectory(), getFileNameFromPath(path));
					RandomAccessFile raf = new RandomAccessFile(file, "rwd");
					//�޸�д����ʱ�ļ��Ŀ�ʼλ��
					raf.seek(startIndex);
					
					byte[] b = new byte[1024];
					int len = 0;
					//��ǰ�߳����ص��ܽ���
					int total = lastProgress;
					while((len = is.read(b)) != -1){
						//�Ѷ�ȡ�����ֽ�д����ʱ�ļ���
						raf.write(b, 0, len);
						//��ÿ��whileѭ�����ص��ֽ����ӵ��ܽ�����
						total += len;
						System.out.println("�߳�" + threadId + "���صĽ���Ϊ��" + total);
						
						//����һ��������ʱ�ļ����������ؽ���
						RandomAccessFile rafProgress = new RandomAccessFile(fileProgress, "rwd");
						rafProgress.write((total + "").getBytes());
						rafProgress.close();
						
						currentProgress += len;
						//���ý�������ǰ����
						pb.setProgress(currentProgress);
						
						//������Ϣ,�����߳�ˢ���ı�����
						handler.sendEmptyMessage(1);
					}
					raf.close();
					System.out.println("�߳�" + threadId + "�������---------------------");
					
					//�������̶߳�������Ϻ�һ��ɾ�����н�����ʱ�ļ�
					finishedThreadCount++;
					
					synchronized (path) {
						if(finishedThreadCount == 3){
							//ɾ�����н�����ʱ�ļ�
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
