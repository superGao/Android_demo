package cn.itcast.smssender;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send(View v){
    	//获取用户输入的号码和内容
    	EditText et_phone = (EditText) findViewById(R.id.et_phone);
    	EditText et_content = (EditText) findViewById(R.id.et_content);
    	
    	String phone = et_phone.getText().toString();
    	String content = et_content.getText().toString();
    	
    	//获取发送短信的api
    	SmsManager sm = SmsManager.getDefault();
    	
    	//把长短信拆分成若干条短短信
    	ArrayList<String> smss = sm.divideMessage(content);
    	
    	for (String string : smss) {
    		//arg0:对方号码
    		//arg1:短信服务中心的号码
    		//arg2：短信内容
    		sm.sendTextMessage(phone, null, string, null, null);
		}
    }

    
}
