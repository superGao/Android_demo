package com.itheima.cache55;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 需要权限:android.permission.GET_PACKAGE_SIZE
 * 
 * @author Kevin
 * 
 */
public class MainActivity extends Activity {

	private EditText etPackage;
	private Button btnOk;
	private TextView tvResult;

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			String result = (String) msg.obj;
			tvResult.setText(result);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etPackage = (EditText) findViewById(R.id.et_package);
		btnOk = (Button) findViewById(R.id.btn_ok);
		tvResult = (TextView) findViewById(R.id.tv_result);

		btnOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String packageName = etPackage.getText().toString().trim();
				if (!TextUtils.isEmpty(packageName)) {
					PackageManager pm = getPackageManager();
					try {
						Method method = pm.getClass().getDeclaredMethod(
								"getPackageSizeInfo", String.class,
								IPackageStatsObserver.class);
						method.invoke(pm, packageName, new MyObserver());
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					Toast.makeText(getApplicationContext(), "输入内容不能为空!",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	class MyObserver extends IPackageStatsObserver.Stub {

		// 在子线程运行
		@Override
		public void onGetStatsCompleted(PackageStats pStats, boolean succeeded)
				throws RemoteException {
			long cacheSize = pStats.cacheSize;
			long dataSize = pStats.dataSize;
			long codeSize = pStats.codeSize;

			String result = "缓存:"
					+ Formatter.formatFileSize(getApplicationContext(),
							cacheSize)
					+ "\n"
					+ "数据:"
					+ Formatter.formatFileSize(getApplicationContext(),
							dataSize)
					+ "\n"
					+ "代码:"
					+ Formatter.formatFileSize(getApplicationContext(),
							codeSize);
			System.out.println(result);
			Message msg = Message.obtain();
			msg.obj = result;
			mHandler.sendMessage(msg);
		}

	}

}
