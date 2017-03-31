package com.qjp.service;

import com.qjp.entity.DailyAccountEntity;
import com.qjp.util.query.DailyAccountQuery;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: DailyAccountService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public interface DailyAccountService {
	String insertDailyAccount(DailyAccountEntity DailyAccount);
	DailyAccountQuery getDailyAccountPage(DailyAccountQuery DailyAccountQuery);
	DailyAccountEntity getDailyAccountById(String id);
	void deleteDailyAccountById(String id);
	void updateDailyAccount(DailyAccountEntity DailyAccount);
}

