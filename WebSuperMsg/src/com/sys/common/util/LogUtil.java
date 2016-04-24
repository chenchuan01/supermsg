package com.sys.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.SpringContextHolder;
import com.sys.SysConstants;
import com.sys.common.AppExpection;
import com.sys.common.Params;

/**
 * @author chenchuan
 * @date 2016年1月25日 
 * 日志记录工具类 LogUtil.java
 */
public class LogUtil {

	private static Logger logger;
	private static Params sysParams = SpringContextHolder.getBean("params");

	/**
	 * info级别
	 * 
	 * @param clazz
	 * @param context
	 * @param params
	 */
	public static void info(Class<?> clazz, String context, Object... params) {
		logger = LoggerFactory.getLogger(clazz);
		context = CommonUtil.format(context, params);
		logger.info(context);
	}
	/**
	 * info级别
	 * 
	 * @param clazz
	 * @param context
	 * @param params
	 */
	public static void infoSql(Class<?> clazz, String context, Object... params) {
		if(ConfigUtil.isSwitchOn(sysParams.getSql_log())){
			info(clazz, SysConstants.SQL_LOG+"=>"+context, params);
		}
	}
	/**
	 * info级别
	 * 
	 * @param clazz
	 * @param context
	 * @param params
	 */
	public static void infoReq(Class<?> clazz, String context, Object... params) {
		if(ConfigUtil.isSwitchOn(sysParams.getReq_log())){
			info(clazz, SysConstants.URL_LOG+"=>"+context, params);
		}
	}

	/**
	 * info级别（含方法名）
	 * 
	 * @param clazz
	 * @param methodName
	 * @param context
	 * @param params
	 */
	public static void info(Class<?> clazz, String methodName, String context,
			Object... params) {
		context = "methodName=>(" + methodName + ");info->" + context;
		info(clazz, context, params);
	}

	/**
	 * info级别（含方法名）保存数据库
	 * 
	 * @param clazz
	 * @param oper
	 * @param optype
	 * @param methodName
	 * @param context
	 * @param params
	 */
	public static void infoDB(Class<?> clazz, String oper, String optype,
			String methodName, String context, Object... params) {
		/*try {
			Log log = new Log(DateUtil.getNow(), oper, optype,
					LogConstants.LEVEL_INFO, methodName, context, params);
			getDBSvr().saveEntity(log);
		} catch (Exception e) {
			error(clazz, new AppExpection("","日志保存错误",e));
		}*/
		
		info(clazz, methodName, context, params);
	}

	/**
	 * error级别
	 * 
	 * @param clazz
	 * @param context
	 * @param params
	 */
	private static void error(Class<?> clazz, String context, Object... params) {
		logger = LoggerFactory.getLogger(clazz);
		logger.error(context, params);
	}

	/**
	 * error级别（含方法名）
	 * 
	 * @param clazz
	 * @param methodName
	 * @param context
	 * @param params
	 */
	public static void error(Class<?> clazz, String methodName, String context,
			Object... params) {

		context = "methodName=>(" + methodName + ");error->" + context;
		error(clazz, context, params);
	}

	/**
	 * error级别（含方法名）保存数据库
	 * 
	 * @param clazz
	 * @param oper
	 * @param optype
	 * @param methodName
	 * @param context
	 * @param params
	 */
	public static void errorDB(Class<?> clazz, String oper, String optype,
			String methodName, String context, Object... params) {
		/*try {
			Log log = new Log(DateUtil.getNow(), oper, optype,
					LogConstants.LEVEL_INFO, methodName, context, params);
			getDBSvr().saveEntity(log);
		} catch (Exception e) {
			error(clazz, new AppExpection("", "日志保存出错", e));
		}*/
		
		error(clazz, methodName, context, params);
	}

	/**
	 * 记录异常
	 * 
	 * @param clazz
	 * @param context
	 * @param e
	 */
	public static void error(Class<?> clazz, String context, Exception e) {
		logger = LoggerFactory.getLogger(clazz);
		logger.error(context, e);
	}

	/**
	 * 记录业务异常
	 * 
	 * @param clazz
	 * @param context
	 * @param e
	 */
	public static void error(Class<?> clazz, AppExpection e) {
		String context = "BusExpection[method=" + e.getMethodName() + "msg="
				+ e.getMessage() + "]\n";
		error(clazz, context, e);
	}

	/*private static LogService getDBSvr() {
		if (dbSvr == null) {
			dbSvr = SpringContextHolder.getBean("logService");
		}
		return dbSvr;
	}*/
}
