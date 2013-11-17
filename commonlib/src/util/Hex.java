package util;

public class Hex {
	
	/**
	 * 以16进制格式化一个字节数组
	 * @param data 字节数组
	 * @return
	 */
	public static String format(byte[] data) {
		StringBuilder result = new StringBuilder();
		int n = 0;
		for (byte b : data) {
			if (n % 16 == 0)
				result.append(String.format("%05X: ", n));
			result.append(String.format("%02X ", b));
			n++;
			if (n % 16 == 0)
				result.append(Sys.LINE_SEPARATOR);
		}
		result.append(Sys.LINE_SEPARATOR);
		return result.toString();
	}
	
	/**
	 * 将一个字节数组转化为等值16进制的字符串
	 * @param data 字节数组
	 * @return
	 */
	public static String toString(byte[] data){
		StringBuilder result = new StringBuilder();
		for(byte b : data){
			result.append(String.format("%02X", b));
		}
		return result.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Hex.toString(Sys.LINE_SEPARATOR.getBytes()));
		System.out.println(Hex.toString("\n".getBytes()));

	}

}
