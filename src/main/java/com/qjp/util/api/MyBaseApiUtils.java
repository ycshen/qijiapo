package com.qjp.util.api;

import com.alibaba.fastjson.JSONObject;
import com.qjp.util.ConfigUtils;
import com.qjp.util.PropertiesUtils;
import com.qjp.util.SHA1Utils;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonBackReference;

import com.qjp.base.Constant;
import com.qjp.util.CommonUtils;
import com.qjp.util.HttpUtils;

import java.io.IOException;
import java.security.DigestException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
	 * 获取pms地址
	 * @return
	 */
	private static String getPMSUrl(){
		return "";
		/*if(CommonUtils.getProdEnv()){
			return Constant.PMS_PROD;
		}else {
			return Constant.PMS_TEST ;
		}*/
	}
	
	public static void main(String[] args) {
		String url = "http://localhost:8080/MyBase/api/user/login";		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("account", "13608236879");
		jsonObject.put("password", "aa13a7ee75");
		jsonObject.put("cId", 18);
		String md5 = "5FB135E2603F4CF990442B16F4E2EA76";
		Map<String,Object> maps = new HashMap<String, Object>();
		maps.put("account", "13608236879");
		maps.put("password", "aa13a7ee75");
		maps.put("secret", md5);
		maps.put("cId", 18);
		try {
			String secret = SHA1Utils.SHA1(maps);
			jsonObject.put("secret", secret);
			String result = HttpUtils.postUrl(url, jsonObject.toJSONString());
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static String login(String account, String password){
		String url = "http://localhost:8080/MyBase/api/user/login";		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("account", "13608236879");
		jsonObject.put("password", "aa13a7ee75");
		jsonObject.put("cId", 18);
		String md5 = "5FB135E2603F4CF990442B16F4E2EA76";
		Map<String,Object> maps = new HashMap<String, Object>();
		maps.put("account", "13608236879");
		maps.put("password", "aa13a7ee75");
		maps.put("secret", md5);
		maps.put("cId", 18);
		String result = StringUtils.EMPTY;
		try {
			String secret = SHA1Utils.SHA1(maps);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject.toJSONString());
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}

