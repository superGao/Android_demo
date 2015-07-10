package com.itheima.admin;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private DevicePolicyManager mDPM;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		// mDPM.lockNow();//桌面一键锁屏
		// finish();
	}

	public void lockScreen(View view) {
		ComponentName component = new ComponentName(this, AdminReceiver.class);
		if (mDPM.isAdminActive(component)) {
			mDPM.lockNow();// 锁屏
			mDPM.resetPassword("123", 0);// 设置锁屏密码
		} else {
			Toast.makeText(this, "请先激活设备管理员", Toast.LENGTH_SHORT).show();
		}
		// mDPM.wipeData(0);//恢复出厂设置
		// mDPM.wipeData(DevicePolicyManager.WIPE_EXTERNAL_STORAGE);//清除sdcard内容
	}

	public void openAdmin(View view) {
		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		ComponentName component = new ComponentName(this, AdminReceiver.class);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, component);
		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "巴拉巴拉拉把拉链包");
		startActivity(intent);
	}

	public void uninstall(View view) {
		ComponentName component = new ComponentName(this, AdminReceiver.class);
		mDPM.removeActiveAdmin(component);// 删除超级管理权限

		// 跳转到卸载页面
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.setData(Uri.parse("package:" + getPackageName()));
		startActivity(intent);
	}
}
