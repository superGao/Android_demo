package com.itcast.rocket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

/**
 * 烟雾背景图片
 * 
 * @author Kevin
 * 
 */
public class BackgroundActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_backgroud);

		ImageView ivTop = (ImageView) findViewById(R.id.iv_top);
		ImageView ivBottom = (ImageView) findViewById(R.id.iv_bottom);

		// 渐变动画
		AlphaAnimation anim = new AlphaAnimation(0, 1);
		anim.setDuration(1000);

		ivTop.startAnimation(anim);
		ivBottom.startAnimation(anim);

		// 延时1秒后执行一个Runnable对象
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				finish();
			}
		}, 1000);
	}
}
