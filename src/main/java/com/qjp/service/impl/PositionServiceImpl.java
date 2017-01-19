package com.qjp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.PositionEntity;
import com.qjp.service.PositionService;
import com.qjp.util.api.MyBaseApiUtils;
import com.qjp.util.api.model.ApiCode;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: PositionServiceImpl.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class PositionServiceImpl implements PositionService{
	
	@Override
	public List<PositionEntity> getSystemPosition() {
		String result = MyBaseApiUtils.getSystemPosition();
		List<PositionEntity>  list = null;
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
							list = JSONObject.parseArray(data, PositionEntity.class);
						}
					}
				}
			}
		}
		
		return list;
	}

	@Override
	public List<PositionEntity> getPositionByCompanyId(String companyId) {
		String result = MyBaseApiUtils.getPositionByCompanyId(companyId);
		List<PositionEntity>  list = null;
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
							list = JSONObject.parseArray(data, PositionEntity.class);
						}
					}
				}
			}
		}
		
		return list;
	}
	
}

