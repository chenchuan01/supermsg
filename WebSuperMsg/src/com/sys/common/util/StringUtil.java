package com.sys.common.util;


/**
 * @author chenchuan
 * @date 2016��1��25��
 * �ַ�����������
 * StringUtil.java
 */
public class StringUtil {
	
	/**
	 * �ַ����п�
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
	 * �ַ����жϷǿ�
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
