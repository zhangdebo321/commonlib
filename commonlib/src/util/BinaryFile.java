package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryFile {
	
	/**
	 * 以文件输入流的方式读取2进制文件的数据字节
	 * @param filePath 需读取的文件路径
	 * @return 文件的数据字节
	 * @throws IOException
	 */
	public static byte[] read(String filePath) throws IOException{
		return read(new File(filePath));
	}
	
	/**
	 * 以文件输入流的方式读取2进制文件的数据字节
	 * @param file 需读取的文件
	 * @return 文件的数据字节
	 * @throws IOException
	 */
	public static byte[] read(File file) throws IOException{
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		try{
			byte[] data = new byte[bis.available()];
			bis.read(data);
			return data;
		}finally{
			bis.close();
		}
	}
	
	/**
	 * 以文件输出流的方式向文件写入2进制数据字节
	 * @param filePath 写入文件路径
	 * @param data 写入数据字节
	 * @throws IOException
	 */
	public static void write(String filePath, byte[] data) throws IOException{
		write(new File(filePath), data);
	}
	
	/**
	 * 以文件输出流的方式向文件写入2进制数据字节
	 * @param file 写入文件
	 * @param data 写入数据字节
	 * @throws IOException
	 */
	public static void write(File file, byte[] data) throws IOException{
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		try{
			bos.write(data);
			bos.flush();
		}finally{
			bos.close();
		}
	}
	
	/**
	 * 从数据字节的offset便宜位置处向文件写入2进制数据字节
	 * @param filePath 写入文件路径
	 * @param data 写入数据
	 * @param offset 写入数据的偏移量
	 * @throws IOException
	 */
	public static void write(String filePath, byte[] data, int offset) throws IOException{
		write(new File(filePath), data, offset);
	}
	
	/**
	 * 从数据字节的offset便宜位置处向文件写入2进制数据字节
	 * @param file 写入文件
	 * @param data 写入数据
	 * @param offset 写入数据的偏移量
	 * @throws IOException
	 */
	public static void write(File file, byte[] data, int offset) throws IOException{
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		try{
			bos.write(data, offset, data.length - offset);
			bos.flush();
		}finally{
			bos.close();
		}
	}


	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		byte[] data = read("d:/1.txt");
		System.out.println(data[0]);
		System.out.println(data[1]);
		System.out.println(data[2]);
		System.out.println(Integer.toHexString(data[0] & 0xFF).toUpperCase());
		util.Out.bprint(data);

	}

}
