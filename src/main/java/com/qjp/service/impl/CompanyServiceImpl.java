package com.qjp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qjp.entity.CompanyEntity;
import com.qjp.service.CompanyService;
import com.qjp.util.api.MyBaseApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.CompanyQuery;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: CompanyService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class CompanyServiceImpl implements CompanyService{

	@Override
	public void insertCompany(CompanyEntity company) {
		String companyJson = new Gson().toJson(company);
		MyBaseApiUtils.insertCompany(companyJson);
	}
	
	@Override
	public CompanyQuery getCompanyList(CompanyQuery companyQuery) {
		String companyName = companyQuery.getCompanyName();
		String companyId = companyQuery.getCompanyId();
		Integer pageSize = companyQuery.getSize();
		Integer currentPage = companyQuery.getPage();
		String loginResult = MyBaseApiUtils.getSubCompanyPage(companyName, companyId, pageSize.toString(), currentPage.toString());
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
							List<CompanyEntity> list = JSONObject.parseArray(data, CompanyEntity.class);
							companyQuery.setItems(list);
						}
						
						Object countObj = jsonObject.get("count");
						if(countObj != null){
							String count = countObj.toString();
							companyQuery.setCount(Integer.parseInt(count));
						}
					}
				}
			}
		}
		
		return companyQuery;
	}

	@Override
	public void updateCompany(CompanyEntity company) {
		String companyJson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(company);
		MyBaseApiUtils.updateCompany(companyJson);
	}

	@Override
	public CompanyEntity getCompanyById(Long id) {
		String result = MyBaseApiUtils.getCompanyById(id.toString());
		CompanyEntity company = null;
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
							company = JSONObject.parseObject(data, CompanyEntity.class);
						}
					}
				}
			}
		}
		
		return company;
	}

	@Override
	public void deleteCompany(String id) {
		MyBaseApiUtils.deleteCompany(id);
	}

	@Override
	public void activateCompany(String id) {
		MyBaseApiUtils.deleteCompany(id);
	}

	@Override
	public String getCompanyStaffTreeById(String companyId) {
		String result = MyBaseApiUtils.getCompanyStaffTreeById(companyId);
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
							return data;
						}
					}
				}
			}
		}
		
		return StringUtils.EMPTY;
	}
}

