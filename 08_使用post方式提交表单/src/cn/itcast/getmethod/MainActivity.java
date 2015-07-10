package cn.itcast.getmethod;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import cn.itcast.htmlviewer.tool.Tools;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			Toast.makeText(MainActivity.this, (String)msg.obj, 0).show();
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View v){
		EditText et_name = (EditText) findViewById(R.id.et_name);
		EditText et_pass = (EditText) findViewById(R.id.et_pass);
		
		final String name = et_name.getText().toString();
		final String pass = et_pass.getText().toString();
		
		final String path = "http://192.168.1.100/Web2/servlet/Login";
		
		Thread t = new Thread(){
			@Override
			public void run() {
				URL url;
				try {
					url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("POST");
					conn.setConnectTimeout(8000);
					conn.setReadTimeout(8000);
					//post请求头中需要额外添加属性
					conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					
					String content = "name=" + URLEncoder.encode(name) + "&pass=" + pass;
					conn.setRequestProperty("Content-Length", content.length() + "");
					
					//开启请求头的流，把要提交的数据写入流中
					conn.setDoOutput(true);
					OutputStream os = conn.getOutputStream();
					os.write(content.getBytes());
					
					if(conn.getResponseCode() == 200){
						InputStream is = conn.getInputStream();
						String text = Tools.getTextFromStream(is);
						
						Message msg = handler.obtainMessage();
						msg.obj = text;
						handler.sendMessage(msg);
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
