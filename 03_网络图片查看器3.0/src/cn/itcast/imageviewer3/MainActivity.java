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
		// ����Ա��Ҫ��д�˷�����ˢ��ui
		public void handleMessage(android.os.Message msg) {
			// �ж���Ϣ�ǳɹ���Ϣ����ʧ����Ϣ
			switch (msg.what) {
			case 1:
				// ��ͼƬ��ʾ����Ļ
				ImageView iv = (ImageView) findViewById(R.id.iv);
				iv.setImageBitmap((Bitmap) msg.obj);
				break;
			case 2:
				Toast.makeText(MainActivity.this, "����ʧ��������", 0).show();
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
			System.out.println("�ӻ����ȡ");
			Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());
			ImageView iv = (ImageView) findViewById(R.id.iv);
			iv.setImageBitmap(bm);
		} else {
			Thread t = new Thread() {
				public void run() {
					System.out.println("�������ȡ");
					// �����������http����ȥ����ͼƬ
					try {
						// 1.����ַ��װ��url����
						URL url = new URL(path);
						// 2.��һ�����Ӷ���
						HttpURLConnection conn = (HttpURLConnection) url
								.openConnection();
						// 3.�����Ӷ���������
						conn.setRequestMethod("GET");
						conn.setConnectTimeout(8000);
						conn.setReadTimeout(8000);
						// 4.�������󣬽�������
						conn.connect();
						// 5.��ȡ��Ӧ�룬���Ϊ200��ͷ��˵������ɹ�
						if (conn.getResponseCode() == 200) {
							// ��ȡ���������������������ص�������ͨ����д���ͻ��˵ģ�Ҳ����˵��������������ͼƬ
							InputStream is = conn.getInputStream();

							// ��Ҫ�Լ������ļ����������ȡ�������ݵ�ͬʱ��������д������
							byte[] b = new byte[1024];
							int len;

							FileOutputStream fos = new FileOutputStream(file);
							while ((len = is.read(b)) != -1) {
								fos.write(b, 0, len);
							}
							fos.close();

							// ���������Ѿ���ȡ��ϣ����д����޷��ٹ���ͼƬ��
							// Bitmap bm = BitmapFactory.decodeStream(is);

							Bitmap bm = BitmapFactory.decodeFile(file
									.getAbsolutePath());

							// ������Ϣ����
							Message msg = new Message();
							// ��Ϣ�������Я������
							msg.obj = bm;
							msg.what = 1;
							// ������Ϣ�����̵߳���Ϣ����
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
