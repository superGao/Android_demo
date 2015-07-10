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
				//1.�����ͻ��˶���
				HttpClient client = new DefaultHttpClient();
				//2.����http GET�������
				HttpGet get = new HttpGet(path);
				try {
					//3.ʹ�ÿͻ��˷���get����
					HttpResponse response = client.execute(get);
					//��ȡ״̬��
					StatusLine line = response.getStatusLine();
					//��״̬�����õ�״̬��
					if(line.getStatusCode() == 200){
						//��ȡʵ�壬ʵ�����ŵ��Ƿ��������ص����ݵ������Ϣ
						HttpEntity entity = response.getEntity();
						//��ȡ���������ص�������
						InputStream is = entity.getContent();
						
						String text = Tools.getTextFromStream(is);
						
						//������Ϣ�������߳�ˢ��ui
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
				//�����ͻ��˶���
				HttpClient client = new DefaultHttpClient();
				//����post�������
				HttpPost post = new HttpPost(path);
				
				try {
					//ͨ���˼��Ϸ�װҪ�ύ������
					List<NameValuePair> parameters = new ArrayList<NameValuePair>();
					
					//���ϵķ�����BasicNameValuePair���ͣ���ô�ɴ˿����������Ҫ�ύ�������Ƿ�װ��BasicNameValuePair�����е�
					BasicNameValuePair bvp1 = new BasicNameValuePair("name", name);
					BasicNameValuePair bvp2 = new BasicNameValuePair("pass", pass);
					
					parameters.add(bvp1);
					parameters.add(bvp2);
					
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, "utf-8");
					//��ʵ�����װ��post�����У��ύpost����ʱ��ʵ���е����ݾͻ��������д��������
					post.setEntity(entity);
					//�ͻ��˷���post����
					HttpResponse response = client.execute(post);
					
					//��ȡ״̬��
					StatusLine line = response.getStatusLine();
					//��״̬�����õ�״̬��
					if(line.getStatusCode() == 200){
						//��ȡʵ�壬ʵ�����ŵ��Ƿ��������ص����ݵ������Ϣ
						HttpEntity et = response.getEntity();
						//��ȡ���������ص�������
						InputStream is = et.getContent();
						
						String text = Tools.getTextFromStream(is);
						
						//������Ϣ�������߳�ˢ��ui
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
