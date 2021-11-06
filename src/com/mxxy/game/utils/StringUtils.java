package com.mxxy.game.utils;

import java.util.StringTokenizer;

/**
 * 字符串操作
 * @author ZAB 邮箱 ：624284779@qq.com
 */
public class StringUtils {

	public static final String EMPTY = "";

	/**
	 * 获取自定字符后面的字符串 StringUtils.afterLast("akcb","k") =cb
	 * 
	 * @param str
	 * @param delimiter
	 * @return
	 */
	public static String afterLast(String str, String delimiter) {
		int p = str.lastIndexOf(delimiter);
		return (p >= 0 && p < str.length() - 1) ? str.substring(p + delimiter.length()) : null;
	}

	/**
	 * 将指定字符,插入到字符串中间
	 * 
	 * @param str
	 * @param open
	 * @param close
	 * @return
	 */
	public static String substringBetween(String str, String open, String close) {
		if (str == null || open == null || close == null) {
			return null;
		}
		int start = str.indexOf(open);
		if (start != -1) {
			int end = str.indexOf(close, start + open.length());
			if (end != -1) {
				return str.substring(start + open.length(), end);
			}
		}
		return null;
	}

	/**
	 * 比较两个字符串 StringUtils.equals("s","s") =true; StringUtils.equals("s","q")
	 * =false;
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equals(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
	}

	/**
	 * StringUtils.isBlank("") =true; StringUtils.isBlank("f")=false;
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * 判断字符串是否有值
	 * </p>
	 * StringUtils.isNotBlank(null) = false StringUtils.isNotBlank("") = false
	 * StringUtils.isNotBlank(" ") = false StringUtils.isNotBlank("bob") = true
	 * StringUtils.isNotBlank(" bob ") = true
	 * </pre>
	 */
	public static boolean isNotBlank(String str) {
		return !StringUtils.isBlank(str);
	}

	/**
	 * 判断字符串中是否有汉字 StringUtils.isEnglishAndNumeric("123sdsd")=true
	 * StringUtils.isEnglishAndNumeric("123你") =flase
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isEnglishAndNumeric(String string) {
		if (string == null || string.length() == 0) {
			return false;
		}
		char[] chars = string.toCharArray();
		int size = chars.length;
		for (int j = 0; j < size; j++) {
			char letter = chars[j];
			if ((97 > letter || letter > 122) && (65 > letter || letter > 90) && (48 > letter || letter > 57)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 截取字符串 StringUtils.splits("z:k",":") string[0]=z,string[1]=k;
	 * 
	 * @param string
	 * @param tag
	 * @return
	 */
	public static String[] splits(final String string, final String tag) {
		StringTokenizer str = new StringTokenizer(string, tag);
		String[] result = new String[str.countTokens()];
		int index = 0;
		for (; str.hasMoreTokens();) {
			result[index++] = str.nextToken();
		}
		return result;
	}

	/**
	 * 替换指定字符 StringUtils.replace("bbba", "bbb", "a")=aa
	 * 
	 * @param string
	 *            指定字符串
	 * @param oldString
	 *            需要替换的字符
	 * @param newString
	 *            替换成的字符
	 * @return
	 */
	public static final String replace(String string, String oldString, String newString) {
		if (string == null)
			return null;
		if (newString == null)
			return string;
		int i = 0;
		if ((i = string.indexOf(oldString, i)) >= 0) {
			char string2[] = string.toCharArray();
			char newString2[] = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(string2.length);
			buf.append(string2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = string.indexOf(oldString, i)) > 0; j = i) {
				buf.append(string2, j, i - j).append(newString2);
				i += oLength;
			}
			buf.append(string2, j, string2.length - j);
			return buf.toString();
		} else {
			return string;
		}
	}

	/**
	 * 过滤指定字符 String[] parseString = StringUtils.parseString("你H我",'H'); string[0]=你
	 * string[1]=我
	 * 
	 * @param text
	 * @return
	 */
	public static String[] parseString(String text, char c) {
		int token, index, index2;
		token = index = index2 = 0;
		while ((index = text.indexOf(c, index)) != -1) {
			token++;
			index++;
		}
		token++;
		index = 0;
		String[] document = new String[token];
		for (int i = 0; i < token; i++) {
			index2 = text.indexOf(c, index);
			if (index2 == -1) {
				index2 = text.length();
			}
			document[i] = text.substring(index, index2);
			index = index2 + 1;
		}

		return document;
	}

	/**
	 * 检查一组字符串是否完全由中文组成
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isChinaLanguage(String str) {
		char[] chars = str.toCharArray();
		int[] ints = new int[2];
		boolean isChinese = false;
		int length = chars.length;
		byte[] bytes = null;
		for (int i = 0; i < length; i++) {
			bytes = ("" + chars[i]).getBytes();
			if (bytes.length == 2) {
				ints[0] = bytes[0] & 0xff;
				ints[1] = bytes[1] & 0xff;
				if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40 && ints[1] <= 0xFE) {
					isChinese = true;
				}
			} else {
				return false;
			}
		}
		return isChinese;
	}

	/**
	 * 检查是否为纯字母 StringUtils.isAlphabet("aaa") =true;
	 * StringUtils.isAlphabet("aaa1s")=false;
	 * 
	 * @param value
	 * @return
	 */
	public final static boolean isAlphabet(String value) {
		if (value == null || value.length() == 0)
			return false;
		for (int i = 0; i < value.length(); i++) {
			char c = Character.toUpperCase(value.charAt(i));
			if ('A' <= c && c <= 'Z')
				return true;
		}
		return false;
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

	/**
	 * 插入字符
	 * 
	 * @param s1
	 * @param s2
	 * @param l
	 * @return
	 */
	public static String insertString(String s1, String s2, int index) {
		StringBuilder sb = new StringBuilder();
		sb.append(s1).insert(index, s2);
		return sb.toString();
	}
	
	/** 
	 * @param min 指定范围最小值 
	 * @param max 指定范围最大值 
	 * @param n 随机数个数 
	 */ 
	public static int[] randomCommon(int min, int max, int n){  
	    if (n > (max - min + 1) || max < min) {  
	           return null;  
	       }  
	    int[] result = new int[n];  
	    int count = 0;  
	    while(count < n) {  
	        int num = (int) (Math.random() * (max - min)) + min;  
	        boolean flag = true;  
	        for (int j = 0; j < n; j++) {  
	            if(num == result[j]){  
	                flag = false;  
	                break;  
	            }  
	        }  
	        if(flag){  
	            result[count] = num;  
	            count++;  
	        }  
	    }  
	    return result;  
	}
	
	/**
     * 比较版本号  1.0.1   1.0.2
     * 
     * @param version1 
     * @param version2
     * @return
     * @throws Exception 负数则  version1<version2  相反
     */
    public static int compareVersion(String version1, String version2) {
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }
	
	
}
