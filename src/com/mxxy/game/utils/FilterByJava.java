package com.mxxy.game.utils;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 文件后缀过滤器
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class FilterByJava implements FilenameFilter {
	/**
	 * ext为需要过滤的条件，比如如果ext =".jpg"，则只能返回后缀为jpg的文件
	 */
	private String ext;

	public FilterByJava(String ext) {
		this.ext = ext;
	}

	public boolean accept(File dir, String name) { // 返回true的文件则合格
		return name.endsWith(ext);
	}
}