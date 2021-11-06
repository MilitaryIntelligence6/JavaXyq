package com.mxxy.game.xml;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mxxy.game.utils.PanelManager;

/**
 * XML解析
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class XMLoader {

	SAXReader saxReader;

	public XMLoader() {
		saxReader = new SAXReader();
	}

	public void loadUI(String fileName) throws DocumentException {
		System.out.println("loadUI>>" + fileName);
		Document document = saxReader.read(new File(fileName));
		Element rootElement = document.getRootElement();
		if (rootElement.getName().equals("panel")) {
			PanelManager.putFileNameMap(rootElement.attributeValue(rootElement.attribute(0).getName()), fileName);
		}
	}
}
