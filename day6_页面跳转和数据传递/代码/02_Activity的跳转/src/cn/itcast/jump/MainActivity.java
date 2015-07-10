package cn.itcast.jump;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //��ʽ��ת
    public void click1(View v){
    	//������ͼ������ʽ��ͼ
    	Intent intent = new Intent();
    	intent.setAction(Intent.ACTION_CALL);
    	intent.setData(Uri.parse("tel:110"));
    	//�����µ�Activity��Ҳ����Activity����ת
    	startActivity(intent);
    }
    
    /**
     * ��ʽ����SecondActivity
     * @param v
     */
    //��ʽ��ת
    public void click2(View v){
    	//��ʽ��ͼ
    	Intent intent = new Intent();
    	//ͨ��ָ��Activity����ֽ��룬ָ��Ҫ��ת����һ��Activity
    	intent.setClass(this, SecondActivity.class);
    	startActivity(intent);
    }
    
    /**
     * ��ʽ����������
     * @param v
     */
    public void click3(View v){
    	Intent intent = new Intent();
    	//ָ��Ҫ�����ĸ�Ӧ���е��ĸ�Activity
    	intent.setClassName("com.android.dialer", "com.android.dialer.DialtactsActivity");
    	startActivity(intent);
    	
    }
    /**
     * ��ʽ����������
     * @param v
     */
    public void click4(View v){
    	Intent intent = new Intent();
    	intent.setAction(Intent.ACTION_DIAL);
    	startActivity(intent);
    	
    }
    
    /**
     * ��ʽ����SecondActivity
     * @param v
     */
    public void click5(View v){
    	Intent intent = new Intent();
    	intent.setAction("a.b.c");
//    	intent.setData(Uri.parse("itcast:hahaha"));
//    	intent.setType("aa/bb");
    	//setData��setType�������ܹ���
    	intent.setDataAndType(Uri.parse("itcast:hahaha"), "aa/bb");
    	//���д���д��д������,��д��Ĭ��ƥ��CATEGORY_DEFAULT
//    	intent.addCategory(Intent.CATEGORY_DEFAULT);
    	startActivity(intent);
    	
    }
    
    /**
     * ��ʽ���������
     * @param v
     */
    public void click6(View v){
    	Intent intent = new Intent();
    	intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
    	startActivity(intent);
    	
    }
    /**
     * ��ʽ���������
     * @param v
     */
    public void click7(View v){
    	Intent intent = new Intent();
    	//����ж��Activity�����intentƥ�䣬��ô�ͻᵯ��ѡ��Ի���
    	intent.setAction(Intent.ACTION_VIEW);
    	intent.setData(Uri.parse("http:www.baidu.com"));
    	startActivity(intent);
    	
    }
}
