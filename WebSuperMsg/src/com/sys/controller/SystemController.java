package com.sys.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.base.BaseController;
import com.sys.base.dto.PageResult;
import com.sys.base.dto.QueryParam;
import com.sys.common.AppExpection;
import com.sys.common.LogConstants;
import com.sys.common.util.DateUtil;
import com.sys.common.util.LogUtil;
import com.sys.common.util.SessionUtil;
import com.sys.common.util.StringUtil;
import com.sys.db.DBConstants;
import com.sys.db.entity.Config;
import com.sys.db.entity.Log;
import com.sys.db.entity.User;
import com.sys.db.service.ConfigService;
import com.sys.db.service.LogService;
import com.sys.db.service.UserService;
/**
 * @author chenchuan
 * @date 2016��1��22�� ϵͳ���ܿ�����
 */
@Controller
@RequestMapping("/sys")
public class SystemController extends BaseController {
	@Resource
	UserService userService;
	@Resource
	ConfigService configService;
	@Resource
	LogService logService;

	/**
	 * �û��б�
	 * 
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "userList")
	public String userList(Model m) {
		return "sys/userList";
	}

	/**
	 * �û��б�
	 * 
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "userPage")
	public @ResponseBody
	PageResult<User> userListPage(QueryParam<User> params, Model m, User user) {
		params.setParam(user);
		PageResult<User> result = userService.pageQuery(params);
		return result;
	}
	@RequestMapping("newUser")
	public String newUser(){
		return "sys/newUser";
	}
	@RequestMapping("addUser")
	public @ResponseBody User addUser(User user){
		userService.userRegist(user);
		return user;
	}
	/**
	 * �û�����
	 * 
	 * @param id
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "userForm")
	public String userForm(Integer id, Model m) {
		User user = userService.findById(id);
		m.addAttribute("user", user);
		return "sys/userForm";
	}

	/**
	 * �����޸�
	 * 
	 * @param id
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "pwdModify")
	public String pwdModify(Integer id, Model m) {
		User user = userService.findById(id);
		m.addAttribute("user", user);
		return "sys/pwdModify";
	}

	/**
	 * �û��޸�
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "userModify")
	public @ResponseBody User userModify(User user, HttpSession session) {
		User sysUser =SessionUtil.sysUser(session);
		try {
			User dbUser = userService.findById(user.getId());
			userService.userUpdate(user);
			LogUtil.infoDB(getClass(), sysUser.getUserName(),
					LogConstants.OP_TYPE_UPD,
					"SystemController.userModify(User)", "ϵͳ��¼�˻���Ϣ����",dbUser, user);
		} catch (AppExpection e) {
			
			LogUtil.infoDB(getClass(), sysUser.getUserName(),
					LogConstants.LEVEL_EXP,
					"SystemController.userModify(User)", 
					e.getMessage(), user,e);
		}

		return user;
	}

	/**
	 * �û�ɾ��
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "userDelete")
	public @ResponseBody User userDelete(Integer id) {
		User user = userService.findById(id);
		userService.deleteEntity(user);
		return user;
	}

	/**
	 * ϵͳ�����б�
	 * 
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "configList")
	public String configList(Model m) throws AppExpection {
		return "sys/configList";
	}

	/**
	 * ϵͳ���÷�ҳ��ѯ
	 * 
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "configPage")
	public @ResponseBody
	PageResult<Config> configListPage(QueryParam<Config> params, Model m,
			Config config) throws AppExpection {
		params.setParam(config);
		PageResult<Config> result = configService.pageQuery(params);
		return result;
	}

	/**
	 * ϵͳ���ñ�
	 * 
	 * @param id
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "configForm")
	public String configForm(Integer id, Model m) throws AppExpection {
		Config config = configService.findById(id);
		m.addAttribute("config", config);
		return "sys/configForm";
	}

	/**
	 * ϵͳ�����޸�
	 * 
	 * @param config
	 * @return
	 */
	@RequestMapping(value = "configModify")
	public @ResponseBody Config configModify(Config config,HttpSession session) {
		User sysUser =SessionUtil.sysUser(session);
		try {
			Config dbConfig = configService.findById(config.getId());
			configService.updateEntity(config);
			LogUtil.infoDB(getClass(), sysUser.getUserName(),
					LogConstants.OP_TYPE_UPD,
					"SystemController.configModify(Config)","ϵͳ�����޸ı���" ,dbConfig,config);
		} catch (AppExpection e) {
			LogUtil.infoDB(getClass(), sysUser.getUserName(),
					LogConstants.LEVEL_EXP,
					"SystemController.configModify(Config)", 
					e.getMessage(), config,e);
		}
		return config;
	}
	/**
	 * ��־�б�
	 * 
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "logList")
	public String logList(Model m) {
		return "sys/logList";
	}

	/**
	 * ��־�б�
	 * 
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "logPage")
	public @ResponseBody
	PageResult<Log> logListPage(QueryParam<Log> params, Model m, Log log) {
		if(StringUtil.isNull(params.getStartDate())){
			params.setStartDate(DateUtil.countDayTime(new Date(), -3));
		}
		params.setOrderFiled("operatime");
		params.setOrderType(DBConstants.DML_ORDER_DESC);
		params.setParam(log);
		PageResult<Log> result = logService.pageQuery(params);
		return result;
	}

	/**
	 * ��־����
	 * 
	 * @param id
	 * @param m
	 * @return
	 * @throws AppExpection
	 */
	@RequestMapping(value = "logForm")
	public String logForm(Integer id, Model m) {
		Log log = logService.findById(id);
		m.addAttribute("log", log);
		return "sys/logForm";
	}
	
	@RequestMapping(value="invokeComarea")
	public String comarea(Integer peopleId,String saveUrl,Model m){
		m.addAttribute("peopleId", peopleId);
		m.addAttribute("saveUrl", saveUrl);
		return "common/camera";
	}
	
}
