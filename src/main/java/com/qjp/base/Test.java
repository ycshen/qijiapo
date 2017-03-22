package com.qjp.base;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSONObject;
import com.qjp.base.url.CRMApiUrl;
import com.qjp.util.HttpUtils;
import com.qjp.util.SHA1Utils;

public class Test {
	
	public static String getCRMUrl(){
		return "";
	}
	//Customer
	public static String insertCustomer(String customer){
		String result = StringUtils.EMPTY;
		try {	
			String url = getCRMUrl() + CRMApiUrl.crm_insertCustomer;	
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("customer", customer);
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("customer", customer);
			String secret = SHA1Utils.SHA1(maps);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * 更新客户
	 * @param customer 客户
	 * @return
	 */
	public static String updateCustomer(String customer){
		String result = StringUtils.EMPTY;
		try {	
			String url = getCRMUrl() + CRMApiUrl.crm_updateCustomer;	
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("customer", customer);
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("customer", customer);
			String secret = SHA1Utils.SHA1(maps);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getCustomerPage(String query){
		String result = StringUtils.EMPTY;
		try {	
			String url = getCRMUrl() + CRMApiUrl.crm_getCustomerPage;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("query", query);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("query", query);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}	
	
	public static String getCustomerById(String id){
		String result = StringUtils.EMPTY;
		try {	
			String url = getCRMUrl() + CRMApiUrl.crm_getCustomerById;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", id);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("secret", secret);
			jsonObject.put("id", id);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 批量删除客户
	 * @param idList
	 * @return
	 */
	public static String batchDeleteCustomer(String idList){
		String result = StringUtils.EMPTY;
		try {	
			String url = getCRMUrl() + CRMApiUrl.crm_batchDeleteCustomer;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("idList", idList);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("secret", secret);
			jsonObject.put("idList", idList);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 根据id删除客户
	 * @param id
	 * @return
	 */
	public static String deleteCustomerById(String id){
		String result = StringUtils.EMPTY;
		try {	
			String url = getCRMUrl() + CRMApiUrl.crm_deleteCustomerById;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", id);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("secret", secret);
			jsonObject.put("id", id);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
