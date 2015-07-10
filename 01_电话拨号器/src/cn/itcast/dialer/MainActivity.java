package cn.itcast.dialer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	//Activity������ʱ���˷�������
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //ͨ����Դid����ȡָ��������Ķ���
        Button bt = (Button) findViewById(R.id.bt);
        //���õ������
        bt.setOnClickListener(new MyListener());
        
    }

    class MyListener implements OnClickListener{

    	//��ť����ʱ���˷�������
		@Override
		public void onClick(View v) {
			EditText et = (EditText) findViewById(R.id.et);
			//�õ��û�����ĺ���
			String phone = et.getText().toString();
			
			//����ϵͳ�����ǵĶ���
			//1.������ͼ����
			Intent intent = new Intent();
			//2.���ö���
			intent.setAction(Intent.ACTION_CALL);
			//3.���ú���
			intent.setData(Uri.parse("tel:" + phone));
			//4.�Ѷ�������ϵͳ
			startActivity(intent);
		}
    	
    }

}
