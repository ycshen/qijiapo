package com.qjp.util.api;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.qjp.base.Constant;
import com.qjp.util.CommonUtils;
import com.qjp.util.HttpUtils;
import com.qjp.util.SHA1Utils;

/** 
 * <p>Project: qijiapo</p> 
 * <p>Title: MyBaseApiUtils.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public class MyBaseApiUtils {
	/**
	 * 获取Mybase地址
	 * @return
	 */
	private static String getMyBaseUrl(){
		if(CommonUtils.getProdEnv()){
			return Constant.mybase;
		}else {
			return Constant.mybase ;
		}
	}
	
	
	public static String login(String account, String password){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + Constant.mybase_login;	
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("account", account);
			jsonObject.put("password", password);
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("account", account);
			maps.put("password", password);
			String secret = SHA1Utils.SHA1(maps);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}

