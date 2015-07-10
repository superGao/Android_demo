package cn.itcast.transmitdataagain;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	static final int CONTACTS_SELECT = 10;
	static final int SMS_SELECT = 20;
	static final int MASTER_SELECT = 30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }


    public void click(View v){
    	Intent intent = new Intent(this, ContactsActivity.class);
//    	startActivity(intent);
    	//Ϊ�˽��ȥ����Activity����ô��ContactsActivity����ʱ��ϵͳ�Ż����onActivityResult
    	startActivityForResult(intent, CONTACTS_SELECT);
    }
    public void click2(View v){
    	Intent intent = new Intent(this, SmsActivity.class);
    	//Ϊ�˽��ȥ����Activity����ô��ContactsActivity����ʱ��ϵͳ�Ż����onActivityResult
    	startActivityForResult(intent, SMS_SELECT);
    }
    public void click3(View v){
    	Intent intent = new Intent(this, MasterActivity.class);
    	//Ϊ�˽��ȥ����Activity����ô��ContactsActivity����ʱ��ϵͳ�Ż����onActivityResult
    	startActivityForResult(intent, MASTER_SELECT);
    }
    
    //��ĳ��Activity����ʱ����intent���ݸ�MainActivityʱ���˷�������
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	//requestCode:�ж�����������һ��Activity
    	//resultCode:�жϴ��ݹ�������������ʲô����
    	if(requestCode == CONTACTS_SELECT){
    		//��intentȡ�����ݹ���������
    		String name = data.getStringExtra("name");
    		EditText et_name = (EditText) findViewById(R.id.et_name);
    		//�����ݻ����������
    		et_name.setText(name);
    	}
    	else if(requestCode == SMS_SELECT){
    		String content = data.getStringExtra("content");
    		EditText et_content = (EditText) findViewById(R.id.et_content);
    		//�����ݻ����������
    		et_content.setText(content);
    	}
    	else if(requestCode == MASTER_SELECT){
    		if(resultCode == MasterActivity.MASTER_NAME){
    			String name = data.getStringExtra("name");
        		EditText et_name = (EditText) findViewById(R.id.et_name);
        		et_name.setText(name);
    		}
    		else if(resultCode == MasterActivity.MASTER_CONTENT){
    			String content = data.getStringExtra("content");
        		EditText et_content = (EditText) findViewById(R.id.et_content);
        		et_content.setText(content);
    		}
    	}
    }
}
