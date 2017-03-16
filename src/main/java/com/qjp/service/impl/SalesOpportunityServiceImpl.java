package com.qjp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.SalesOpportunityEntity;
import com.qjp.service.SalesOpportunityService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.CRMApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.SalesOpportunityQuery;

/** 
 * <p>Project: qijiapo</p> 
 * <p>Title: SalesOpportunityServiceImpl.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class SalesOpportunityServiceImpl implements SalesOpportunityService{

	@Override
	public String insertSalesOpportunity(SalesOpportunityEntity salesOpportunity) {
		String jsonStr = JsonUtils.json2Str(salesOpportunity);
		String result = CRMApiUtils.insertSalesOpportunity(jsonStr);
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
	public SalesOpportunityQuery getSalesOpportunityPage(SalesOpportunityQuery salesOpportunityQuery) {
		String json = JsonUtils.json2Str(salesOpportunityQuery);
		String loginResult = CRMApiUtils.getSalesOpportunityPage(json);
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
							List<SalesOpportunityEntity> list = JSONObject.parseArray(data, SalesOpportunityEntity.class);
							salesOpportunityQuery.setItems(list);
						}
						
						Object countObj = jsonObject.get("count");
						if(countObj != null){
							String count = countObj.toString();
							salesOpportunityQuery.setCount(Integer.parseInt(count));
						}
					}
				}
			}
		}
		
		return salesOpportunityQuery;
	}

	@Override
	public SalesOpportunityEntity getSalesOpportunityById(String id) {
		String result = CRMApiUtils.getSalesOpportunityById(id);
		SalesOpportunityEntity salesOpportunity = null;
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
							salesOpportunity = JSONObject.parseObject(data, SalesOpportunityEntity.class);
						}
					}
				}
			}
		}
		
		return salesOpportunity;
	}

	@Override
	public void deleteSalesOpportunityById(String id) {
		CRMApiUtils.deleteSalesOpportunityById(id);
	}

	@Override
	public void batchDeleteSalesOpportunity(List<String> ids) {
		String idList = JsonUtils.json2Str(ids);
		CRMApiUtils.batchDeleteSalesOpportunity(idList);
	}

	@Override
	public void updateSalesOpportunity(SalesOpportunityEntity salesOpportunity) {
		String jsonStr = JsonUtils.json2Str(salesOpportunity);
		CRMApiUtils.updateSalesOpportunity(jsonStr);
	}

	@Override
	public void batchDelete(String idArr) {
		System.out.println(idArr);
	}

	@Override
	public void batchTransfer(String idArr) {
		System.out.println(idArr);
		
	}
	
}

