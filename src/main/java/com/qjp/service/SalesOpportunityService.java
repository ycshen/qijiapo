package com.qjp.service;

import com.qjp.entity.SalesOpportunityEntity;
import com.qjp.util.query.SalesOpportunityQuery;

import java.util.List;

/** 
 * <p>Project: qijiapo</p> 
 * <p>Title: SalesOpportunityService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public interface SalesOpportunityService {
	String insertSalesOpportunity(SalesOpportunityEntity salesOpportunity);
	SalesOpportunityQuery getSalesOpportunityPage(SalesOpportunityQuery salesOpportunityQuery);
	SalesOpportunityEntity getSalesOpportunityById(String id);
	void deleteSalesOpportunityById(String id);
	void batchDeleteSalesOpportunity(List<String> ids);
	void updateSalesOpportunity(SalesOpportunityEntity salesOpportunity);
	void batchDelete(String idArr);
	void batchTransfer(String idArr);
	/**
	 * 根据id更新销售金额
	 * @param id id
	 * @param saleMoney 销售金额
	 * @return 返回更新结果
	 */
	void updateSaleMoneyById(String id, String saleMoney);
}

