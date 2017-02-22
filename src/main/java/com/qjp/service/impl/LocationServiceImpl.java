package com.qjp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.AuthorityEntity;
import com.qjp.entity.AuthorityUserEntity;
import com.qjp.entity.CompanyEntity;
import com.qjp.service.AuthorityService;
import com.qjp.service.LocationService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.MyBaseApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.AuthorityQuery;
import com.qjp.util.query.AuthorityVOQuery;
import com.qjp.util.vo.AuthorityVO;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: AuthorityServiceImpl.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class LocationServiceImpl implements LocationService{
	@Override
	public String getProvinceCityArea() {
		String result = MyBaseApiUtils.getProCityArea();
		String data = null;
		if(StringUtils.isNotBlank(result)){
			JSONObject jsonObject = JSONObject.parseObject(result);
			if(jsonObject != null){
				Object codeObj = jsonObject.get("code");
				if(codeObj != null){
					String code = codeObj.toString();
					if (ApiCode.OK.toString().equals(code)) {
						Object dataObj = jsonObject.get("data");
						if(dataObj != null){
							data = dataObj.toString();
						}
					}
				}
			}
		}
		
		return data;
	}


	
}

