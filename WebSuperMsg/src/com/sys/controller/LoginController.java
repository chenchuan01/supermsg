package com.sys.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sys.base.BaseController;
import com.sys.common.AppExpection;
import com.sys.common.ConfigKeys;
import com.sys.common.util.ConfigUtil;
import com.sys.common.util.SessionUtil;
import com.sys.common.util.StringUtil;
import com.sys.db.entity.User;
import com.sys.db.service.UserService;

/**
 * 登录
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	private static final String LOGINPAGE = "login/login";
	private static final String TO_INDEX = "redirect:/home.do";
	@Resource
	UserService userService;

	@RequestMapping(value = "")
	public String tologin(HttpSession session) throws InterruptedException {
		if (null != SessionUtil.sysUser(session)) {
			return TO_INDEX;
		}
		return LOGINPAGE;
	}

	@RequestMapping(value = "verify")
	public String verifyLogin(Model m, HttpSession session, String userName,
			String password, String code) {
		boolean verifyCodeRslt = verifyCode(session, code);
		if (!verifyCodeRslt) {
			return forwordExpPage(m, new AppExpection(
					"LoginController.verifyLogin(User, Model, HttpSession)",
					"验证码输入错误！"), LOGINPAGE);
		}
		if (StringUtil.isNull(userName) || StringUtil.isNull(password)) {
			return forwordExpPage(m, new AppExpection(
					"LoginController.verifyLogin(User, Model, HttpSession)",
					"用户名或密码为空！"), LOGINPAGE);
		}
		userName = userName.trim();
		password = password.trim();
		User loginUser = userService.userVerify(new User(userName, password));
		if (loginUser == null) {
			return forwordExpPage(m, new AppExpection(
					"LoginController.verifyLogin(User, Model, HttpSession)",
					"用户名或密码错误或未注册！"), LOGINPAGE);
		}

		// 登录验证
		session.setAttribute("sysuser", loginUser);
		return TO_INDEX;
	}

	private boolean verifyCode(HttpSession session, String code) {
		if (ConfigUtil.isConfigSwitchOn(ConfigKeys.VERIFYCODE_SWITCH)) {
			String sessionCode = (String) session.getAttribute("code");
			if (StringUtil.isNull(code) || StringUtil.isNull(sessionCode)) {
				return false;
			}
			if (!sessionCode.equalsIgnoreCase(code)) {
				return false;
			}
		}
		return true;
	}

	@RequestMapping(value = "regist")
	public String registUser(User user, Model m, String code,
			HttpSession session) {
		boolean verifyCodeRslt = verifyCode(session, code);
		if (!verifyCodeRslt) {
			return forwordExpPage(m, new AppExpection(
					"LoginController.registUser(User, Model, HttpSession)",
					"验证码输入错误！"), LOGINPAGE);
		}
		if (user == null || StringUtil.isNull(user.getUserName())
				|| StringUtil.isNull(user.getPassword())) {
			return forwordExpPage(m,
					new AppExpection("LoginController.registUser(User,Model)",
							"注册用户对象为空！请检查输入！"), LOGINPAGE);
		}
		boolean result = userService.userRegist(user) != null;
		if (!result) {
			return forwordExpPage(m, new AppExpection(
					"LoginController.registUser(User,Model)",
					"注册新用户出现异常！请联系管理员！"), LOGINPAGE);
		}
		return forwordExpPage(m, new AppExpection(
				"LoginController.registUser(User,Model)",
				"新用户注册成功！"), LOGINPAGE);
	}

	@RequestMapping(value = "logout")
	public void logOut(HttpSession session, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		if (SessionUtil.sysUser(session) != null) {
			session.invalidate();
		}
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/";
		response.sendRedirect(basePath);
	}
}
