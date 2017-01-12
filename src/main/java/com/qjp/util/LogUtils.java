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
	 * 记录操作日志
	 * @param logType 0-功能日志 1-管理日志 
	 * @param log 日志内容
	 */
	public static void log(Integer logType, String log){
		
	}
	
	/**
	 * 记录管理员操作日志
	 * @param log 日志内容
	 */
	public static void logAdmin( String logMsg, UserEntity user){
		LogEntity log = new LogEntity();
		log.setCompanyId(user.getCompanyId().toString());
		log.setDepartmentId(user.getDepartmentId().toString());
		log.setUserId(user.getId().toString());
		log.setUserName(user.getUserName());
		log.setLogType(1);
		log.setLogMsg(logMsg);
		String logJson = new Gson().toJson(log);
		MyBaseApiUtils.log(logJson);
	}
}

