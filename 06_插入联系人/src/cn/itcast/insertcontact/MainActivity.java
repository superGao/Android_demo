package cn.itcast.insertcontact;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void click(View v){
    	ContentResolver resolver = getContentResolver();
    	//������ϵ��֮ǰ�Ȳ�ѯraw_contacts,�������²������ϵ��idӦ���Ƕ���
    	Cursor cursor = resolver.query(Uri.parse("content://com.android.contacts/raw_contacts"), new String[]{"_id"}, 
    			null, null, null);
    	int _id = 0;
    	//�ƶ������һ��
    	if(cursor.moveToLast()){
    		_id = cursor.getInt(0);
    	}
    	//����������ϵ�˵�idӦ���Ƕ���
    	_id++;
    	
    	//�Ȳ�����ϵ��id
    	ContentValues values = new ContentValues();
    	values.put("contact_id", _id);
    	resolver.insert(Uri.parse("content://com.android.contacts/raw_contacts"), values);
    	
    	values.clear();
    	values.put("raw_contact_id", _id);
    	values.put("mimetype", "vnd.android.cursor.item/name");
    	values.put("data1", "ʥս����׿");
    	//��data���в�����ϵ����Ϣ
    	resolver.insert(Uri.parse("content://com.android.contacts/data"), values);
    	
    	values.clear();
    	values.put("raw_contact_id", _id);
    	values.put("mimetype", "vnd.android.cursor.item/phone_v2");
    	values.put("data1", "13444444");
    	//��data���в�����ϵ����Ϣ
    	resolver.insert(Uri.parse("content://com.android.contacts/data"), values);
    }
    
}
