package com.qjp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.DailyAccountEntity;
import com.qjp.service.DailyAccountService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.MyBaseApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.DailyAccountQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/** 
 * <p>Project: qijiapo</p> 
 * <p>Title: DailyAccountServiceImpl.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class DailyAccountServiceImpl implements DailyAccountService{

	@Override
	public String insertDailyAccount(DailyAccountEntity dailyAccount) {
		String jsonStr = JsonUtils.json2Str(dailyAccount);
		String result = MyBaseApiUtils.insertDailyAccount(jsonStr);
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
	public DailyAccountQuery getDailyAccountPage(DailyAccountQuery dailyAccountQuery) {
		String json = JsonUtils.json2Str(dailyAccountQuery);
		String loginResult = MyBaseApiUtils.getDailyAccountPage(json);
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
							List<DailyAccountEntity> list = JSONObject.parseArray(data, DailyAccountEntity.class);
							dailyAccountQuery.setItems(list);
						}
						
						Object countObj = jsonObject.get("count");
						if(countObj != null){
							String count = countObj.toString();
							dailyAccountQuery.setCount(Integer.parseInt(count));
						}
					}
				}
			}
		}
		
		return dailyAccountQuery;
	}

	@Override
	public DailyAccountEntity getDailyAccountById(String id) {
		String result = MyBaseApiUtils.getDailyAccountById(id);
		DailyAccountEntity dailyAccount = null;
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
							dailyAccount = JSONObject.parseObject(data, DailyAccountEntity.class);
						}
					}
				}
			}
		}
		
		return dailyAccount;
	}

	@Override
	public void deleteDailyAccountById(String id) {
		MyBaseApiUtils.deleteDailyAccountById(id);
	}


	@Override
	public void updateDailyAccount(DailyAccountEntity dailyAccount) {
		String jsonStr = JsonUtils.json2Str(dailyAccount);
		MyBaseApiUtils.updateDailyAccount(jsonStr);
	}

	
}

