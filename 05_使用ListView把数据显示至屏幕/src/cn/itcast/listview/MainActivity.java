package cn.itcast.listview;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.sqlite.domain.Person;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	List<Person> personList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		personList = new ArrayList<Person>();
		//��ȡ���ݿ�
		MyOpenHelper oh = new MyOpenHelper(this);
		SQLiteDatabase db = oh.getWritableDatabase();
		
		Cursor cursor = db.query("person", null, null, null, null, null, null, null);
		while(cursor.moveToNext()){
			int _id = cursor.getInt(0);
			String name = cursor.getString(1);
			String phone = cursor.getString(2);
			int salary = cursor.getInt(3);
			
			Person p = new Person(_id, name, phone, salary);
			personList.add(p);
		}
		
		ListView lv = (ListView) findViewById(R.id.lv);
		lv.setAdapter(new MyAdapter());
	}
	
	class MyAdapter extends BaseAdapter{

		//��ȡ���ϵ�Ԫ�ص�����
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return personList.size();
		}

		//ϵͳ���ôη�������ȡһ��View���󣬸�View�������Ϊһ����Ŀ��ʾ��ListView��
		//postision:getView���ص�View�������ΪListView�ĵڼ�����Ŀ��ʾ,��ôposition��ֵ���Ƕ���
		//convertView:��ϵͳ�������Ŀ
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			System.out.println("getView����" + position + ":" + convertView);
			
			Person p = personList.get(position);
//			TextView tv = new TextView(MainActivity.this);
//			tv.setText(p.toString());
//			tv.setTextSize(16);
			
			View v = null;
			//���û�л���,����µ�View����
			if(convertView == null){
				//��ָ�������ļ�����View����
				v = View.inflate(MainActivity.this, R.layout.item_listview, null);
			}
			//����л��棬���û���
			else{
				v = convertView;
			}
			
//			LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//			View v = inflater.inflate(R.layout.item_listview, null);
			
//			LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//			View v = inflater.inflate(R.layout.item_listview, null);
			
			//�޸�View�����и������������
			TextView tv_name = (TextView) v.findViewById(R.id.tv_name);
			tv_name.setText(p.getName());
			TextView tv_phone = (TextView) v.findViewById(R.id.tv_phone);
			tv_phone.setText(p.getPhone());
			TextView tv_salary = (TextView) v.findViewById(R.id.tv_salary);
			tv_salary.setText(p.getSalary()+"");
			return v;
		}
		
		@Override
		public Object getItem(int position) {
			return personList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		
		
	}


}
