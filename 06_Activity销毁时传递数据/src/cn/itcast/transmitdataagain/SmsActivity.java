package cn.itcast.transmitdataagain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SmsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		
		final String[] content = new String[]{
				"�ź����ڹ���̣�һ������ظ���",
				"�ź����ڼ����һ������ظ���",
				"�ź����ڰ��ᣬһ��������Ҳ�ز�����"
		};
		ListView lv = (ListView) findViewById(R.id.lv);
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item_listview, //Ҫ����listview��Ŀ�Ĳ����ļ�����Դid
				R.id.tv_name, //�ַ�����ʾ���ĸ������
				content));
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent data = new Intent();
				//�����ݷ�װ��intent��
				data.putExtra("content", content[position]);
				setResult(20, data);
				
				//���ٵ�ǰActivity
				finish();
			}
		});
	}
}
