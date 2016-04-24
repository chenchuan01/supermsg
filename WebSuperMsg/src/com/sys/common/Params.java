package com.sys.common;
/**
 *
 *@author chenchuan
 *@date 2016年2月16日
 *Params.java
 *系统properties配置参数
 */
public class Params {
	/**
	 * 分公司标志
	 */
	private String comp_flag;
	/**
	 * SQL语句日志打印开关
	 */
	private String sql_log;
	/**
	 * 请求日志打印开关
	 */
	private String req_log;
	/**
	 * 系统用户角色
	 */
	private String sys_user_roles;
	
	public String getComp_flag() {
		return comp_flag;
	}

	public void setComp_flag(String comp_flag) {
		this.comp_flag = comp_flag;
	}

	public String getSql_log() {
		return sql_log;
	}

	public void setSql_log(String sql_log) {
		this.sql_log = sql_log;
	}

	public String getReq_log() {
		return req_log;
	}

	public void setReq_log(String req_log) {
		this.req_log = req_log;
	}

	public String getSys_user_roles() {
		return sys_user_roles;
	}

	public void setSys_user_roles(String sys_user_roles) {
		this.sys_user_roles = sys_user_roles;
	}
}
