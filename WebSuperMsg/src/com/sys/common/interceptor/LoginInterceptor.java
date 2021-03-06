package com.sys.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sys.SysConstants;
import com.sys.common.util.LogUtil;
import com.sys.db.entity.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final String[] IGNORE_URI = { "login", "regist","verify","code","js","css","fonts","icon","images","theme"};

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean flag = false;
		String url = request.getRequestURL().toString();
		for (String s : IGNORE_URI) {
			if (url.contains(s)) {
				flag = true;
				break;
			}
		}
		if(!flag){
			LogUtil.infoReq(this.getClass(), url);
			User user = (User) request.getSession().getAttribute(SysConstants.SESSION_USER);
			if (user != null){
				flag = true;
				
			}else{
				response.sendRedirect(request.getContextPath()+"/login/");
				flag = false;
			}
		}
		return flag;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}
