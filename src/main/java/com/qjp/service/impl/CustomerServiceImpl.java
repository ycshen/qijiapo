package com.qjp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.CustomerEntity;
import com.qjp.service.CustomerService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.CRMApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.CustomerQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/** 
 * <p>Project: qijiapo</p> 
 * <p>Title: CustomerServiceImpl.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class CustomerServiceImpl implements CustomerService{

	@Override
	public String insertCustomer(CustomerEntity customer) {
		String jsonStr = JsonUtils.json2Str(customer);
		String result = CRMApiUtils.insertCustomer(jsonStr);
		String id = "";
		if(StringUtils.isNotBlank(result)){
			JSONObject jsonObject = JSONObject.parseObject(result);
			if(jsonObject != null){
				Object codeObj = jsonObject.get("code");
				if(codeObj != null){
					String code = codeObj.toString();
					if (ApiCode.OK.toString().equals(code)) {
						Object dataObj = jsonObject.get("data");
						if(dataObj != null){
							id = dataObj.toString();
							
						}
					}
				}
			}
		}
		
		return id;
	}

	@Override
	public CustomerQuery getCustomerPage(CustomerQuery customerQuery) {
		String json = JsonUtils.json2Str(customerQuery);
		String loginResult = CRMApiUtils.getCustomerPage(json);
		if(StringUtils.isNotBlank(loginResult)){
			JSONObject jsonObject = JSONObject.parseObject(loginResult);
			if(jsonObject != null){
				Object codeObj = jsonObject.get("code");
				if(codeObj != null){
					String code = codeObj.toString();
					if (ApiCode.OK.toString().equals(code)) {
						Object dataObj = jsonObject.get("data");
						if(dataObj != null){
							String data = dataObj.toString();
							List<CustomerEntity> list = JSONObject.parseArray(data, CustomerEntity.class);
							customerQuery.setItems(list);
						}
						
						Object countObj = jsonObject.get("count");
						if(countObj != null){
							String count = countObj.toString();
							customerQuery.setCount(Integer.parseInt(count));
						}
					}
				}
			}
		}
		
		return customerQuery;
	}

	@Override
	public CustomerEntity getCustomerById(String id) {
		String result = CRMApiUtils.getCustomerById(id);
		CustomerEntity customer = null;
		if(StringUtils.isNotBlank(result)){
			JSONObject jsonObject = JSONObject.parseObject(result);
			if(jsonObject != null){
				Object codeObj = jsonObject.get("code");
				if(codeObj != null){
					String code = codeObj.toString();
					if (ApiCode.OK.toString().equals(code)) {
						Object dataObj = jsonObject.get("data");
						if(dataObj != null){
							String data = dataObj.toString();
							customer = JSONObject.parseObject(data, CustomerEntity.class);
						}
					}
				}
			}
		}
		
		return customer;
	}

	@Override
	public void deleteCustomerById(String id) {
		CRMApiUtils.deleteCustomerById(id);
	}

	@Override
	public void batchDeleteCustomer(List<String> ids) {
		String idList = JsonUtils.json2Str(ids);
		CRMApiUtils.batchDeleteCustomer(idList);
	}

	@Override
	public void updateCustomer(CustomerEntity customer) {
		String jsonStr = JsonUtils.json2Str(customer);
		CRMApiUtils.updateCustomer(jsonStr);
	}

	@Override
	public void batchDelete(String idArr) {
		System.out.println(idArr);
	}

	@Override
	public void batchTransfer(String idArr) {
		System.out.println(idArr);
		
	}

	@Override
	public Integer getSelfCustomerCount(CustomerQuery customerQuery) {
		String json = JsonUtils.json2Str(customerQuery);
		Integer count = 0;
		String loginResult = CRMApiUtils.getSelfCustomerCount(json);
		if(StringUtils.isNotBlank(loginResult)){
			JSONObject jsonObject = JSONObject.parseObject(loginResult);
			if(jsonObject != null){
				Object codeObj = jsonObject.get("code");
				if(codeObj != null){
					String code = codeObj.toString();
					if (ApiCode.OK.toString().equals(code)) {
						Object dataObj = jsonObject.get("data");
						if(dataObj != null){
							String data = dataObj.toString();
							count = Integer.parseInt(data);
						}
					}
				}
			}
		}

		return count;
	}

}

