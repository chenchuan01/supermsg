package com.sys.common.util;


/**
 * @author chenchuan
 * @date 2016年1月25日
 * 字符串处理工具类
 * StringUtil.java
 */
public class StringUtil {
	
	/**
	 * 字符串判空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		if(null!=str){
			str = str.trim();
		}
		return str==null||"".equalsIgnoreCase(str);
	}
	/**
	 * 字符串判断非空
	 * @param str
	 * @return
	 */
	public static boolean isNotNull(String str){
		if(null!=str){
			str = str.trim();
		}
		return str!=null&&!"".equalsIgnoreCase(str);
	}
	
}
