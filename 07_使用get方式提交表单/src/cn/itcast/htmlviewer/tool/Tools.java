package cn.itcast.htmlviewer.tool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Tools {

	public static String getTextFromStream(InputStream is){
		
		try {
			byte[] b = new byte[1024];
			int len;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			while((len = is.read(b)) != -1){
				bos.write(b, 0, len);
			}
			//						把输出流里的内容转换成字节数组	
			String text = new String(bos.toByteArray());
			return text;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
