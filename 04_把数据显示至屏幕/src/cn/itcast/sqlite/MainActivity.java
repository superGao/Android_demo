package cn.itcast.sqlite;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.showdata.R;
import cn.itcast.sqlite.domain.Person;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

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
		//获取线性布局
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
		for (Person p : personList) {
			TextView tv = new TextView(this);
			tv.setText(p.toString());
			tv.setTextSize(16);
			
			//把tv设置为线性布局的子节点
			ll.addView(tv);
		}
	}


}
