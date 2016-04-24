package com.sys.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.SpringContextHolder;
import com.sys.common.ConfigKeys;
import com.sys.db.entity.Config;
import com.sys.db.service.ConfigService;

/**
 * @author chenchuan
 * @date 2016��1��22��
 * ϵͳ���ù�����
 */
public class ConfigUtil {
	/**
	 * ����ֵ�ָ��--;
	 */
	public static final String VALUE_SPLIT=";";
	/**
	 * �������-ֵ�ָ��--:
	 */
	public static final String K_V_SPLIT=":";
	/**
	 * ��������ֵ�ָ��--,
	 */
	public static final String V_SPLIT=",";
	
	static ConfigService configService =SpringContextHolder.getBean("configService");
	
	
	
	/**
	 * ���config����
	 * @param key
	 * @return
	 */
	public static Config getConfig(String key){
		Config query = new Config(key);
		return configService.findEntity(query);
	}
	
	/**
	 * ���ϵͳ����ֵString
	 * @param key
	 * @return
	 */
	public static String getStrVal(String key){
		Config config = getConfig(key);
		if(config!=null&&StringUtil.isNotNull(config.getValue())){
			return config.getValue();
		}
		return "";
	}
	/**
	 * ���ϵͳ����ֵInt
	 * @param key
	 * @return
	 */
	public static int getIntVal(String key){
		Config config = getConfig(key);
		if(config!=null&&StringUtil.isNotNull(config.getValue())){
			return Integer.valueOf(config.getValue());
		}
		return 0;
	}
	/**
	 * ���������ַ������ֵ�list
	 * @param key
	 * @return
	 */
	public static List<String> getListVal(String key){
		String value = getStrVal(key);
		List<String> list = converToList(value);
		return list;
	}

	public static List<String> converToList(String value) {
		List<String> list = new ArrayList<String>();
		for (String string : value.split(VALUE_SPLIT)) {
			if(StringUtil.isNotNull(string)){
				list.add(string);
			}
		}
		return list;
	}
	/**
	 * ��ѯ����Ȩ��
	 * @return
	 */
	public static Map<Integer, List<String>> authConfMap(){
		Map<Integer, List<String>> authMap = new HashMap<Integer, List<String>>();
		List<String> authList = getListVal(ConfigKeys.AUTH_CONF);
		for (String authConf : authList) {
			String[] items = authConf.split(K_V_SPLIT);
			if(items!=null&&items.length>1){
				Integer authKey = Integer.valueOf(items[0]);
				List<String> authValue = new ArrayList<String>();
				for (String val : items[1].split(V_SPLIT)) {
					if(StringUtil.isNotNull(val)){
						authValue.add(val);
					}
				}
				authMap.put(authKey, authValue);
			}
		}
		return authMap;
	}
	/**
	 * ���ݿ�ϵͳ�����Ƿ���
	 * @param key
	 * @return
	 */
	public static boolean isConfigSwitchOn(String key){
		Config config = getConfig(key);
		if(config!=null&&StringUtil.isNotNull(config.getValue())){
			isSwitchOn(config.getValue());
		}
		return false;
	}
	/**
	 * ��������
	 * @param value
	 * @return
	 */
	public static boolean isSwitchOn(String value){
		int rslt = 0;
		if(StringUtil.isNotNull(value)){
			rslt = Integer.valueOf(value);
		}
		return rslt==0?false:true;
	}

	/**
	 * ��ü�ֵӳ������
	 * @param cardType
	 * @return
	 */
	public static Map<Object, Object> getMapVal(String configKey) {
		List<String> configs = getListVal(configKey);
		Map<Object, Object> configMap = new HashMap<Object, Object>();
		for (String config : configs) {
			String[] items = config.split(K_V_SPLIT);
			configMap.put(items[0], items[1]);
		}
		return configMap;
	}

	/**
	 * ������ͻἮ��λ
	 * @param givemetric
	 * @return
	 */
	public static String getGiveMetric(String givemetric) {
		List<String> metricConfigs = getListVal(ConfigKeys.DATE_METRIC);
		for (String metric : metricConfigs) {
			String[] items = metric.split(K_V_SPLIT);
			if(givemetric.equalsIgnoreCase(items[0])){
				return items[1];
			}
		}
		return givemetric;
	}
}
