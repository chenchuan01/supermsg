package com.sys.common;
/**
 *
 *@author chenchuan
 *@date 2016��2��16��
 *Params.java
 *ϵͳproperties���ò���
 */
public class Params {
	/**
	 * �ֹ�˾��־
	 */
	private String comp_flag;
	/**
	 * SQL�����־��ӡ����
	 */
	private String sql_log;
	/**
	 * ������־��ӡ����
	 */
	private String req_log;
	/**
	 * ϵͳ�û���ɫ
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
