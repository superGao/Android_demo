package cn.itcast.transmit;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;

public class Fragment01 extends Fragment {

	//ϵͳ���ã�����һ��View������ΪFragment����ʾ����
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//�Ѳ����ļ�����View����return��ȥ
		final View v = inflater.inflate(R.layout.fragment01, null);
		Button bt_fragment01 = (Button) v.findViewById(R.id.bt_fragment01);
		
		//���ð�ť�������
		bt_fragment01.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				//��ȡ�û���������
				EditText et = (EditText) v.findViewById(R.id.et_fragment01);
				String text = et.getText().toString();
				//���ݸ�Activity
				//��ȡMainActivity�Ķ���
				((MainActivity)getActivity()).setText(text);
			}
		});
		return v;
	}
}
