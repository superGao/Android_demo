package com.itcast.doubleclick;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private long firstClickTime;// 首次点击时间

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onClick(View view) {
		if (firstClickTime > 0) {
			if (System.currentTimeMillis() - firstClickTime < 500) {
				Toast.makeText(this, "双击啦!!!", Toast.LENGTH_SHORT).show();
				firstClickTime = 0;
				return;
			}
		}

		firstClickTime = System.currentTimeMillis();
	}
}
