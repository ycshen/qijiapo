package com.qjp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qjp.entity.MenuDefinedEntity;
import com.qjp.service.MenuDefinedService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.MyBaseApiUtils;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: MenuDefinedServiceImpl.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class MenuDefinedServiceImpl implements MenuDefinedService{

	@Override
	public void insertMenuDefined(List<MenuDefinedEntity> menuDefinedList) {
		String mdJson = JsonUtils.json2Str(menuDefinedList);
		MyBaseApiUtils.insertMenuDefined(mdJson);
	}

	
	
	
	
}

