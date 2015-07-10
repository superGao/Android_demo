package cn.itcast.rwinsd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText et_name;
	private EditText et_pass;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name = (EditText) findViewById(R.id.et_name);
    	et_pass = (EditText) findViewById(R.id.et_pass);
        
        readAccount();
    }

    public void login(View v){
    	
    	//获取用户输入的数据
    	String name = et_name.getText().toString();
    	String pass = et_pass.getText().toString();
    	
    	CheckBox cb = (CheckBox) findViewById(R.id.cb);
    	if(cb.isChecked()){
    		//MEDIA_REMOVED:sd卡被拔出
    		//MEDIA_UNMOUNTED:sd卡未挂载
    		//MEDIA_CHECKING:sd卡正在准备
    		//MEDIA_MOUNTED:sd卡已挂载，当前可用
    		//MEDIA_MOUNTED_READ_ONLY:sd卡挂载可用，但是只读
    		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
	    		//指定sd卡路径
    			//File file = new File("sdcard/info.txt");
	    		
	    		//返回一个File对象，路径是sd卡的真实路径
	    		File file = new File(Environment.getExternalStorageDirectory(), "info.txt");
	    		try {
					FileOutputStream fos = new FileOutputStream(file);
					fos.write((name + "##" + pass).getBytes());
					fos.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		else{
    			Toast.makeText(this, "sd卡不可用哟亲(づ￣ 3￣)づ", 0).show();
    		}
    	}
    	//弹出吐司对话框
    	Toast.makeText(this, "登录成功", 0).show();
    }
    
    public void readAccount(){
//    	File file = new File("sdcard/info.txt");
    	
    	File file = new File(Environment.getExternalStorageDirectory(), "info.txt");
    	if(file.exists()){
	    	try {
				FileInputStream fis = new FileInputStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				String text = br.readLine();
				String[] s = text.split("##");
				et_name.setText(s[0]);
				et_pass.setText(s[1]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
