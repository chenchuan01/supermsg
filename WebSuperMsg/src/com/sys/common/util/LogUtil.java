package com.sys.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.SpringContextHolder;
import com.sys.SysConstants;
import com.sys.common.AppExpection;
import com.sys.common.Params;

/**
 * @author chenchuan
 * @date 2016��1��25�� 
 * ��־��¼������ LogUtil.java
 */
public class LogUtil {

	private static Logger logger;
	private static Params sysParams = SpringContextHolder.getBean("params");

	/**
	 * info����
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
	 * info����
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
	 * info����
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
	 * info���𣨺���������
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
	 * info���𣨺����������������ݿ�
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
			error(clazz, new AppExpection("","��־�������",e));
		}*/
		
		info(clazz, methodName, context, params);
	}

	/**
	 * error����
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
	 * error���𣨺���������
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
	 * error���𣨺����������������ݿ�
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
			error(clazz, new AppExpection("", "��־�������", e));
		}*/
		
		error(clazz, methodName, context, params);
	}

	/**
	 * ��¼�쳣
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
	 * ��¼ҵ���쳣
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
