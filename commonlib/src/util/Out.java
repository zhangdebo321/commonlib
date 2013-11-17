package util;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Out {

	public static void print(Object o) {
		System.out.print(o.toString());
	}

	public static void println(Object o) {
		System.out.println(o.toString());
	}

	public static void print() {
		System.out.println();
	}

	public static PrintStream printf(String format, Object... args) {
		return System.out.printf(format, args);
	}

	/**
	 * 格式化输出一个Collection
	 * @param c 待输出集合
	 */
	public static void cprint(Collection<?> c) {
		System.out.println(cformat(c));
	}

	/**
	 * 格式化输出一个对象数组
	 * @param c 待输出数组
	 */
	public static void cprint(Object[] c) {
		System.out.println(cformat(Arrays.asList(c)));
	}

	/**
	 * 格式化输出一个MAP
	 * @param m 待输出MAP
	 */
	public static void mprint(Map<?, ?> m) {
		System.out.println(mformat(m));
	}

	/**
	 * 以16进制格式化输出一个字节数组
	 * @param data 字节数组
	 */
	public static void bprint(byte[] data) {
		System.out.println(Hex.format(data));
	}

	public static String cformat(Collection<?> c) {
		if (c.size() == 0)
			return "[]";
		StringBuilder result = new StringBuilder("[");
		for (Object elem : c) {
			if (c.size() != 1)
				result.append(Sys.LINE_SEPARATOR + "  ");
			result.append(elem);
		}
		if (c.size() != 1)
			result.append(Sys.LINE_SEPARATOR);
		result.append("]");
		return result.toString();
	}

	public static String mformat(Map<?, ?> m) {
		if (m.size() == 0)
			return "{}";
		StringBuilder result = new StringBuilder("{");
		Set<?> keys = m.keySet();
		for (Object key : keys) {
			if (keys.size() != 1)
				result.append(Sys.LINE_SEPARATOR + "  ");
			result.append(key).append("=").append(m.get(key));
		}
		if (keys.size() != 1)
			result.append(Sys.LINE_SEPARATOR);
		result.append("}");
		return result.toString();
	}

}
