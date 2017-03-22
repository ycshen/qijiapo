package com.qjp.service;

import java.util.List;

import com.qjp.entity.CustomerEntity;
import com.qjp.util.query.CustomerQuery;

/** 
 * <p>Project: qijiapo</p> 
 * <p>Title: CustomerService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public interface CustomerService {
	String insertCustomer(CustomerEntity customer);
	CustomerQuery getCustomerPage(CustomerQuery customerQuery);
	CustomerEntity getCustomerById(String id);
	void deleteCustomerById(String id);
	void batchDeleteCustomer(List<String> ids);
	void updateCustomer(CustomerEntity customer);
	void batchDelete(String idArr);
	void batchTransfer(String idArr);
}

