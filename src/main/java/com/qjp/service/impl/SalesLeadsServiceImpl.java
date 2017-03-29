package com.qjp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.SalesLeadsEntity;
import com.qjp.service.SalesLeadsService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.CRMApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.SalesLeadsQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/** 
 * <p>Project: qijiapo</p> 
 * <p>Title: SalesLeadsServiceImpl.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class SalesLeadsServiceImpl implements SalesLeadsService{

	@Override
	public String insertSalesLeads(SalesLeadsEntity salesLeads) {
		String jsonStr = JsonUtils.json2Str(salesLeads);
		String result = CRMApiUtils.insertSalesLeads(jsonStr);
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
	public SalesLeadsQuery getSalesLeadsPage(SalesLeadsQuery salesLeadsQuery) {
		String json = JsonUtils.json2Str(salesLeadsQuery);
		String loginResult = CRMApiUtils.getSalesLeadsPage(json);
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
							List<SalesLeadsEntity> list = JSONObject.parseArray(data, SalesLeadsEntity.class);
							salesLeadsQuery.setItems(list);
						}
						
						Object countObj = jsonObject.get("count");
						if(countObj != null){
							String count = countObj.toString();
							salesLeadsQuery.setCount(Integer.parseInt(count));
						}
					}
				}
			}
		}
		
		return salesLeadsQuery;
	}

	@Override
	public SalesLeadsEntity getSalesLeadsById(String id) {
		String result = CRMApiUtils.getSalesLeadsById(id);
		SalesLeadsEntity salesLeads = null;
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
							salesLeads = JSONObject.parseObject(data, SalesLeadsEntity.class);
						}
					}
				}
			}
		}
		
		return salesLeads;
	}

	@Override
	public void deleteSalesLeadsById(String id) {
		CRMApiUtils.deleteSalesLeadsById(id);
	}

	@Override
	public void batchDeleteSalesLeads(List<String> ids) {
		String idList = JsonUtils.json2Str(ids);
		CRMApiUtils.batchDeleteSalesLeads(idList);
	}

	@Override
	public void updateSalesLeads(SalesLeadsEntity salesLeads) {
		String jsonStr = JsonUtils.json2Str(salesLeads);
		CRMApiUtils.updateSalesLeads(jsonStr);
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

