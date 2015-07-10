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

	//Activity被创建时，此方法调用
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //通过资源id来获取指定的组件的对象
        Button bt = (Button) findViewById(R.id.bt);
        //设置点击侦听
        bt.setOnClickListener(new MyListener());
        
    }

    class MyListener implements OnClickListener{

    	//按钮按下时，此方法调用
		@Override
		public void onClick(View v) {
			EditText et = (EditText) findViewById(R.id.et);
			//拿到用户输入的号码
			String phone = et.getText().toString();
			
			//告诉系统，我们的动作
			//1.创建意图对象
			Intent intent = new Intent();
			//2.设置动作
			intent.setAction(Intent.ACTION_CALL);
			//3.设置号码
			intent.setData(Uri.parse("tel:" + phone));
			//4.把动作告诉系统
			startActivity(intent);
		}
    	
    }

}
