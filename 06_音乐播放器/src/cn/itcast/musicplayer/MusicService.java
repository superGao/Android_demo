package cn.itcast.musicplayer;

import java.io.FileDescriptor;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

public class MusicService extends Service {

	//��ý�岥������
	MediaPlayer player;
	private Timer timer;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return new MusicController();
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		player = new MediaPlayer();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		player.stop();
		player.release();
		
		if(timer != null){
			//ֹͣˢ�½���
			timer.cancel();
			timer = null;
		}
	}
	//���м����еķ�����ȡ���ӿ��У�Ȼ��ʵ������ӿ�
	class MusicController extends Binder implements MusicControllerInterface{
		@Override
		public void play(){
			//�����ⲿ���ͬ������
			MusicService.this.play();
		}

		@Override
		public void pause() {
			MusicService.this.pause();
			
		}

		@Override
		public void continuePlay() {
			MusicService.this.continuePlay();
			
		}

		@Override
		public void seekTo(int progress) {
			MusicService.this.seekTo(progress);
			
		}

	}
	public void play(){
		//�������״̬
		player.reset();
		try {
			
			AssetManager am=this.getAssets();
			AssetFileDescriptor afd=am.openFd("sfh.mp3");
			FileDescriptor fd=afd.getFileDescriptor();
			//����Ҫ���ŵ�����
			player.setDataSource(fd);
			
			//afd.close();
//			player.setDataSource("http://192.168.1.100:8080/bzj.mp3");
			//ͬ��׼��
//			player.prepare();
			//�첽׼��
			player.prepareAsync();
			player.setOnPreparedListener(new OnPreparedListener() {
				
				//�첽׼�����ʱ���˷�������
				@Override
				public void onPrepared(MediaPlayer mp) {
					player.start();
					addTimer();
				}
			});
//			player.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void pause(){
		player.pause();
	}
	public void continuePlay(){
		player.start();
	}
	
	public void seekTo(int progress){
		player.seekTo(progress);
	}
	
	public void addTimer(){
		if(timer == null){
			timer = new Timer();
			timer.schedule(new TimerTask() {
				//run�����������߳���ִ�е�
				@Override
				public void run() {
					//��ȡ��Ƶ�ļ�����ʱ��
					int duration = player.getDuration();
					//��ȡ��ǰ���Ž���
					int currentPosition = player.getCurrentPosition();
					
					//������Ϣ
					Message msg = MainActivity.handler.obtainMessage();
					//�Ѳ��Ž��ȷ�װ����Ϣ��
					Bundle data = new Bundle();
					data.putInt("duration", duration);
					data.putInt("currentPosition", currentPosition);
					msg.setData(data);
					//������Ϣ
					MainActivity.handler.sendMessage(msg);
					
				}
				//5�����ִ��run����������ÿ500����ִ��һ��run����
			}, 5, 500);
		}
	}
}
