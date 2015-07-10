package com.itheima.location;

import java.util.List;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	private LocationManager lm;
	private MyLocationListener listener;

	private TextView tvLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvLocation = (TextView) findViewById(R.id.tv_location);

		lm = (LocationManager) getSystemService(LOCATION_SERVICE);//获取系统位置服务

		List<String> allProviders = lm.getAllProviders();//获取所有位置提供者
		for (String string : allProviders) {
			System.out.println(string);
		}

		listener = new MyLocationListener();//位置监听器
		lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0,
				listener);//更新位置, 参2和参3设置为0,表示只要位置有变化就立即更新
	}

	class MyLocationListener implements LocationListener {

		//位置发生变化
		@Override
		public void onLocationChanged(Location location) {
			System.out.println("onLocationChanged");
			String longitude = "经度:" + location.getLongitude();
			String latitude = "纬度:" + location.getLatitude();
			String accuracy = "精确度:" + location.getAccuracy();
			String altitude = "海拔:" + location.getAltitude();

			tvLocation.setText(longitude + "\n" + latitude + "\n" + accuracy
					+ "\n" + altitude);
		}

		//位置提供者的状态发生变化
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			System.out.println("onStatusChanged");
		}

		//位置提供者可用
		@Override
		public void onProviderEnabled(String provider) {
			System.out.println("onProviderEnabled");
		}

		//位置提供者不可用
		@Override
		public void onProviderDisabled(String provider) {
			System.out.println("onProviderDisabled");
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		lm.removeUpdates(listener);//为了节省性能,当页面销毁时,删除位置更新的服务
		listener = null;
	}

}
