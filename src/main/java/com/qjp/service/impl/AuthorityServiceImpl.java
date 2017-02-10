package com.qjp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.AuthorityEntity;
import com.qjp.service.AuthorityService;
import com.qjp.util.api.MyBaseApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.AuthorityQuery;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: AuthorityServiceImpl.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class AuthorityServiceImpl implements AuthorityService{

	@Override
	public AuthorityQuery getAuthorityList(AuthorityQuery authQuery) {
		String authName = authQuery.getAuthName();
		String companyId = authQuery.getCompanyId();
		Integer pageSize = authQuery.getSize();
		Integer currentPage = authQuery.getPage();
		String loginResult = MyBaseApiUtils.getAuthPage(authName, companyId, pageSize.toString(), currentPage.toString());
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
							List<AuthorityEntity> list = JSONObject.parseArray(data, AuthorityEntity.class);
							authQuery.setItems(list);
						}
						
						Object countObj = jsonObject.get("count");
						if(countObj != null){
							String count = countObj.toString();
							authQuery.setCount(Integer.parseInt(count));
						}
					}
				}
			}
		}
		
		return authQuery;
	}

	
}

