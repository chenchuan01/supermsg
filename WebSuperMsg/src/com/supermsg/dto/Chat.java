package com.supermsg.dto;

import com.sys.common.util.DateUtil;
import com.sys.common.util.StringUtil;

/**
 *
 *Chat.java
 */
public class Chat {
	private String fromName;
	private String toName;
	private String msg;
	private String time;
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		if(StringUtil.isNotNull(time)){
			this.time = time;
		}else{
			this.time = DateUtil.getNow();
		}
		
	}
	
}
