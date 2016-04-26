package com.supermsg.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.supermsg.dto.Chat;
import com.supermsg.websocket.SystemWebSocketHandler;
import com.sys.base.BaseController;
import com.sys.common.AppExpection;
import com.sys.common.util.CommonUtil;
import com.sys.common.util.ConfigUtil;
import com.sys.common.util.JsonUtil;
import com.sys.common.util.SessionUtil;
import com.sys.db.entity.User;
import com.sys.db.service.UserService;

/**
 * ChatController.java
 */
@Controller
@RequestMapping("/chat")
public class ChatController extends BaseController {
	@Resource
	UserService userService;

	@Bean
	public SystemWebSocketHandler systemWebSocketHandler() {
		return new SystemWebSocketHandler();
	}

	Set<Map<String, User>> chatSet;

	public ChatController() {
		getChatSet();
	}

	/**
	 * ѡ����ѣ���ʼ����
	 * 
	 * @param host
	 * @param friend_id
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping("/startChat")
	public @ResponseBody
	Map<String, User> startChat(Integer host, Integer friend_id)
			throws AppExpection {
		Map<String, User> chatMap = new HashMap<String, User>();
		User mine = userService.findById(host);
		User friend = new User();
		if (CommonUtil.isContains(mine.getFriends_id(), friend_id + "",
				ConfigUtil.VALUE_SPLIT)) {
			friend = userService.findById(friend_id);
		}
		chatMap.put("me", mine);
		chatMap.put("he", friend);
		return chatMap;
	}

	@RequestMapping("/chating")
	public @ResponseBody
	User chating(Chat chat,HttpSession session) {
		String msg = JsonUtil.toJson(chat);
		systemWebSocketHandler().sendMessageToUser(chat.getToName(),
				new TextMessage(msg));
		return SessionUtil.sysUser(session);
	}

	public Set<Map<String, User>> getChatSet() {
		if (null == chatSet) {
			chatSet = new HashSet<Map<String, User>>();
		}
		return chatSet;
	}
}
