package com.qjp.service;

import java.util.List;

import com.qjp.entity.LogEntity;
import com.qjp.util.query.LogQuery;


/** 
 * <p>Project: MyBase</p> 
 * <p>Title: ConfigService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public interface LogService {
	LogQuery getAdminLogPage(LogQuery logQuery);
	LogQuery getLogPage(LogQuery logQuery);
}

