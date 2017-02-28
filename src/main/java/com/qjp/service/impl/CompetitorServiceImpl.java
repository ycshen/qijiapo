package com.qjp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qjp.entity.CompanyEntity;
import com.qjp.entity.CompetitorEntity;
import com.qjp.service.CompanyService;
import com.qjp.service.CompetitorService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.CRMApiUtils;
import com.qjp.util.api.MyBaseApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.CompanyQuery;
import com.qjp.util.query.CompetitorQuery;

/** 
 * <p>Project: qijiapo</p> 
 * <p>Title: CompetitorServiceImpl.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class CompetitorServiceImpl implements CompetitorService{

	@Override
	public String insertCompetitor(CompetitorEntity competitor) {
		String jsonStr = JsonUtils.json2Str(competitor);
		String result = CRMApiUtils.insertCompetitor(jsonStr);
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
	public CompetitorQuery getCompetitorPage(CompetitorQuery competitorQuery) {
		String json = JsonUtils.json2Str(competitorQuery);
		String loginResult = CRMApiUtils.getCompetitorPage(json);
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
							List<CompetitorEntity> list = JSONObject.parseArray(data, CompetitorEntity.class);
							competitorQuery.setItems(list);
						}
						
						Object countObj = jsonObject.get("count");
						if(countObj != null){
							String count = countObj.toString();
							competitorQuery.setCount(Integer.parseInt(count));
						}
					}
				}
			}
		}
		
		return competitorQuery;
	}

	@Override
	public CompetitorEntity getCompetitorById(String id) {
		String result = CRMApiUtils.getCompetitorById(id);
		CompetitorEntity competitor = null;
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
							competitor = JSONObject.parseObject(data, CompetitorEntity.class);
						}
					}
				}
			}
		}
		
		return competitor;
	}

	@Override
	public void deleteCompetitorById(String id) {
		CRMApiUtils.deleteCompetitorById(id);
	}

	@Override
	public void batchDeleteCompetitor(List<String> ids) {
		String idList = JsonUtils.json2Str(ids);
		CRMApiUtils.batchDeleteCompetitor(idList);
	}

	@Override
	public void updateCompetitor(CompetitorEntity competitor) {
		String jsonStr = JsonUtils.json2Str(competitor);
		CRMApiUtils.updateCompetitor(jsonStr);
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

