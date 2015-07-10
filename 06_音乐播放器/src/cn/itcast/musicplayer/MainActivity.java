package cn.itcast.musicplayer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

	MusicControllerInterface mi;
	private static SeekBar sb;
	
	static Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			Bundle data = msg.getData();
			int duration = data.getInt("duration");
			int currentPosition = data.getInt("currentPosition");
			
			//�ѽ������ø�������
			sb.setMax(duration);
			sb.setProgress(currentPosition);
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        sb = (SeekBar) findViewById(R.id.sb);
        sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
        	//��ָ̧��
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				//��ָ̧��ʱ����ȡ�������Ľ���
				mi.seekTo(seekBar.getProgress());
			}
			
			// ��ָ����
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			//��ָ����
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
			}
		});
        
        Intent intent = new Intent(this, MusicService.class);
        
        //Ϊ���ý��̱�ɷ������
        startService(intent);
        //Ϊ���õ��м��˶���
        bindService(intent, new MyConnection(), BIND_AUTO_CREATE);
    }

    class MyConnection implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mi = (MusicControllerInterface) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
    	
    }

    public void play(View v){
    	mi.play();
    }
    
    public void pause(View v){
    	mi.pause();
    }
    public void continuePlay(View v){
    	mi.continuePlay();
    }
    
}
