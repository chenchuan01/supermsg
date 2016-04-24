package com.sys.common.util;

import java.util.List;
import java.util.Map;

/**
 *
 *@author chenchuan
 *@date 2016年2月16日
 *AuthUtil.java
 */
public class AuthUtil {
	
	/***************角色类型********************/
	/**
	 * 超级管理员
	 */
	public static final int AU_SUPER=0;
	/**
	 * 管理员
	 */
	public static final int AU_ADMIN=1;
	/**
	 * 普通用户
	 */
	public static final int AU_USER=2;
	
	/***************操作类型********************/
	/**
	 * 系统操作
	 */
	public static final String OP_SYS="sys";
	/**
	 * 前台特有
	 */
	public static final String OP_FRONT="front";
	
	public static final String OP_ADMIN="admin";

	/**
	 * 判断是否具有操作权限
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
