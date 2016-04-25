package com.supermsg.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.base.BaseController;
import com.sys.common.AppExpection;
import com.sys.common.util.CommonUtil;
import com.sys.common.util.ConfigUtil;
import com.sys.db.entity.User;
import com.sys.db.service.UserService;

/**
 *ChatController.java
 */
@Controller
@RequestMapping("/chat")
public class ChatController extends BaseController{
	@Resource
	UserService userService;
	
	Set<Map<String, User>> chatSet;
	public ChatController() {
		getChatSet();
	}
	/**
	 * 选择好友，开始聊天
	 * @param host
	 * @param friend_id
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping("/startChat")
	public @ResponseBody
	Map<String,User> startChat(Integer host, Integer friend_id) throws AppExpection {
		Map<String, User> chatMap = new HashMap<String, User>();
		User mine = userService.findById(host);
		User friend = new User();
		if (CommonUtil.isContains(mine.getFriends_id(), friend_id + "",
				ConfigUtil.VALUE_SPLIT)) {
			friend=userService.findById(friend_id);
		}
		chatMap.put("me", mine);
		chatMap.put("he", friend);
		return chatMap;
	}
	
	public Set<Map<String, User>> getChatSet(){
		if(null==chatSet){
			chatSet = new HashSet<Map<String, User>>();
		}
		return chatSet;
	}
}
