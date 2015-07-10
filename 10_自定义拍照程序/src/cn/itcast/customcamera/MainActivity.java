package cn.itcast.customcamera;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

	private Camera mCamera;
    private CameraPreview mPreview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(checkCameraHardware(this)){
	        // ��ȡ����ͷʵ��
	        mCamera = getCameraInstance();
	
	        // ����Ԥ������Ķ���
	        mPreview = new CameraPreview(this, mCamera);
	        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
	        //��Ԥ���������Ϊ֡���ֵ��ӽڵ�
	        preview.addView(mPreview);
	        
	        //��ȡ���հ�ť
	        Button captureButton = (Button) findViewById(R.id.button_capture);
	        //���õ������
	        captureButton.setOnClickListener(
	            new View.OnClickListener() {
	                @Override
	                public void onClick(View v) {
	                	//�Զ��Խ�
	                	mCamera.autoFocus(new AutoFocusCallback(){

	                		//�Խ����ʱ����
							@Override
							public void onAutoFocus(boolean success,
									Camera camera) {
								//����
			                    mCamera.takePicture(null, null, mPicture);
							}
	                		
	                	});
	                    
	                }
	            }
	        );
        }
    }


	/** ����ֻ��Ƿ�������ͷ */
	private boolean checkCameraHardware(Context context) {
	    if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
	        // �ֻ�������ͷ
	        return true;
	    } else {
	        // û������ͷ
	        return false;
	    }
	}
	
	/** һ����ȫ�ķ�ʽȥ��ȡһ������ͷ��ʵ�� */
	public static Camera getCameraInstance(){
	    Camera c = null;
	    try {
	        c = Camera.open(); // ��ȡ��������ͷʵ��
	    }
	    catch (Exception e){
	        // Camera is not available (in use or does not exist)
	    }
	    return c; // returns null if camera is unavailable
	}
	
	private PictureCallback mPicture = new PictureCallback() {

		//������ϣ��˷�������
		//data:��Ƭ���ֽ�����
	    @Override
	    public void onPictureTaken(byte[] data, Camera camera) {

	        File pictureFile = new File("sdcard/ohohoho.jpg");

	        try {
	            FileOutputStream fos = new FileOutputStream(pictureFile);
	            fos.write(data);
	            fos.close();
	        } catch (FileNotFoundException e) {
	            Log.d(CameraPreview.TAG, "File not found: " + e.getMessage());
	        } catch (IOException e) {
	            Log.d(CameraPreview.TAG, "Error accessing file: " + e.getMessage());
	        } finally{
	        	mCamera.startPreview();
	        }
	    }
	};

}
