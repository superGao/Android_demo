package cn.itcast.recorder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void click(View v){
    	//����¼������
    	Intent intent = new Intent(this, RecorderService.class);
    	startService(intent);
    }
    
}
