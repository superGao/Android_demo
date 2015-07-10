package cn.itcast.ccalljava;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	static{
		System.loadLibrary("call");
	}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void click(View v){
    	callC();
    }
    
    public native void callC();
    
    public void showDialog(String message){
    	AlertDialog.Builder builder = new Builder(this);
    	builder.setMessage(message);
    	builder.show();
    }
    
}
