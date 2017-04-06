package com.qjp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.SalesOppoProductEntity;
import com.qjp.service.SaleOppoProductService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.CRMApiUtils;
import com.qjp.util.api.model.ApiCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/** 
 * <p>Project: qijiapo</p> 
 * <p>Title: SaleOppoProductServiceImpl.java</p>
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class SaleOppoProductServiceImpl implements SaleOppoProductService{

	@Override
	public String insertSop(SalesOppoProductEntity sop) {
		String jsonStr = JsonUtils.json2Str(sop);
		String result = CRMApiUtils.insertSop(jsonStr);
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
	public List<SalesOppoProductEntity> getSopListBySaleOppoId(String saleOppoId) {
		String sopResult = CRMApiUtils.getSopBySaleOppoId(saleOppoId);
		List<SalesOppoProductEntity> list = null;
		if (StringUtils.isNotBlank(sopResult)){
			JSONObject object = JSONObject.parseObject(sopResult);
			if(object != null) {
				Object codeObj = object.get("code");
				if (codeObj != null) {
					String code = codeObj.toString();
					if (ApiCode.OK.toString().equals(code)) {
						Object dataObj = object.get("data");
						if (dataObj != null) {
							String data = dataObj.toString();
							list = JSONObject.parseArray(data, SalesOppoProductEntity.class);
						}
					}
				}
			}
		}
		return list;
	}

	@Override
	public void deleteById(String id) {
		CRMApiUtils.deleteSopById(id);
	}
}

