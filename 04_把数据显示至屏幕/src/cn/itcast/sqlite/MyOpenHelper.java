package cn.itcast.sqlite;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

	//arg1:���ݿ��ļ�������
	//arg2:�α깤�����α���ʵ���ǽ����
	//arg3:���ݿ�汾�������1����������
	public MyOpenHelper(Context context) {
		super(context, "people.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	//���ݿⴴ��ʱ���˷�������
	@Override
	public void onCreate(SQLiteDatabase db) {
		//ִ��sql���
		db.execSQL("create table person(_id integer primary key autoincrement, name char(10), phone char(20), salary integer(10))");
	}

	//���ݿ�����ʱ���˷�������
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("���ݿ�����");
	}

}
