package com.itheima.call;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	//需要权限:  <uses-permission android:name="com.android.launcher.permission.INSTALL_SHORTCUT"/>
	public void click(View view) {
		Intent intent = new Intent(
				"com.android.launcher.action.INSTALL_SHORTCUT");
		// 应用名称
		intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "立即报警");

		// 应用图标
		intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, BitmapFactory
				.decodeResource(getResources(), R.drawable.home_apps));

		// 应用动作
		Intent actionIntent = new Intent(Intent.ACTION_DIAL,
				Uri.parse("tel://110"));
		intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, actionIntent);

		//发送广播
		sendBroadcast(intent);
	}

}
