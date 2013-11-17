package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XmlFile {
	/**
	 * 生成一个空的Document
	 * @return
	 */
	public static Document genDocument(){		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		Document doc = builder.newDocument();
		doc.setXmlStandalone(true);	
		return doc;
	}
	/**
	 * 将指定Document写到文件中
	 * @param doc XML文档
	 * @param outPath 输出路径
	 * @param encoding 编码
	 */
	public void write(Document doc, String outPath, String encoding){
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(outPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OutputStreamWriter writer = new OutputStreamWriter(fos);
		Source source = new DOMSource(doc);
		Result result = new StreamResult(writer);
		Transformer former;
		try {
			former = TransformerFactory.newInstance().newTransformer();
			former.setOutputProperty(OutputKeys.ENCODING, encoding);
			former.setOutputProperty(OutputKeys.INDENT, "yes");
			former.transform(source, result);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(writer != null)
					writer.close();
				if(fos != null)
					fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}		
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
