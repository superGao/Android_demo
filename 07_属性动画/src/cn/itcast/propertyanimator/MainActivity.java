package cn.itcast.propertyanimator;

import android.os.Bundle;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ImageView iv;
	private ObjectAnimator oa1;
	private ObjectAnimator oa2;
	private ObjectAnimator oa3;
	private ObjectAnimator oa5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		iv = (ImageView) findViewById(R.id.iv);
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "�����ѽ", 0).show();
				
			}
		});
	}

	public void translate(View v){
//		//����ƽ�Ʋ��䶯��
//		TranslateAnimation ta = new TranslateAnimation(0, 100, 0, 0);
//		//���ö�������ʱ��
//		ta.setDuration(2000);
//		//���ö���ͣ���ڽ���λ��
//		ta.setFillAfter(true);
//		iv.startAnimation(ta);
		
		//�������Զ���ʦ
		//arg0:ָ�����Ŷ��������
		//arg1:Ҫ�޸��ĸ����Ե�ֵ
		//arg2:Ҫ�޸ĵ��µ�ֵ
		
		oa1 = ObjectAnimator.ofFloat(iv, "translationX", 0, 70, 20, 120);
		oa1.setDuration(2000);
		oa1.setRepeatCount(1);
		oa1.setRepeatMode(ObjectAnimator.REVERSE);
		//��ʼ�������Զ���
		oa1.start();
	}
	
	public void scale(View v){
		oa2 = ObjectAnimator.ofFloat(iv, "scaleY", 0.5f, 1.5f, 0.7f, 2);
		oa2.setDuration(2000);
		oa2.setRepeatCount(1);
		oa2.setRepeatMode(ObjectAnimator.REVERSE);
		//��ʼ�������Զ���
		oa2.start();
	}

	public void alpha(View v){
		oa3 = ObjectAnimator.ofFloat(iv, "alpha", 0.3f, 0.7f, 0.4f, 1);
		oa3.setDuration(2000);
		oa3.setRepeatCount(1);
		oa3.setRepeatMode(ObjectAnimator.REVERSE);
		//��ʼ�������Զ���
		oa3.start();
	}
	public void rotate(View v){
		oa5 = ObjectAnimator.ofFloat(iv, "rotationY", 0, 200, 45, 360);
		oa5.setDuration(2000);
		oa5.setRepeatCount(1);
		oa5.setRepeatMode(ObjectAnimator.REVERSE);
		//��ʼ�������Զ���
		oa5.start();
	}
	public void fly(View v){
		//����һ������ʦ����
		AnimatorSet as = new AnimatorSet();
		//һ��һ��װ��һ��һ����
//		as.playSequentially(oa1, oa2, oa3, oa5);
		//һ��װ��һ���
		as.playTogether(oa1, oa2, oa3, oa5);
		//���ò��Ŷ����Ķ���
		as.setTarget(iv);
		as.start();
	}
	
	public void loadXml(View v){
		//����xml��������Զ���
		Animator at = AnimatorInflater.loadAnimator(this, R.animator.objectanimator);
		at.setTarget(iv);
		at.start();
	}
}
