package Test;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Text {

	public static void main(String[] args) throws Exception {

		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new File("./layout/APPELLATION_UI.xml"));
		// 获取根元素
		Element root = document.getRootElement();
		System.out.println("Root: " + root.getName());

		// 获取所有子元素
		List<Element> childList = root.elements();
		System.out.println("total child count: " + childList.size());

		// 获取特定名称的子元素
		List<Element> childList2 = root.elements("ImageComPonent");
		System.out.println("hello child: " + childList2.size());

		// // 获取名字为指定名称的第一个子元素
		// Element firstWorldElement = root.element("world");
		// // 输出其属性
		// System.out.println("first World Attr: "
		// + firstWorldElement.attribute(0).getName() + "="
		// + firstWorldElement.attributeValue("name"));

		System.out.println("迭代输出-----------------------");

		for (Element element : childList2) {
			System.out.println(element.attributeValue("x"));
		}

		for (Iterator iter = root.elementIterator(); iter.hasNext();) {
			Element e = (Element) iter.next();
			System.out.println(e.attributeValue("x") + ">>>" + e.attributeValue("y"));
		}

	}

	public static String string2Unicode(String string) {

		StringBuffer unicode = new StringBuffer();

		for (int i = 0; i < string.length(); i++) {

			// 取出每一个字符
			char c = string.charAt(i);

			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}

		return unicode.toString();
	}

	/**
	 * unicode 转字符串
	 */
	public static String unicode2String(String unicode) {

		StringBuffer string = new StringBuffer();

		String[] hex = unicode.split("\\\\u");

		for (int i = 1; i < hex.length; i++) {

			// 转换出每一个代码点
			int data = Integer.parseInt(hex[i], 16);

			// 追加成string
			string.append((char) data);
		}

		return string.toString();
	}
}
