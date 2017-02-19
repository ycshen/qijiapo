package com.qjp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.MemoEntity;
import com.qjp.service.MemoService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.MyBaseApiUtils;
import com.qjp.util.api.model.ApiCode;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: ConfigService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class MemoServiceImpl implements MemoService{

	@Override
	public void insertMemo(MemoEntity memo) {
		String memoJson = JsonUtils.json2Str(memo);
		MyBaseApiUtils.insertMemo(memoJson);
	}

	@Override
	public List<MemoEntity> getTodayMemo(String userId) {
		List<MemoEntity> list = null;
		String loginResult = MyBaseApiUtils.getTodayMemo(userId);
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
							list = JSONObject.parseArray(data, MemoEntity.class);
						}
						
					}
				}
			}
		}
		
		return list;
	}

	@Override
	public List<MemoEntity> getWeekMemo(String userId) {
		List<MemoEntity> list = null;
		String loginResult = MyBaseApiUtils.getWeekMemo(userId);
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
							list = JSONObject.parseArray(data, MemoEntity.class);
						}
						
					}
				}
			}
		}
		
		return list;
	}

	@Override
	public List<MemoEntity> getMonthMemo(String userId) {
		List<MemoEntity> list = null;
		String loginResult = MyBaseApiUtils.getMonthMemo(userId);
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
							list = JSONObject.parseArray(data, MemoEntity.class);
						}
						
					}
				}
			}
		}
		
		return list;
	}

}

