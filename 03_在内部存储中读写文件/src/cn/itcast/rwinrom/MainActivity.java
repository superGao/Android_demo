package cn.itcast.rwinrom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
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
    	
    	
    	//��ȡ�û����������
    	String name = et_name.getText().toString();
    	String pass = et_pass.getText().toString();
    	
    	CheckBox cb = (CheckBox) findViewById(R.id.cb);
    	if(cb.isChecked()){
    		//ָ���ڲ��洢�ռ��·��
//	    	File file = new File("data/data/cn.itcast.rwinrom/info.txt");
    		
    		//����һ��File����·����data/data/cn.itcast.rwinrom/files/
//	    	File file = new File(getFilesDir(), "info.txt");
    		
    		//����һ��File����·����data/data/cn.itcast.rwinrom/cache/
	    	File file = new File(getCacheDir(), "info.txt");
	    	try {
				FileOutputStream fos = new FileOutputStream(file);
				fos.write((name + "##" + pass).getBytes());
				fos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	//������˾�Ի���
    	Toast.makeText(this, "��¼�ɹ�", 0).show();
    }
    
    public void readAccount(){
//    	File file = new File("data/data/cn.itcast.rwinrom/info.txt");
    	File file = new File(getFilesDir(), "info.txt");
    	if(file.exists()){
	    	try {
				FileInputStream fis = new FileInputStream(file);
				//���ֽ���ת�����ַ���
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				String text = br.readLine();
				String s[] = text.split("##");
				
		    	et_name.setText(s[0]);
		    	et_pass.setText(s[1]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
