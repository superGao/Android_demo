package cn.itcast.getmethod;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cn.itcast.htmlviewer.tool.Tools;

public class MainActivity extends Activity {

	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			Toast.makeText(MainActivity.this, (String)msg.obj, 0).show();
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click1(View v){
		EditText et_name = (EditText) findViewById(R.id.et_name);
		EditText et_pass = (EditText) findViewById(R.id.et_pass);
		
		String name = et_name.getText().toString();
		String pass = et_pass.getText().toString();
		
		final String path = "http://192.168.1.98/Login/login?username="+name+"&password="+pass;
		
		Thread t = new Thread(){
			@Override
			public void run() {
				//1.创建客户端对象
				HttpClient client = new DefaultHttpClient();
				//2.创建http GET请求对象
				HttpGet get = new HttpGet(path);
				try {
					//3.使用客户端发送get请求
					HttpResponse response = client.execute(get);
					//获取状态行
					StatusLine line = response.getStatusLine();
					//从状态行中拿到状态码
					if(line.getStatusCode() == 200){
						//获取实体，实体里存放的是服务器返回的数据的相关信息
						HttpEntity entity = response.getEntity();
						//获取服务器返回的输入流
						InputStream is = entity.getContent();
						
						String text = Tools.getTextFromStream(is);
						
						//发送消息，让主线程刷新ui
						Message msg = handler.obtainMessage();
						msg.obj = text;
						handler.sendMessage(msg);
					}
				}  catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t.start();
	}

	public void click2(View v){
		EditText et_name = (EditText) findViewById(R.id.et_name);
		EditText et_pass = (EditText) findViewById(R.id.et_pass);
		
		final String name = et_name.getText().toString();
		final String pass = et_pass.getText().toString();
		
		final String path = "http://192.168.1.100/Web2/servlet/Login";
		
		Thread t = new Thread(){
			@Override
			public void run() {
				//创建客户端对象
				HttpClient client = new DefaultHttpClient();
				//创建post请求对像
				HttpPost post = new HttpPost(path);
				
				try {
					//通过此集合封装要提交的数据
					List<NameValuePair> parameters = new ArrayList<NameValuePair>();
					
					//集合的泛型是BasicNameValuePair类型，那么由此可以推算出，要提交的数据是封装在BasicNameValuePair对象中的
					BasicNameValuePair bvp1 = new BasicNameValuePair("name", name);
					BasicNameValuePair bvp2 = new BasicNameValuePair("pass", pass);
					
					parameters.add(bvp1);
					parameters.add(bvp2);
					
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, "utf-8");
					//把实体类封装至post请求中，提交post请求时，实体中的数据就会用输出流写给服务器
					post.setEntity(entity);
					//客户端发送post请求
					HttpResponse response = client.execute(post);
					
					//获取状态行
					StatusLine line = response.getStatusLine();
					//从状态行中拿到状态码
					if(line.getStatusCode() == 200){
						//获取实体，实体里存放的是服务器返回的数据的相关信息
						HttpEntity et = response.getEntity();
						//获取服务器返回的输入流
						InputStream is = et.getContent();
						
						String text = Tools.getTextFromStream(is);
						
						//发送消息，让主线程刷新ui
						Message msg = handler.obtainMessage();
						msg.obj = text;
						handler.sendMessage(msg);
					}
				}  catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t.start();
	}
}
