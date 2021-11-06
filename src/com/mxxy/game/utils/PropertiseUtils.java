package com.mxxy.game.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * 操作Propertise
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class PropertiseUtils {

	public static boolean loadCheckUser(String user, String password) {
		Properties properties = new Properties();
		File file = new File("User.properties");
		loadPro(properties, file);
		for (Entry<Object, Object> entrySet : properties.entrySet()) {
			if (entrySet.getKey().equals(user) && entrySet.getValue().equals(password)) {
				return true;
			}
		}
		return false;
	}

	// Properties加载文件信息
	public static void loadPro(Properties pro, File file) {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			pro.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
