package cn.itcast.sdstatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SDReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		//�ж��յ�����ʲô�㲥
		String action = intent.getAction();
		if(Intent.ACTION_MEDIA_MOUNTED.equals(action)){
			Toast.makeText(context, "SD������", 0).show();
		}
		else if(Intent.ACTION_MEDIA_UNMOUNTED.equals(action)){
			Toast.makeText(context, "SD��������", 0).show();
		}
		else if(Intent.ACTION_MEDIA_REMOVED.equals(action)){
			Toast.makeText(context, "SD��ˤ������", 0).show();
		}
	}

}
