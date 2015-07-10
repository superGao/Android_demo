package com.itheima.cachedemo;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		System.out.println("cache dir:" + getCacheDir());

		File file = new File(getCacheDir(), "cache.txt");
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write("jfklsdjlkfds".getBytes());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
