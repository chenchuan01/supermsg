package com.sys.common.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import com.sys.common.util.AuthUtil;

/**
 * @author chenchuan
 * @date 2016年2月16日
 * AuthTag.java
 * 前台菜单展示权限
 */
public class AuthTag extends TagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer roles;
	private String opreat;
	@Override
	public int doStartTag() throws JspException {
		String opreat = getOpreat();
		int roles = getRoles();
		boolean auth = false;
		if(AuthUtil.isAuth(roles,opreat)){
			auth = true;
		}
		if(auth){
			return Tag.EVAL_BODY_INCLUDE;			
		}else{
			return Tag.SKIP_BODY;
		}
	}
	public Integer getRoles() {
		return roles;
	}
	public void setRoles(Integer roles) {
		this.roles = roles;
	}
	public String getOpreat() {
		return opreat;
	}
	public void setOpreat(String opreat) {
		this.opreat = opreat;
	}
	
	
	
}
