package com.itcast.superadmin;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private DevicePolicyManager mDPM;
	private ComponentName mDeviceAdminSample;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 设备策略管理器
		mDPM = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
		// 初始化管理员组件
		mDeviceAdminSample = new ComponentName(this, MyAdminReceiver.class);

		// mDPM.lockNow();
		// finish();
	}

	/**
	 * 立即锁屏
	 */
	public void lockNow(View view) {
		if (mDPM.isAdminActive(mDeviceAdminSample)) {
			mDPM.lockNow();// 立即锁屏
			mDPM.resetPassword("123456", 0);// 重新设置密码, 传""取消密码
		} else {
			Toast.makeText(this, "您需要先激活超级管理员!", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 激活超级管理员权限 设置->安全->设备管理器
	 * 
	 * @param view
	 */
	public void activeAdmin(View view) {
		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,
				mDeviceAdminSample);
		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
				"超级管理员,您的好帮手,哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
		startActivity(intent);
	}

	/**
	 * 一键卸载
	 * 
	 * @param view
	 */
	public void uninstall(View view) {
		// 清除超级管理员权限
		mDPM.removeActiveAdmin(mDeviceAdminSample);

		// 跳到系统卸载页面
		Intent intent = new Intent(Intent.ACTION_DELETE);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.setData(Uri.parse("package:" + getPackageName()));
		startActivity(intent);
	}

	/**
	 * 清除数据
	 * 
	 * @param view
	 */
	public void wipeData(View view) {
		if (mDPM.isAdminActive(mDeviceAdminSample)) {
			mDPM.wipeData(DevicePolicyManager.WIPE_EXTERNAL_STORAGE);// 清除内部数据和sdcard
		} else {
			Toast.makeText(this, "您需要先激活超级管理员!", Toast.LENGTH_SHORT).show();
		}
	}
}
