package com.itcast.popupdemo;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private RelativeLayout rlRoot;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
	}

	/**
	 * @param view 被点击的对象
	 */
	public void showPopup(View view) {
		TextView contentView = new TextView(this);
		contentView.setText("我是小弹窗哦, 萌萌哒!");
		contentView.setTextColor(Color.RED);
		contentView.setTextSize(22);

		PopupWindow popupWindow = new PopupWindow(contentView, 100, 100, true);// 参1:布局,参2,3宽高;
																				// 参4是否获取焦点
		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.GREEN));// 只有设置了背景之后,点击返回键和空白处才可以消失掉

		//popupWindow.showAtLocation(rlRoot, Gravity.CENTER, 0, 0);// 显示在屏幕某个位置
		popupWindow.showAsDropDown(view, 0, 0);//展示在某个布局正下方
	}

}
