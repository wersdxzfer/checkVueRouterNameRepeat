package checkVueRouterNameRepeat;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ConfigUtils {
	static final String[] read() {
		String[] r = null;
		File currentDir = new File("config.xml");
		System.out.println(currentDir.getAbsolutePath());
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(currentDir);
			NodeList nodeList = document.getElementsByTagName("folder");
			int length = nodeList.getLength();
			r = new String[length];
			for (int i = 0; i < length; i++) {
				Node node = nodeList.item(i);
				String text = node.getTextContent();
				r[i] = text;
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	
}
