package cn.itcast.recorder;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
public class RecorderService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		//1.��ȡ�绰������
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		//2.�����绰״̬
		//arg0:�绰������
		//arg1���绰��������������״̬�ָܶ࣬��ֻ�����绰״̬�ı�
		tm.listen(new MyListener(), PhoneStateListener.LISTEN_CALL_STATE);
		
	}
	
	class MyListener extends PhoneStateListener{

		private MediaRecorder recorder;

		//���绰״̬�ı�ʱ���˷�������
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			// TODO Auto-generated method stub
			super.onCallStateChanged(state, incomingNumber);
			
			switch (state) {
			//����״̬
			case TelephonyManager.CALL_STATE_IDLE:
				System.out.println("����");
				if(recorder != null){
					//ֹͣ¼��
					recorder.stop();
					//�ͷ���Դ
					recorder.release();
					recorder = null;
				}
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				System.out.println("����");
				if(recorder == null){
					recorder = new MediaRecorder();
					//������Ƶ��Դ
					recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
					//���������ʽ
					recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					recorder.setOutputFile("sdcard/superGao.3gp");
					//������Ƶ�ı���
					recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
					try {
						recorder.prepare();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				System.out.println("ժ��");
				if(recorder != null){
					recorder.start();
				}
				break;

			}
			
		}
		
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
