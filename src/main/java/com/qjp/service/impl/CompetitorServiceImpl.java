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
	public void insertCompetitor(CompetitorEntity competitor) {
		String jsonStr = JsonUtils.json2Str(competitor);
		CRMApiUtils.insertCustomer(jsonStr);
	}
	
}

