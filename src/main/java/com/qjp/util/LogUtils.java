package com.qjp.util;

import com.google.gson.Gson;
import com.qjp.entity.LogEntity;
import com.qjp.entity.UserEntity;
import com.qjp.util.api.MyBaseApiUtils;

/** 
 * <p>Project: qijiapo</p> 
 * <p>Title: LogUtils.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public class LogUtils {
	
	/**
	 * 记录日志
	 * @param logType logType 0-功能日志 1-管理日志  2-crm竞争对手动态日志
	 * @param log 日志内容
	 * @param casecadeId 关联id
	 * @param casecadeIdDesc 关联id描述
	 */
	public static void log(Integer logType, String log, String casecadeId, String casecadeIdDesc, UserEntity user){
		
	}
	
	/**
	 * 记录CRM竞争对手动态日志
	 * @param logMsg 日志内容
	 * @param competitorId 竞争对手id
	 * @param user 登录人信息
	 */
	public static void logCRMCompetitor(String logMsg, String competitorId, UserEntity user){
		LogEntity log = new LogEntity();
		log.setCompanyId(user.getCompanyId().toString());
		Integer departmentId = user.getDepartmentId();
		if(departmentId != null){
			log.setDepartmentId(departmentId.toString());
		}
		log.setUserId(user.getId().toString());
		log.setUserName(user.getUserName());
		log.setLogType(2);
		log.setLogMsg(logMsg);
		log.setCasecadeId(competitorId);
		log.setCasecadeIdDesc("CRM-竞争对手ID");
		log.setCreateUser(user.getUserName());
		String logJson = new Gson().toJson(log);
		MyBaseApiUtils.log(logJson);
	}

	/**
	 * 记录CRM联系人动态日志
	 * @param logMsg 日志内容
	 * @param attnId 竞争对手id
	 * @param user 登录人信息
	 */
	public static void logCRMAttn(String logMsg, String attnId, UserEntity user){
		LogEntity log = new LogEntity();
		log.setCompanyId(user.getCompanyId().toString());
		Integer departmentId = user.getDepartmentId();
		if(departmentId != null){
			log.setDepartmentId(departmentId.toString());
		}
		log.setUserId(user.getId().toString());
		log.setUserName(user.getUserName());
		log.setLogType(2);
		log.setLogMsg(logMsg);
		log.setCasecadeId(attnId);
		log.setCasecadeIdDesc("CRM-竞争对手ID");
		log.setCreateUser(user.getUserName());
		String logJson = new Gson().toJson(log);
		MyBaseApiUtils.log(logJson);
	}
	
	/**
	 * 记录CRM产品动态日志
	 * @param logMsg 日志内容
	 * @param productId 产品id
	 * @param user 登录人信息
	 */
	public static void logCRMProduct(String logMsg, String productId, UserEntity user){
		LogEntity log = new LogEntity();
		log.setCompanyId(user.getCompanyId().toString());
		Integer departmentId = user.getDepartmentId();
		if(departmentId != null){
			log.setDepartmentId(departmentId.toString());
		}
		log.setUserId(user.getId().toString());
		log.setUserName(user.getUserName());
		log.setLogType(2);
		log.setLogMsg(logMsg);
		log.setCasecadeId(productId);
		log.setCasecadeIdDesc("CRM-产品ID");
		log.setCreateUser(user.getUserName());
		String logJson = new Gson().toJson(log);
		MyBaseApiUtils.log(logJson);
	}

	/**
	 * 活动日志
	 * @param logMsg
	 * @param activityId
	 * @param user
	 */
	public static void logCRMActivity(String logMsg, String activityId, UserEntity user){
		LogEntity log = new LogEntity();
		log.setCompanyId(user.getCompanyId().toString());
		Integer departmentId = user.getDepartmentId();
		if(departmentId != null){
			log.setDepartmentId(departmentId.toString());
		}
		log.setUserId(user.getId().toString());
		log.setUserName(user.getUserName());
		log.setLogType(2);
		log.setLogMsg(logMsg);
		log.setCasecadeId(activityId);
		log.setCasecadeIdDesc("CRM-活动ID");
		log.setCreateUser(user.getUserName());
		String logJson = new Gson().toJson(log);
		MyBaseApiUtils.log(logJson);
	}
	
	/**
	 * 记录管理员操作日志
	 * @param logMsg 日志内容
	 */
	public static void logAdmin(String logMsg, UserEntity user){
		LogEntity log = new LogEntity();
		log.setCompanyId(user.getCompanyId().toString());
		Integer departmentId = user.getDepartmentId();
		if(departmentId != null){
			log.setDepartmentId(departmentId.toString());
		}
		log.setUserId(user.getId().toString());
		log.setUserName(user.getUserName());
		log.setLogType(1);
		log.setLogMsg(logMsg);
		String logJson = new Gson().toJson(log);
		MyBaseApiUtils.log(logJson);
	}

	public static void logCRMContract(String s, String returnId, UserEntity user) {

		LogEntity log = new LogEntity();
		log.setCompanyId(user.getCompanyId().toString());
		Integer departmentId = user.getDepartmentId();
		if(departmentId != null){
			log.setDepartmentId(departmentId.toString());
		}
		log.setUserId(user.getId().toString());
		log.setUserName(user.getUserName());
		log.setLogType(2);
		log.setLogMsg(s);
		log.setCasecadeId(returnId);
		log.setCasecadeIdDesc("CRM-产品ID");
		log.setCreateUser(user.getUserName());
		String logJson = new Gson().toJson(log);
		MyBaseApiUtils.log(logJson);
	}
}

