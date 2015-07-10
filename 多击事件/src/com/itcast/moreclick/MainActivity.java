package com.itcast.moreclick;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	long[] mHits = new long[3];// 数组长度多少,就点多少下

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onClick(View view) {
		// 参1:原数组, 参2:原数组拷贝位置, 参3:目标数组,参4:目标拷贝位置,参5:拷贝长度
		System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
		mHits[mHits.length - 1] = SystemClock.uptimeMillis();// 已开机时间
		if ((SystemClock.uptimeMillis() - mHits[0]) <= 500) {
			Toast.makeText(this, "是男人!!!", Toast.LENGTH_SHORT).show();
		}
	}

}
