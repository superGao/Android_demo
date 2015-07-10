public class Demo {

	public static void main(String[] args) {
		try {
			ModifyOffset offset = ModifyOffset.getInstance(Demo.class
					.getResourceAsStream("axisoffset.dat"));//加载数据库文件
			PointDouble s2c = offset.s2c(new PointDouble(116.2821962,
					40.0408444));//标准坐标转为火星坐标
			System.out.println(s2c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
