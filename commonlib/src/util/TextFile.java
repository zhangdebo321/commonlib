package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("serial")
public class TextFile extends ArrayList<String> {
	// 程序的行注释正则表达式
	public static String PRO_ANOTA_REG1 = "//[\\s|\\S]*?\\n";

	// 程序的多行注释正则表达式
	public static String PRO_ANOTA_REG2 = "/\\*[\\s|\\S]*?\\*/";

	public static String HTML_ANOTA_REG = "<!--[\\s|\\S]*?-->";

	/**
	 * 从给定文件路径中读取文件的文本内容
	 * @param filePath 文件路径
	 * @return 文件的文本内容
	 */
	public static String read(String filePath) {
		int bufsize = 4 * 1024;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(new File(filePath)), bufsize);
			try {
				String tmp;
				while ((tmp = br.readLine()) != null) {
					sb.append(tmp);
					sb.append(Sys.LINE_SEPARATOR);
				}
			} finally {
				br.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
	
	/**
	 * 读一个文件的文本内容并删除其中的程序性注释//和/*
	 * @param filePath 文件路径
	 * @return 文件的有效文本内容
	 */
	public static String readWithNoProAnnotation(String filePath) {
		return read(filePath).replaceAll(PRO_ANOTA_REG1, Sys.LINE_SEPARATOR)
				.replaceAll(PRO_ANOTA_REG2, "");
	}

	/**
	 * 读一个文件的文本内容并删除其中的html注释<!-- -->
	 * @param filePath 文件的文本路径
	 * @return 文件的有效文本内容
	 */
	public static String readWithNoHtmlAnnotation(String filePath) {
		return read(filePath).replaceAll(HTML_ANOTA_REG, "");
	}

	/**
	 * 读一个文件的文本内容并删除其中的html注释<!-- --> 和 程序注释//和 /*
	 * @param filePath 文件的文本路径
	 * @return 文件的有效文本内容
	 */
	public static String readWithNoAllAnnotation(String filePath) {
		return read(filePath).replaceAll(PRO_ANOTA_REG1, Sys.LINE_SEPARATOR)
				.replaceAll(PRO_ANOTA_REG2, "").replaceAll(HTML_ANOTA_REG, "");
	}

	public static String removeProAnnotation(String content) {
		return content.replaceAll(PRO_ANOTA_REG1, Sys.LINE_SEPARATOR)
				.replaceAll(PRO_ANOTA_REG2, "");
	}

	public static String removeHtmlAnnotation(String content) {
		return content.replaceAll(HTML_ANOTA_REG, "");
	}

	public static String removeAllAnnotation(String content) {
		return removeProAnnotation(removeHtmlAnnotation(content));
	}

	/**
	 * 根据文件路径覆盖写一个文件
	 * @param filePath 文件名
	 * @param content 文件内容
	 * @return 是否写入成功
	 */
	public static boolean write(String filePath, String content) {
		return write(filePath, content, false);
	}

	/**
	 * 根据文件路径向一个文件中写入文本内容
	 * @param filePath 文件路径
	 * @param content 文本内容
	 * @param append 是否追加写
	 * @return 是否写入成功
	 */
	public static boolean write(String filePath, String content, boolean append) {
		File file = new File(filePath);
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					file, append)));
			try {
				pw.print(content);
			} finally {
				pw.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	/**
	 * 用文件名和分隔符构造一个TextFile,文本内容将会被分隔符(正则表达式)分割
	 * @param fileName 文本路径
	 * @param splitter 正则表达式分隔符
	 */
	public TextFile(String fileName, String splitter) {
		super(Arrays.asList(read(fileName).split(splitter)));
		//正则表达式分割的第一个字符串可能为空
		if (get(0).equals(""))
			remove(0);
	}

	/**
	 * 用文件名和换行符构造一个TextFile
	 * @param fileName
	 */
	public TextFile(String fileName) {
		this(fileName, Sys.LINE_SEPARATOR);
	}

	/**
	 * 将该文件的文本内容写入到一个新文件中，相当于复制文本文件
	 * @param fileName 待写入的文件名
	 */
	public void write(String fileName) {
		try {
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			try {
				for (String item : this)
					out.println(item);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// System.out.println(System.getProperty("line.separator"));

		/**/
		// String path = "E:\\ebank\\lianaCoreDoc\\JavaSource";

		System.out.println(readWithNoHtmlAnnotation("D:\\2.htm"));
		/**/

	}

}
