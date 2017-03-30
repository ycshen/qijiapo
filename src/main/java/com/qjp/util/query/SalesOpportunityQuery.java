package com.qjp.util.query;

import com.qjp.entity.SalesOpportunityEntity;
import com.qjp.model.pageutil.Page;

/** 
 * <p>Project: qijiapo-crm</p> 
 * <p>Title: SalesOpportunityQuery.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public class SalesOpportunityQuery extends Page<SalesOpportunityEntity>{
	private String salesOpportunityName;
	private String customerName;
	private String customerId;
	private Integer saleStage;//销售阶段
	public String getSalesOpportunityName() {
		return salesOpportunityName;
	}
	public void setSalesOpportunityName(String salesOpportunityName) {
		this.salesOpportunityName = salesOpportunityName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getSaleStage() {
		return saleStage;
	}
	public void setSaleStage(Integer saleStage) {
		this.saleStage = saleStage;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}

