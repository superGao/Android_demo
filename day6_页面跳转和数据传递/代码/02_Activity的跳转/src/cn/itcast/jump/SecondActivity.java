package cn.itcast.jump;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		
		//��ȡ�������Activity����ͼ
		Intent intent = getIntent();
		
		Uri uri = intent.getData();
		System.out.println(uri.toString());
	}
}
