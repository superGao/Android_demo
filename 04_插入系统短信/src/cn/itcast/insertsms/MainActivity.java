package cn.itcast.insertsms;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mms);
    }


    public void click(View v){
    	//ͨ�������ṩ�߲�����������ݿ�
    	final ContentResolver resolver = getContentResolver();
    	final ContentValues values = new ContentValues();
    	
    	Thread t = new Thread(){
    		@Override
    		public void run() {
    			try {
					sleep(7000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			values.put("address", "95555");
    	    	values.put("date", System.currentTimeMillis());
    	    	values.put("type", 1);
    	    	values.put("body", "��β��ΪXXXX�����д���յ�ת��1,000,000�����");
    	    	resolver.insert(Uri.parse("content://sms"), values);
    		}
    	};
    	t.start();
    	
    }
}
