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
		//读取数据库
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

		//获取集合的元素的数量
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return personList.size();
		}

		//系统调用次方法，获取一个View对象，该View对象会作为一个条目显示至ListView中
		//postision:getView返回的View对象会作为ListView的第几个条目显示,那么position的值就是多少
		//convertView:被系统缓存的条目
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			System.out.println("getView调用" + position + ":" + convertView);
			
			Person p = personList.get(position);
//			TextView tv = new TextView(MainActivity.this);
//			tv.setText(p.toString());
//			tv.setTextSize(16);
			
			View v = null;
			//如果没有缓存,填充新的View对象
			if(convertView == null){
				//把指定布局文件填充成View对象
				v = View.inflate(MainActivity.this, R.layout.item_listview, null);
			}
			//如果有缓存，复用缓存
			else{
				v = convertView;
			}
			
//			LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//			View v = inflater.inflate(R.layout.item_listview, null);
			
//			LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//			View v = inflater.inflate(R.layout.item_listview, null);
			
			//修改View对象中各个组件的内容
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
