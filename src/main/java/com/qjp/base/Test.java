package com.qjp.base;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.qjp.base.url.CRMApiUrl;
import com.qjp.util.HttpUtils;
import com.qjp.util.SHA1Utils;

public class Test {
	
	public static String getCRMUrl(){
		return "";
	}
	//SalesOpportunity
	public static String insertSalesOpportunity(String salesOpportunity){
		String result = StringUtils.EMPTY;
		try {	
			String url = getCRMUrl() + CRMApiUrl.crm_insertSalesOpportunity;	
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("salesOpportunity", salesOpportunity);
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("salesOpportunity", salesOpportunity);
			String secret = SHA1Utils.SHA1(maps);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * 更新产品
	 * @param salesOpportunity 产品
	 * @return
	 */
	public static String updateSalesOpportunity(String salesOpportunity){
		String result = StringUtils.EMPTY;
		try {	
			String url = getCRMUrl() + CRMApiUrl.crm_updateSalesOpportunity;	
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("salesOpportunity", salesOpportunity);
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("salesOpportunity", salesOpportunity);
			String secret = SHA1Utils.SHA1(maps);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getSalesOpportunityPage(String query){
		String result = StringUtils.EMPTY;
		try {	
			String url = getCRMUrl() + CRMApiUrl.crm_getSalesOpportunityPage;
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
	
	public static String getSalesOpportunityById(String id){
		String result = StringUtils.EMPTY;
		try {	
			String url = getCRMUrl() + CRMApiUrl.crm_getSalesOpportunityById;
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
	 * 批量删除产品
	 * @param idList
	 * @return
	 */
	public static String batchDeleteSalesOpportunity(String idList){
		String result = StringUtils.EMPTY;
		try {	
			String url = getCRMUrl() + CRMApiUrl.crm_batchDeleteSalesOpportunity;
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
	 * 根据id删除产品
	 * @param id
	 * @return
	 */
	public static String deleteSalesOpportunityById(String id){
		String result = StringUtils.EMPTY;
		try {	
			String url = getCRMUrl() + CRMApiUrl.crm_deleteSalesOpportunityById;
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
