package cn.itcast.imageviewer3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Handler handler = new Handler() {
		// 程序员需要重写此方法，刷新ui
		public void handleMessage(android.os.Message msg) {
			// 判断消息是成功消息还是失败消息
			switch (msg.what) {
			case 1:
				// 把图片显示至屏幕
				ImageView iv = (ImageView) findViewById(R.id.iv);
				iv.setImageBitmap((Bitmap) msg.obj);
				break;
			case 2:
				Toast.makeText(MainActivity.this, "请求失败啦啦啦", 0).show();
				break;
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View v) {
		final String path = "http://192.168.1.98/968.jpg";
		final File file = new File(getCacheDir(), getFileName(path));
		if (file.exists()) {
			System.out.println("从缓存读取");
			Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());
			ImageView iv = (ImageView) findViewById(R.id.iv);
			iv.setImageBitmap(bm);
		} else {
			Thread t = new Thread() {
				public void run() {
					System.out.println("从网络获取");
					// 想服务器发送http请求去请求图片
					try {
						// 1.把网址封装成url对象
						URL url = new URL(path);
						// 2.打开一个连接对象
						HttpURLConnection conn = (HttpURLConnection) url
								.openConnection();
						// 3.给连接对象做设置
						conn.setRequestMethod("GET");
						conn.setConnectTimeout(8000);
						conn.setReadTimeout(8000);
						// 4.发送请求，建立连接
						conn.connect();
						// 5.获取响应码，如果为200开头，说明请求成功
						if (conn.getResponseCode() == 200) {
							// 获取服务器的流，服务器返回的数据是通过流写给客户端的，也就是说，流里就是请求的图片
							InputStream is = conn.getInputStream();

							// 需要自己开启文件输出流，读取流里数据的同时，把数据写到本地
							byte[] b = new byte[1024];
							int len;

							FileOutputStream fos = new FileOutputStream(file);
							while ((len = is.read(b)) != -1) {
								fos.write(b, 0, len);
							}
							fos.close();

							// 流里数据已经读取完毕，这行代码无法再构造图片了
							// Bitmap bm = BitmapFactory.decodeStream(is);

							Bitmap bm = BitmapFactory.decodeFile(file
									.getAbsolutePath());

							// 创建消息对象
							Message msg = new Message();
							// 消息对象可以携带数据
							msg.obj = bm;
							msg.what = 1;
							// 发送消息至主线程的消息队列
							handler.sendMessage(msg);
						} else {
							handler.sendEmptyMessage(2);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			t.start();
		}
	}

	public String getFileName(String path) {
		int index = path.lastIndexOf("/");
		return path.substring(index + 1);
	}
}
