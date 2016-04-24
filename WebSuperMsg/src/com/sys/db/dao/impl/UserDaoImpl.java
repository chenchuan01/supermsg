package com.sys.db.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sys.base.impl.BaseDaoImpl;
import com.sys.common.util.MD5Util;
import com.sys.common.util.RolesUtil;
import com.sys.db.dao.UserDao;
import com.sys.db.entity.User;

/**
 * UserDaoImpl.java
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public User verify(User user) {
		encryption(user);
		List<User> users =find(user);
		return users==null||users.isEmpty()?null:users.get(0);
	}

	public User regist(User user) {
		encryption(user);
		//设置默认角色
		if(null==user.getRoles()){
			user.setRoles(RolesUtil.juniorRole().getIndex());
		}
		return save(user).get(0);
	}
	/**
	 * 用户密码加密
	 * @param user
	 */
	public void encryption(User user) {
		String pwd = user.getPassword();
		user.setPassword(MD5Util.MD5(pwd));
	}
}
