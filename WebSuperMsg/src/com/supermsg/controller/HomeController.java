package com.supermsg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *HomeController.java
 */
@Controller
@RequestMapping("/home")
public class HomeController {
	@RequestMapping("")
	public String home(){
		return "home";
	}
	@RequestMapping("/addFriend")
	public String addFriend(Integer user_id){
		return "friend/add";
	}
	@RequestMapping("/myFriends")
	public String myFriends(Integer user_id){
		return "friend/list";
	}
	@RequestMapping("/selectChat")
	public String selectChat(Integer user_id){
		return "chat/list";
	}
}
