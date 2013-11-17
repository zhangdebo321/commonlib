package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.mozilla.universalchardet.UniversalDetector;

/**
 * 注意:<br>
 * (1)测试发现ANSI编码识别不了<br>
 * (2)无BOM且无汉字的文本UTF-8识别不了
 * @author zhang
 *
 */
public class CharsetDetector {
	/**
	 * 检测文件的编码集
	 * @param filePath 文件路径
	 * @return 文件的编码集，失败返回null
	 * @throws IOException
	 */
	public static String detect(String filePath) throws IOException{
		return detect(new FileInputStream(filePath));
	}
	
	/**
	 * 检测文件的编码集
	 * @param file 文件
	 * @return 文件的编码集，失败返回null
	 * @throws IOException
	 */
	public static String detect(File file) throws IOException{
		return detect(new FileInputStream(file));
	}

	/**
	 * 根据文件输入流， 检测文件的编码集
	 * @param inputStream 文件输入流
	 * @return 文件的编码集，失败返回null
	 * @throws IOException
	 */
	public static String detect(FileInputStream inputStream) throws IOException {
		String charset = null;
		byte[] buf = new byte[4096];
		UniversalDetector detector = new UniversalDetector(null);
		int nread;
		while ((nread = inputStream.read(buf)) > 0 && !detector.isDone()) {
			detector.handleData(buf, 0, nread);
		}
		detector.dataEnd();
		String encoding = detector.getDetectedCharset();
		if (encoding != null)
			charset = encoding;
		detector.reset();
		return charset;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.print(detect("D:/1.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
