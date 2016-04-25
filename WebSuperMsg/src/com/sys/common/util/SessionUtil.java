package com.sys.common.util;

import javax.servlet.http.HttpSession;

import com.SpringContextHolder;
import com.sys.SysConstants;
import com.sys.db.entity.User;
import com.sys.db.service.UserService;

/**
 *@author chenchuan
 *@date 2016Äê1ÔÂ31ÈÕ
 *SessionUtil.java
 */
public class SessionUtil {
	public static User sysUser(HttpSession session){
		UserService userSvc = SpringContextHolder.getBean("userService");
		User sysUser = (User)session.getAttribute(SysConstants.SESSION_USER);
		if(null!=sysUser){
			sysUser = userSvc.findById(sysUser.getId());
			session.setAttribute(SysConstants.SESSION_USER, sysUser);
		}
		
		return sysUser;
	}
}
