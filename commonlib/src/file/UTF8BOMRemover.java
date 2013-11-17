package file;

import java.io.File;
import java.io.IOException;

import util.BinaryFile;

/**
 * BOM是为了表明文件的编码方式，除此之外，没有其他用处。<br>
 * 在java的许多文件读取中，如果含有BOM则会造成文件读取错误，所以可考虑去除BOM.<br>
 * BOM即文件的前三个字节，其值为16进制的0XEF BB BF.<br>
 * @author zhang
 *
 */
public class UTF8BOMRemover {
	/**
	 * 移除以UTF-8编码的文件的BOM
	 * @param filePath 待移除BOM的文件路径
	 * @throws IOException
	 */
	public static void remove(String filePath) throws IOException{
		remove(new File(filePath));
	}
	/**
	 * 移除以UTF-8编码的文件的BOM
	 * @param file 待移除BOM的文件
	 * @throws IOException
	 */
	public static void remove(File file) throws IOException{
		byte[] data = BinaryFile.read(file);
		if(data.length < 3)
			return;
		//对于byte,EF=-17, BB=-69, BF=-65
		if(data[0] ==  -17 && data[1] == -69 && data[2] == -65){
			BinaryFile.write(file, data, 3);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "d:/1.txt";
		try {
			remove(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
