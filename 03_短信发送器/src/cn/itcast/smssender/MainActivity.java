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
    	//��ȡ�û�����ĺ��������
    	EditText et_phone = (EditText) findViewById(R.id.et_phone);
    	EditText et_content = (EditText) findViewById(R.id.et_content);
    	
    	String phone = et_phone.getText().toString();
    	String content = et_content.getText().toString();
    	
    	//��ȡ���Ͷ��ŵ�api
    	SmsManager sm = SmsManager.getDefault();
    	
    	//�ѳ����Ų�ֳ��������̶���
    	ArrayList<String> smss = sm.divideMessage(content);
    	
    	for (String string : smss) {
    		//arg0:�Է�����
    		//arg1:���ŷ������ĵĺ���
    		//arg2����������
    		sm.sendTextMessage(phone, null, string, null, null);
		}
    }

    
}
