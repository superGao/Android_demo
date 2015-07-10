package cn.itcast.getsdavail;

import java.io.File;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.format.Formatter;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		 File path = Environment.getExternalStorageDirectory();
         StatFs stat = new StatFs(path.getPath());
         long blockSize;
         long availableBlocks;
         
         //获取当前系统的level
         if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
        	  blockSize = stat.getBlockSizeLong();
              availableBlocks = stat.getAvailableBlocksLong();
         }
         else{
        	 blockSize = stat.getBlockSize();
             availableBlocks = stat.getAvailableBlocks();
         }
         
         String text = formatSize(availableBlocks * blockSize);
         TextView tv = (TextView) findViewById(R.id.tv);
         tv.setText(text);
	}
	private String formatSize(long size) {
        return Formatter.formatFileSize(this, size);
    }

}
