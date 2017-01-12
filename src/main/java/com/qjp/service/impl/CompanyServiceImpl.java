package com.qjp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
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
	}
	
	@Override
	public CompanyQuery getCompanyList(CompanyQuery companyQuery) {
		String companyName = companyQuery.getCompanyName();
		Long companyId = companyQuery.getCompanyId();
		Integer pageSize = companyQuery.getSize();
		Integer currentPage = companyQuery.getPage();
		String loginResult = MyBaseApiUtils.getSubCompanyPage(companyName, companyId.toString(), pageSize.toString(), currentPage.toString());
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
	}

	@Override
	public CompanyEntity getCompanyById(Long id) {
		
		return null;
	}
}

