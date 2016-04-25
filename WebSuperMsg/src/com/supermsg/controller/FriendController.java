package com.supermsg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.base.dto.PageResult;
import com.sys.base.dto.QueryParam;
import com.sys.common.AppExpection;
import com.sys.common.util.CommonUtil;
import com.sys.common.util.ConfigUtil;
import com.sys.common.util.SessionUtil;
import com.sys.common.util.StringUtil;
import com.sys.db.DBConstants;
import com.sys.db.entity.User;
import com.sys.db.service.UserService;

/**
 * HomeController.java
 */
@Controller
@RequestMapping("/friend")
public class FriendController {
	@Resource
	UserService userService;

	/**
	 * 移除好友
	 * 
	 * @param host
	 * @param friend_id
	 * @return
	 * @throws AppExpection 
	 */
	@RequestMapping("/delete")
	public @ResponseBody
	User delete(Integer host, Integer friend_id) throws AppExpection {
		User mine = userService.findById(host);
		if (CommonUtil.isContains(mine.getFriends_id(), friend_id + "",
				ConfigUtil.VALUE_SPLIT)) {
			userService.removeFriend(mine,friend_id);
		}
		return mine;
	}
	/**
	 * 添加好友
	 * 
	 * @param host
	 * @param friend_id
	 * @return
	 * @throws AppExpection 
	 */
	@RequestMapping("/append")
	public @ResponseBody
	User append(Integer host, Integer friend_id) throws AppExpection {
		User mine = userService.findById(host);
		if (!CommonUtil.isContains(mine.getFriends_id(), friend_id + "",
				ConfigUtil.VALUE_SPLIT)) {
			userService.appendFriend(mine,friend_id);
		}
		return mine;
	}

	/**
	 * 好友查询
	 * 
	 * @param params
	 * @param user
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "friendPage")
	public @ResponseBody
	PageResult<User> friendPage(QueryParam<User> params, User user,Integer myFlag,
			HttpSession session) throws AppExpection {
		if (StringUtil.isNotNull(user.getName())) {
			user.setName(DBConstants.CHAR_LIKE + user.getName()
					+ DBConstants.CHAR_LIKE);
		}
		params.setParam(user);
		PageResult<User> result = userService.pageQuery(params);
		User mine = SessionUtil.sysUser(session);
		List<User> filterFds = filterFds(result, mine,myFlag);
		result.setContent(filterFds);
		return result;
	}

	/**
	 * 过滤自己和自己的好友
	 * 
	 * @param result
	 * @param mine
	 * @param myFlag 
	 * @return
	 */
	private List<User> filterFds(PageResult<User> result, User mine, Integer myFlag) {
		List<User> filterFds = new ArrayList<User>();
		if(myFlag!=null&&myFlag!=0){
			for (User temp : result.getContent()) {
				String friends = mine.getFriends_id();
				if (temp.getId() != mine.getId()
						&&CommonUtil.isContains(friends, temp.getId() + "",
								ConfigUtil.VALUE_SPLIT)) {
					filterFds.add(temp);
				}
			}
		}else{
			for (User temp : result.getContent()) {
				String friends = mine.getFriends_id();
				if (temp.getId() != mine.getId()
						&&!CommonUtil.isContains(friends, temp.getId() + "",
								ConfigUtil.VALUE_SPLIT)) {
					filterFds.add(temp);
				}
			}
		}
		
		return filterFds;
	}
}
