package cn.itcast.sqlite.test;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import cn.itcast.sqlite.MyOpenHelper;

public class Test extends AndroidTestCase {

	private MyOpenHelper oh;
	private SQLiteDatabase db;

	public void createDatabase(){
		//创建数据库
		//1.创建打开帮助器
										//获取一个虚拟上下文
		MyOpenHelper oh = new MyOpenHelper(getContext());
		//2.创建并打开数据库
		//如果数据库不存在，先创建后打开，如果数据库存在，直接打开
		SQLiteDatabase db = oh.getWritableDatabase();
	}
	
	//测试框架初始化完毕后，测试方法执行前，此方法调用
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		oh  = new MyOpenHelper(getContext());
		db = oh.getWritableDatabase();
	}
	
	//测试方法运行完毕后，此方法调用
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		db.close();
	}
	
	
	public void insertApi(){
		
		for(int i = 0; i < 50; i++){
			ContentValues values = new ContentValues();
			values.put("name", "张" + i);
			values.put("phone", "138" + i + i);
			values.put("salary", "200" + i);
			db.insert("person", null, values);
		}
	}
	
}
