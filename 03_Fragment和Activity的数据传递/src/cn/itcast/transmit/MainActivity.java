package cn.itcast.transmit;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Fragment01 fragment01;
	private Fragment02 fragment02;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //1.����Fragment����
        fragment01 = new Fragment01();
    	//2.��ȡFragment������
    	FragmentManager fm = getFragmentManager();
    	//3.������
    	FragmentTransaction ft = fm.beginTransaction();
    	//4.������ʾfragment01
    	//arg0:������id��Ҳ����֡����
    	ft.replace(R.id.fl, fragment01);
    	//5.�ύ
    	ft.commit();
    }


    public void click1(View v){
    	//��ʾfragment01
    	//2.��ȡFragment������
    	FragmentManager fm = getFragmentManager();
    	//3.������
    	FragmentTransaction ft = fm.beginTransaction();
    	//4.������ʾfragment01
    	//arg0:������id��Ҳ����֡����
    	ft.replace(R.id.fl, fragment01);
    	//5.�ύ
    	ft.commit();
    }
    
    public void click2(View v){
    	//��ʾfragment02
    	//1.����Fragment����
    	fragment02 = new Fragment02();
    	//2.��ȡFragment������
    	FragmentManager fm = getFragmentManager();
    	//3.������
    	FragmentTransaction ft = fm.beginTransaction();
    	//4.������ʾfragment02
    	//arg0:������id��Ҳ����֡����
    	ft.replace(R.id.fl, fragment02);
    	//5.�ύ
    	ft.commit();
    }
    
    public void click3(View v){
    	//��ʾfragment03
    	//1.����Fragment����
    	Fragment03 fragment03 = new Fragment03();
    	//2.��ȡFragment������
    	FragmentManager fm = getFragmentManager();
    	//3.������
    	FragmentTransaction ft = fm.beginTransaction();
    	//4.������ʾfragment03
    	//arg0:������id��Ҳ����֡����
    	ft.replace(R.id.fl, fragment03);
    	//5.�ύ
    	ft.commit();
    }
    
    public void click4(View v){
    	//��ȡ�û����������
    	EditText et_main = (EditText) findViewById(R.id.et_main);
    	String text = et_main.getText().toString();
    	//���ݸ�Fragment
    	fragment02.setText(text);
    }
    
    public void setText(String text){
    	TextView tv_main = (TextView) findViewById(R.id.tv_main);
    	tv_main.setText(text);
    }
}
