package cn.itcast.transmit;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;

public class Fragment02 extends Fragment {

	private TextView tv_fragment02;

	//ϵͳ���ã�����һ��View������ΪFragment����ʾ���� 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//�Ѳ����ļ�����View����return��ȥ
		View v = inflater.inflate(R.layout.fragment02, null);
		
		tv_fragment02 = (TextView) v.findViewById(R.id.tv_fragment02);
		return v;
	}
	
	public void setText(String text){
		tv_fragment02.setText(text);
	}
}



