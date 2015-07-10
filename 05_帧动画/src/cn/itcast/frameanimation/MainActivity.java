package cn.itcast.frameanimation;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageView rocketImage = (ImageView) findViewById(R.id.iv);
		//��֡��������Դ�ļ���Ϊimageview�ı���ͼƬ
		rocketImage.setBackgroundResource(R.drawable.frameanimation);
		//��ȡimageView�ı������ѱ���ǿת�ɶ���Drawable
		AnimationDrawable rocketAnimation = (AnimationDrawable) rocketImage.getBackground();
		//���Ŷ���
		rocketAnimation.start();
	}


}
