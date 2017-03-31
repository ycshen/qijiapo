package com.qjp.util.query;

import com.qjp.entity.DailyAccountEntity;
import com.qjp.model.pageutil.Page;

/** 
 * <p>Project: qijiapo-crm</p> 
 * <p>Title: DailyAccountQuery.java</p> 
 * <p>Description: 日常账号查询条件</p>
 * <p>Copyright (c) 2017 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public class DailyAccountQuery extends Page<DailyAccountEntity>{
	
	private String accountName;
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

}

