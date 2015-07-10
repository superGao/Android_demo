import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5算法
 * 
 * @author Kevin
 * 
 */
public class MD5 {

	public static void main(String[] args) {
		try {
			String password = "123456";
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] result = digest.digest(password.getBytes());

			StringBuffer sb = new StringBuffer();
			for (byte b : result) {
				int i = b & 0xff;// 将字节转为整数
				String hexString = Integer.toHexString(i);// 将整数转为16进制

				if (hexString.length() == 1) {
					hexString = "0" + hexString;// 如果长度等于1, 加0补位
				}

				sb.append(hexString);
			}

			System.out.println(sb.toString());//打印得到的md5

		} catch (NoSuchAlgorithmException e) {
			// 如果算法不存在的话,就会进入该方法中
			e.printStackTrace();
		}
	}
}
