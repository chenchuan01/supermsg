package com.sys.common.util;

import java.util.ArrayList;
import java.util.List;

import com.SpringContextHolder;
import com.sys.common.Params;
import com.sys.db.entity.Roles;

/**
 *@author chenchuan
 *@date 2016��3��5��
 *RolesUtil.java
 */
public class RolesUtil {
	private static List<Roles> sysRoles;

	/**
	 * ˢ�²���������Ȩ��
	 * @return
	 */
	private static List<Roles> getSysRoles() {
		if(!CommonUtil.isListNotNull(sysRoles)){
			sysRoles = genSysUserRoles();
		}
		return sysRoles;
	}
	/**
	 * ���ϵͳ���ý�ɫ
	 * @return
	 */
	public static List<Roles> genSysUserRoles(){
		List<Roles> roles = new ArrayList<Roles>();
		Params sysParams = (Params)SpringContextHolder.getBean("params");
		String sysRoles = sysParams.getSys_user_roles();
		String[] roleItems = sysRoles.split(ConfigUtil.VALUE_SPLIT);
		for (String role : roleItems) {
			String[] fields = role.split(ConfigUtil.K_V_SPLIT);
			roles.add(new Roles(Integer.valueOf(fields[0]), fields[1]));
		}
		return roles;
	}
	/**
	 * ��ó���Ȩ��
	 * @return
	 */
	public static Roles juniorRole(){
		List<Roles> list = getSysRoles();
		return list.get(list.size()-1);
	}
}
