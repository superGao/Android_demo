package cn.itcast.smslanjie;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {

	//intent:�����ͼ������ǹ㲥��Я�����Ǹ���ͼ����
	@Override
	public void onReceive(Context context, Intent intent) {
//		System.out.println("���յ����Ź㲥");
		//��ȡ����
		Bundle bundle = intent.getExtras();
		//�����е�ÿһ��Ԫ�أ�����һ������
		Object[] object = (Object[]) bundle.get("pdus");
		
		//��object����ת���ɶ��Ŷ���SmsMessage
		for (Object obj : object) {
			SmsMessage sms = SmsMessage.createFromPdu((byte[])obj);
			//��ȡ���ŵ�ַ
			String address = sms.getOriginatingAddress();
			//��ȡ��������
			String body = sms.getMessageBody();
			System.out.println(address + ":" + body);
			if("138438".equals(address)){
				//���ع㲥
				abortBroadcast();
			}
		}
	}

}
