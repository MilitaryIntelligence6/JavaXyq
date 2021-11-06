package com.mxxy.game.utils;

import com.mxxy.game.resources.Constant;
import com.mxxy.game.resources.Constant.EPlatform;

/**
 * 当前系统判断
 * 
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class OSinfo {

	private static String OS = System.getProperty("os.name").toLowerCase();

	private static OSinfo _instance = new OSinfo();

	private EPlatform platform;

	private OSinfo() {
	}

	public static boolean isLinux() {
		return OS.indexOf("linux") >= 0;
	}

	public static boolean isMacOS() {
		return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0 && OS.indexOf("x") < 0;
	}

	public static boolean isMacOSX() {
		return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0 && OS.indexOf("x") > 0;
	}

	public static boolean isWindows() {
		return OS.indexOf("windows") >= 0;
	}

	public static boolean isSunOS() {
		return OS.indexOf("sunos") >= 0;
	}

	/**
	 * 获取操作系统名字
	 * 
	 * @return 操作系统名
	 */
	public static EPlatform getOSname() {
		if (isLinux()) {
			_instance.platform = EPlatform.Linux;
		} else if (isMacOS()) {
			_instance.platform = EPlatform.Mac_OS;
		} else if (isMacOSX()) {
			_instance.platform = EPlatform.Mac_OS_X;
		} else if (isSunOS()) {
			_instance.platform = EPlatform.SunOS;
		} else if (isWindows()) {
			_instance.platform = EPlatform.Windows;
		}
		return _instance.platform;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Constant.currentDir.charAt(Constant.currentDir.length() - 1));
		System.out.println(OSinfo.getOSname());
	}
}