package com.qjp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qjp.entity.ConfigEntity;
import com.qjp.service.ConfigService;
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
	public List<ConfigEntity> getConfigListByCode(String code) {
		
		return null;
	}

	@Override
	public void deleteConfigById(String id) {
	}
}

