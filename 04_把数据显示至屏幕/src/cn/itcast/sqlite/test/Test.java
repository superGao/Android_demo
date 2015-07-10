package cn.itcast.sqlite.test;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import cn.itcast.sqlite.MyOpenHelper;

public class Test extends AndroidTestCase {

	private MyOpenHelper oh;
	private SQLiteDatabase db;

	public void createDatabase(){
		//�������ݿ�
		//1.�����򿪰�����
										//��ȡһ������������
		MyOpenHelper oh = new MyOpenHelper(getContext());
		//2.�����������ݿ�
		//������ݿⲻ���ڣ��ȴ�����򿪣�������ݿ���ڣ�ֱ�Ӵ�
		SQLiteDatabase db = oh.getWritableDatabase();
	}
	
	//���Կ�ܳ�ʼ����Ϻ󣬲��Է���ִ��ǰ���˷�������
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		oh  = new MyOpenHelper(getContext());
		db = oh.getWritableDatabase();
	}
	
	//���Է���������Ϻ󣬴˷�������
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		db.close();
	}
	
	
	public void insertApi(){
		
		for(int i = 0; i < 50; i++){
			ContentValues values = new ContentValues();
			values.put("name", "��" + i);
			values.put("phone", "138" + i + i);
			values.put("salary", "200" + i);
			db.insert("person", null, values);
		}
	}
	
}
