package com.sys.db.entity;

import net.sf.json.JSONArray;

import com.sys.base.BaseEntity;

/**
 *Log.java
 */
public class Log extends BaseEntity {
	
	private String level;
	private String method;
	private String msg;
	private String parmjson;
	public Log() {
	}
	/**
	 * 参数传入至少需要修改前或修改后
	 * @param time
	 * @param oper
	 * @param optype
	 * @param level
	 * @param method
	 * @param msg
	 * @param params
	 */
	public Log(String time, String oper, String optype, String level,
			String method, String msg, Object...params) {
		super();
		this.level = level;
		this.method = method;
		this.msg = msg;
		if(params!=null&&params.length>0){
			JSONArray array = JSONArray.fromObject(params);
			JSONArray saveJson = new JSONArray();
			if(array.size()>0){
				saveJson.add("修改前参数:");
				saveJson.add(array.get(0));
			}
			
			if(array.size()>1){
				saveJson.add("修改后参数:");
				saveJson.add(array.get(1));
			}
			
			if(array.size()>2){
				saveJson.add("其他参数:");
				for(int i=2;i<array.size();i++){
					saveJson.add(array.get(i));
				}
			}
			this.parmjson = saveJson.toString();
		}
		
	}
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getParmjson() {
		return parmjson;
	}
	public void setParmjson(String parmjson) {
		this.parmjson = parmjson;
	}
	
}
