package cn.itcast.contentobserver;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //ע�����ݹ۲���
        ContentResolver resolver = getContentResolver();
        //arg0:�����ĸ������ṩ�߷�����֪ͨ
        //arg1��true��ʾֻҪ��content://sms��ͷ��uri�ϸı�����ݣ������յ�֪ͨ��false����ʾ���뾫ȷƥ�䣬ֻ��content://sms�ϵ����ݸı䣬�Ż��յ�֪ͨ
        resolver.registerContentObserver(Uri.parse("content://sms"), true, new MyObserver(new Handler()));
    }
    
    class MyObserver extends ContentObserver{

		public MyObserver(Handler handler) {
			super(handler);
			// TODO Auto-generated constructor stub
		}
		
		//�յ����ݿ����ݸı��֪ͨʱ,�˷�������
		@Override
		public void onChange(boolean selfChange) {
			// TODO Auto-generated method stub
			super.onChange(selfChange);
			
			System.out.println("�������ݿ����ݸı�");
		}
    	
    }


    
}
