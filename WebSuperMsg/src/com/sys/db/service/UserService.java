package com.sys.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sys.base.BaseService;
import com.sys.common.AppExpection;
import com.sys.common.util.ConfigUtil;
import com.sys.common.util.StringUtil;
import com.sys.db.dao.UserDao;
import com.sys.db.entity.User;

/**
 * @author chenchuan
 * @date 2016年1月22日 UserService.java
 */
@Component
public class UserService extends BaseService<User> {
	@Autowired
	UserDao userDao;

	/**
	 * 登录验证
	 * 
	 * @param user
	 * @return
	 */
	public User userVerify(User user) {
		return userDao.verify(user);
	}

	/**
	 * 更新
	 * 
	 * @param user
	 * @return
	 * @throws AppExpection
	 */
	public User userUpdate(User user) throws AppExpection {
		if (StringUtil.isNotNull(user.getPassword())) {
			userDao.encryption(user);
		}
		userDao.update(user);
		return user;
	}

	/**
	 * 添加新用户
	 * 
	 * @param user
	 */
	public User userRegist(User user) {
		return userDao.regist(user);
	}

	/**
	 * 添加好友
	 * 
	 * @param friend_id
	 * @throws AppExpection
	 */
	public void appendFriend(User mine, Integer friend_id) throws AppExpection {
		User friend = findById(friend_id);
		String hadMyFriends = StringUtil.isNotNull(mine.getFriends_id()) ? mine
				.getFriends_id() : "";
		StringBuilder my_frineds = new StringBuilder(hadMyFriends);
		if (friend.notNull()) {
			my_frineds.append(friend.getId());
			my_frineds.append(ConfigUtil.VALUE_SPLIT);
		}
		mine.setFriends_id(my_frineds.toString());
		updateEntity(mine);

	}

	/**
	 * 移除好友
	 * @param mine
	 * @param friend_id
	 * @throws AppExpection 
	 */
	public void removeFriend(User mine, Integer friend_id) throws AppExpection {
		User friend = findById(friend_id);
		String hadMyFriends = StringUtil.isNotNull(mine.getFriends_id()) ? mine
				.getFriends_id() : "";
		if(friend.notNull()&&
				StringUtil.isNotNull(hadMyFriends)){
			StringBuilder my_new_frineds = new StringBuilder();
			for (String tempId : hadMyFriends.split(ConfigUtil.VALUE_SPLIT)) {
				if(friend_id!=Integer.valueOf(tempId)){
					my_new_frineds.append(tempId);
					my_new_frineds.append(ConfigUtil.VALUE_SPLIT);
				}
			}
			mine.setFriends_id(my_new_frineds.toString());
			updateEntity(mine);
		}
		
	}

}
