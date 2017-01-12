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
	
	/**
	 * 获取子公司信息列表
	 * @param companyId
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public static String getSubCompanyPage(String companyName, String companyId, String pageSize, String currentPage){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + Constant.mybase_getSubCompanyPage;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", companyId);
			maps.put("pageSize", pageSize);
			maps.put("currentPage", currentPage);
			maps.put("companyName", companyName);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", companyId);
			jsonObject.put("pageSize", pageSize);
			jsonObject.put("currentPage",currentPage);
			jsonObject.put("companyName",companyName);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * 获取子公司信息列表
	 * @param companyId
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public static String insertCompany(String companyJson){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + Constant.mybase_insertCompany;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("companyJson", companyJson);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("companyJson", companyJson);
			jsonObject.put("secret", secret);
			HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 禁用子公司信息
	 * @param id
	 * @return
	 */
	public static String deleteCompany(String id){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + Constant.mybase_close;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", id);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", id);
			jsonObject.put("secret", secret);
			HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 激活子公司信息
	 * @param id
	 * @return
	 */
	public static String activateCompany(String id){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + Constant.mybase_activate;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", id);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", id);
			jsonObject.put("secret", secret);
			HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 记录日志
	 * @param id
	 * @return
	 */
	public static String log(String logJson){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + Constant.mybase_log;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("logJson", logJson);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("logJson", logJson);
			jsonObject.put("secret", secret);
			HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 记录日志
	 * @param id
	 * @return
	 */
	public static String getAdminLogs(String companyId, String pageSize, String currentPage){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + Constant.mybase_getAdminLogs;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("companyId", companyId);
			maps.put("pageSize", pageSize);
			maps.put("currentPage", currentPage);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("companyId", companyId);
			jsonObject.put("pageSize", pageSize);
			jsonObject.put("currentPage", currentPage);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}

