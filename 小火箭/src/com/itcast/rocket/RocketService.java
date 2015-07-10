package com.itcast.rocket;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;

public class RocketService extends Service {

	private WindowManager mWM;
	private View mView;

	private int startX;
	private int startY;

	private int mScreenWidth;
	private int mScreenHeight;

	WindowManager.LayoutParams mParams;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mWM = (WindowManager) getSystemService(WINDOW_SERVICE);

		mScreenWidth = mWM.getDefaultDisplay().getWidth();// 屏幕宽度
		mScreenHeight = mWM.getDefaultDisplay().getHeight();// 屏幕高度

		// 初始化布局参数
		mParams = new WindowManager.LayoutParams();
		mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		mParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
		// | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
				| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
		mParams.format = PixelFormat.TRANSLUCENT;
		mParams.type = WindowManager.LayoutParams.TYPE_PHONE;// 提高类型级别,保证可以触摸移动
		mParams.gravity = Gravity.LEFT + Gravity.TOP;// 将重心设置在左上方位置,和屏幕坐标体系重合,方便修改窗口的位置

		mView = View.inflate(this, R.layout.rocket, null);

		// 开启帧动画
		ImageView ivRocket = (ImageView) mView.findViewById(R.id.iv_rocket);
		AnimationDrawable anim = (AnimationDrawable) ivRocket.getBackground();
		anim.start();

		mView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					// 记录起点坐标
					startX = (int) event.getRawX();
					startY = (int) event.getRawY();
					break;
				case MotionEvent.ACTION_MOVE:
					// 获取移动后坐标
					int endX = (int) event.getRawX();
					int endY = (int) event.getRawY();

					// 计算偏移量
					int dx = endX - startX;
					int dy = endY - startY;

					// 根据偏移量,更新位置
					mParams.x = mParams.x + dx;
					mParams.y = mParams.y + dy;

					System.out.println("x=" + mParams.x);
					System.out.println("y=" + mParams.y);

					// 防止坐标越界
					if (mParams.x < 0) {
						mParams.x = 0;
					}

					// 防止坐标越界
					if (mParams.x + mView.getWidth() > mScreenWidth) {
						mParams.x = mScreenWidth - mView.getWidth();
					}

					// 防止坐标越界
					if (mParams.y < 0) {
						mParams.y = 0;
					}

					// 防止坐标越界
					if (mParams.y + mView.getHeight() > mScreenHeight - 20) {
						mParams.y = mScreenHeight - 20 - mView.getHeight();
					}

					mWM.updateViewLayout(mView, mParams);// 更新最新位置

					// 重新初始化起点坐标
					startX = (int) event.getRawX();
					startY = (int) event.getRawY();
					break;
				case MotionEvent.ACTION_UP:
					if (mParams.x > 90 && mParams.x < 250 && mParams.y > 350) {
						System.out.println("发射火箭!");
						sendRocket();

						// 开启烟雾页面
						//从service中start一个activity,需要加标记FLAG_ACTIVITY_NEW_TASK
						Intent intent = new Intent(getApplicationContext(),
								BackgroundActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//启动一个新的任务栈
						startActivity(intent);
					}
					break;

				default:
					break;
				}

				return true;
			}
		});

		mWM.addView(mView, mParams);
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mParams.y = msg.arg1;
			mWM.updateViewLayout(mView, mParams);
		};
	};

	/**
	 * 发射火箭
	 */
	protected void sendRocket() {
		new Thread() {
			@Override
			public void run() {
				int distance = 350;
				for (int i = 0; i <= 10; i++) {
					int y = distance - i * 35;
					// mParams.y = y;
					// mWM.updateViewLayout(mView, mParams);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					Message msg = Message.obtain();
					msg.arg1 = y;
					mHandler.sendMessage(msg);
				}
			}
		}.start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mWM != null && mView != null) {
			mWM.removeView(mView);
		}
	}
}
