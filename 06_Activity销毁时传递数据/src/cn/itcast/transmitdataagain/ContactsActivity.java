package cn.itcast.transmitdataagain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContactsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		
		
		final String[] names = new String[]{
				"�������˵ķ�����",
				"�ɰ��Ĺ�Ů"
		};
		ListView lv = (ListView) findViewById(R.id.lv);
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item_listview, //Ҫ����listview��Ŀ�Ĳ����ļ�����Դid
				R.id.tv_name, //�ַ�����ʾ���ĸ������
				names));
		
		//������Ŀ�������������Ϊ����Ŀ��������
		lv.setOnItemClickListener(new OnItemClickListener() {

			//��listView����Ŀ�����ʱ���˷�������
			//position:�������Ŀ������
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				//��Ҫ���ݵ����ݷ�װ��intent������
				Intent data = new Intent();
				data.putExtra("name", names[position]);
				
				//��ǰActivity����ʱ�����data���ݸ�MainActivity
				setResult(10, data);
				//���ٵ�ǰActivity
				finish();
			}

		});
	}
	
}
