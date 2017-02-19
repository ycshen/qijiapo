package com.qjp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.CompanyEntity;
import com.qjp.entity.ConfigEntity;
import com.qjp.service.ConfigService;
import com.qjp.util.api.MyBaseApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.ConfigQuery;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: ConfigService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class ConfigServiceImpl implements ConfigService{
	
	@Override
	public void insertConfig(ConfigEntity config) {
	}
	
	@Override
	public ConfigQuery getConfigList(ConfigQuery configQuery) {
		
		
		return configQuery;
	}

	@Override
	public void updateConfig(ConfigEntity config) {
	}

	@Override
	public ConfigEntity getConfigById(Integer id) {
		
		return null;
	}

	@Override
	public List<ConfigEntity> getConfigListByCode(String configCode) {
		List<ConfigEntity> list = null;
		String loginResult = MyBaseApiUtils.getConfigListByCode(configCode);
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
							list = JSONObject.parseArray(data, ConfigEntity.class);
						}
						
					}
				}
			}
		}
		
		return list;
	}

	@Override
	public void deleteConfigById(String id) {
	}
}

