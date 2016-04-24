package com.sys.common.util;

import java.util.List;
import java.util.Map;

/**
 *
 *@author chenchuan
 *@date 2016��2��16��
 *AuthUtil.java
 */
public class AuthUtil {
	
	/***************��ɫ����********************/
	/**
	 * ��������Ա
	 */
	public static final int AU_SUPER=0;
	/**
	 * ����Ա
	 */
	public static final int AU_ADMIN=1;
	/**
	 * ��ͨ�û�
	 */
	public static final int AU_USER=2;
	
	/***************��������********************/
	/**
	 * ϵͳ����
	 */
	public static final String OP_SYS="sys";
	/**
	 * ǰ̨����
	 */
	public static final String OP_FRONT="front";
	
	public static final String OP_ADMIN="admin";

	/**
	 * �ж��Ƿ���в���Ȩ��
	 * @param roles
	 * @param opreat
	 * @return
	 */
	public static boolean isAuth(int roles, String opreat) {
//		if(0==roles){
//			return true;
//		}
		Map<Integer, List<String>>  authMap = ConfigUtil.authConfMap();
		List<String> auths = authMap.get(roles);
		for (String auth : auths) {
			if(opreat.equalsIgnoreCase(auth)){
				return true;
			}
		}
		return false;
	}

}
