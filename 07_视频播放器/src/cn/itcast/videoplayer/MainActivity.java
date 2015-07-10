package cn.itcast.videoplayer;

import java.io.IOException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MainActivity extends Activity {

	
	private MediaPlayer player;

	int currentPosition;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		SurfaceView sv = (SurfaceView) findViewById(R.id.sv);
		//��ȡSurfaceView������
		final SurfaceHolder sh = sv.getHolder();
		
		//��Щ������Ч����ΪSurfaceView���ɼ�
//		player.reset();
//		try {
//			player.setDataSource("sdcard/2.3gp");
//			//������Ƶ�������ĸ�������
//			player.setDisplay(sh);
//			player.prepare();
//			player.start();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		Thread t = new Thread(){
//			@Override
//			public void run() {
//				try {
//					sleep(200);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				//�����߳�����
//				runOnUiThread(new Runnable() {
//					@Override
//					public void run() {
//						player.reset();
//						try {
//							player.setDataSource("sdcard/2.3gp");
//							//������Ƶ�������ĸ�������
//							player.setDisplay(sh);
//							player.prepare();
//							player.start();
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				});
//			}
//		};
//		t.start();
		
		sh.addCallback(new Callback() {
			//SurfaceView����ʱ����
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				if(player != null){
					currentPosition = player.getCurrentPosition();
					player.stop();
					player.release();
					player = null;
				}
			}
			
			//SurfaceView����ʱ����
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				if(player == null){
					player = new MediaPlayer();
					player.reset();
					try {
						player.setDataSource("sdcard/2.3gp");
						//������Ƶ�������ĸ�������
						player.setDisplay(sh);
						player.prepare();
						player.seekTo(currentPosition);
						player.start();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			//SurfaceView�ṹ�ı�ʱ����
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				
			}
		});
	}


}
