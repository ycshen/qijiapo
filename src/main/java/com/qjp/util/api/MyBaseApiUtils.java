package com.qjp.util.api;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.qjp.base.UserStatus;
import com.qjp.base.url.MyBaseApiUrl;
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
			return MyBaseApiUrl.mybase;
		}else {
			return MyBaseApiUrl.mybase ;
		}
	}
	
	
	public static String login(String account, String password){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_login;	
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
	 * 获取权限列表
	 * @param authName
	 * @param companyId
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public static String getAuthPage(String authName, String companyId, String pageSize, String currentPage){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getAuthPage;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", companyId);
			maps.put("pageSize", pageSize);
			maps.put("currentPage", currentPage);
			maps.put("authName", authName);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", companyId);
			jsonObject.put("pageSize", pageSize);
			jsonObject.put("currentPage",currentPage);
			jsonObject.put("authName",authName);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
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
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getSubCompanyPage;
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
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_insertCompany;
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
	 * 更新公司信息
	 * @param companyJson
	 * @return
	 */
	public static String updateCompany(String companyJson){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_updateCompany;
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
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_close;
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
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_activate;
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
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_log;
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
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getAdminLogs;
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
	
	
	/**
	 * 获取菜单
	 * @param id
	 * @return
	 */
	public static String getMenus(String userId){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getMenus;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("userId", userId);			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("userId", userId);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 根据公司id获取公司信息
	 * @param id
	 * @return
	 */
	public static String getCompanyById(String id){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getCompanyById;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", id);			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", id);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getAuthById(String id){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getAuthById;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", id);			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", id);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 获取授权用户通过公司id和授权id
	 * @param companyId
	 * @param authId
	 * @param isAuth 获取的是否是授权的
	 * @return
	 */
	public static String getAuthUserByCidAndAuthId(String companyId, String authId, String isAuth){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getAuthUserByCidAndAuthId;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("companyId", companyId);			
			maps.put("authId", authId);		
			maps.put("isAuth", isAuth);		
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("companyId", companyId);
			jsonObject.put("authId", authId);
			jsonObject.put("isAuth", isAuth);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
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
	public static String getDepartmentByCompanyId(String id){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getDepByCompanyId;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", id);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", id);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	public static String getUserListByCompanyId(String id){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getUserListByCompanyId;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", id);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", id);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public static String getNoSubDeptListByCompanyId(String id){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getNoSubDepListByCId;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", id);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", id);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getUserPage(String userName, String companyId, String departmentId, String pageSize, String currentPage){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getUserPage;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("companyId", companyId);
			maps.put("departmentId", departmentId);
			maps.put("pageSize", pageSize);
			maps.put("userName", userName);
			maps.put("currentPage", currentPage);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("companyId", companyId);
			jsonObject.put("departmentId", departmentId);
			jsonObject.put("pageSize", pageSize);
			jsonObject.put("currentPage", currentPage);
			jsonObject.put("secret", secret);
			jsonObject.put("userName", userName);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 根据权限id获取用户信息列表
	 * @param authId
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public static String getUserListByAuthId(String authId, String companyId, String pageSize, String currentPage){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getUserListByAuthId;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("authId", authId);
			maps.put("companyId", companyId);
			maps.put("pageSize", pageSize);
			maps.put("currentPage", currentPage);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("authId", authId);
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
	
	
	/**
	 * 获取子公司信息列表
	 * @param companyId
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public static String getCompanyStaffTreeById(String companyId){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getCompanyStaffTreeById;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("companyId", companyId);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("companyId", companyId);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 是否存在相同部门
	 * @param id
	 * @param departmentName
	 * @param isCompany
	 * @return
	 */
	public static String isExistDepartment(String id, String departmentName, String isCompany, String departmentId){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_isExistDepartment;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("departmentName", departmentName);
			maps.put("departmentId", departmentId);
			maps.put("id", id);
			maps.put("isCompany", isCompany);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("departmentName", departmentName);
			jsonObject.put("departmentId", departmentId);
			jsonObject.put("id", id);
			jsonObject.put("isCompany", isCompany);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * 新增部门
	 * @param departmentJson
	 * @return
	 */
	public static String insertDepartment(String departmentJson){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_insertDepartment;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("departmentJson", departmentJson);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("departmentJson", departmentJson);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 更新公司信息
	 * @param companyJson
	 * @return
	 */
	public static String updateDepartment(String departmentJson){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_updateDepartment;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("departmentJson", departmentJson);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("departmentJson", departmentJson);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public static String insertUser(String userJson){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_insertUser;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("userJson", userJson);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("userJson", userJson);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String register(String userJson){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_register;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("userJson", userJson);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("userJson", userJson);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 更新员工信息
	 * @param companyJson
	 * @return
	 */
	public static String updateUser(String userJson){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_updateUser;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("userJson", userJson);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("userJson", userJson);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getDepById(String id){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getDepById;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", id);			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", id);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String changeCollapse(String userId, String isCollapseMenu){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_changeCollapse;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("userId", userId);	
			maps.put("isCollapseMenu", isCollapseMenu);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("userId", userId);
			jsonObject.put("isCollapseMenu", isCollapseMenu);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getSystemPosition(){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getSystemPosition;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getPositionByCompanyId(String companyId){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getPositionByCompanyId;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("companyId", companyId);			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("companyId", companyId);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String isExistPosition(String companyId, String positionName){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_isExistPosition;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("companyId", companyId);		
			maps.put("positionName", positionName);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("companyId", companyId);
			jsonObject.put("positionName", positionName);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return result;
	}
	
	public static String updatePosition(String positionJson){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_updatePosition;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("positionJson", positionJson);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("positionJson", positionJson);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String insertPosition(String positionJson){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_insertPosition;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("positionJson", positionJson);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("positionJson", positionJson);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String forbidUser(String id, String updateUser){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_forbidUser;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", id);
			maps.put("status", UserStatus.FORBIDDEN);
			maps.put("updateUser", updateUser);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", id);
			jsonObject.put("updateUser", updateUser);
			jsonObject.put("status", UserStatus.FORBIDDEN);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String forbidLogin(String id, String updateUser){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_forbidUser;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", id);
			maps.put("status", UserStatus.FORBID_LOGIN_INT);
			maps.put("updateUser", updateUser);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", id);
			jsonObject.put("status", UserStatus.FORBID_LOGIN_INT);
			jsonObject.put("updateUser", updateUser);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String enableUser(String id, String updateUser){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_forbidUser;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", id);
			maps.put("status", UserStatus.NORMAL_INT);
			maps.put("updateUser", updateUser);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", id);
			jsonObject.put("status", UserStatus.NORMAL_INT);
			jsonObject.put("updateUser", updateUser);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String resetPassword(String id, String password, String resetType, String email){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_resetPassword;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", id);
			maps.put("password", password);
			maps.put("resetType", resetType);
			maps.put("email", email);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", id);
			jsonObject.put("password", password);
			jsonObject.put("resetType", resetType);
			jsonObject.put("email", email);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 *得到职位列表
	 * @param companyId
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public static String getPositionPage(String positionName, String companyId, String pageSize, String currentPage){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getPositionPage;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("companyId", companyId);
			maps.put("pageSize", pageSize);
			maps.put("positionName", positionName);
			maps.put("currentPage", currentPage);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("companyId", companyId);
			jsonObject.put("pageSize", pageSize);
			jsonObject.put("currentPage",currentPage);
			jsonObject.put("positionName",positionName);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String changePositionStatus(String id, String status){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_changePositionStatus;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", id);
			maps.put("status", status);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", id);
			jsonObject.put("status", status);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public static String isExistTelephone(String telephone){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_isExistTelephone;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("telephone", telephone);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("telephone", telephone);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public static String getPositionById(String id){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getPositionById;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", id);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", id);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getOuterSystemPage(String companyId){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getOuterSystemPage;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("companyId", companyId);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("companyId", companyId);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getUserById(String id){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getUserById;	
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", id);
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", id);
			String secret = SHA1Utils.SHA1(maps);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String deleteDepartmentById(String id, String companyId){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_deleteDepartmentById;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("id", id);
			maps.put("companyId", companyId);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", id);
			jsonObject.put("companyId", companyId);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			System.out.println("errrrrrrrrrrrrrrrrrrrrrrrr");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String cancelAuthoritys(String authUserJson){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_cancelAuth;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("authUserJson", authUserJson);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("authUserJson", authUserJson);
			jsonObject.put("secret", secret);
			HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String batchAuth(String companyId, String authId, String authUserIdArray, String notAuthUserIdArray){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_batchAuth;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("companyId", companyId);
			maps.put("authId", authId);
			maps.put("authUserIdArray", authUserIdArray);
			maps.put("notAuthUserIdArray", notAuthUserIdArray);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("companyId", companyId);
			jsonObject.put("authId", authId);
			jsonObject.put("authUserIdArray", authUserIdArray);
			jsonObject.put("notAuthUserIdArray", notAuthUserIdArray);
			jsonObject.put("secret", secret);
			HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String insertAuthoritys(String authUserJson){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_insertAuth;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("authUserJson", authUserJson);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("authUserJson", authUserJson);
			jsonObject.put("secret", secret);
			HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String insertMemo(String memoJson){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_insertMemo;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("memoJson", memoJson);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("memoJson", memoJson);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getConfigListByCode(String code){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getConfigListByCode;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("code", code);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code", code);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getTodayMemo(String userId){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getTodayMemo;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("userId", userId);			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("userId", userId);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getWeekMemo(String startTime, String endTime, String userId){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getTodayMemo;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("userId", userId);			
			maps.put("startTime", startTime);		
			maps.put("endTime", endTime);		
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("userId", userId);
			jsonObject.put("startTime", startTime);
			jsonObject.put("endTime", userId);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getMonthMemo(String startTime, String endTime, String userId){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getMonthMemo;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("userId", userId);		
			maps.put("startTime", startTime);		
			maps.put("endTime", endTime);		
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("userId", userId);
			jsonObject.put("startTime", startTime);
			jsonObject.put("endTime", endTime);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String getProCityArea(){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getProCityArea;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public static String getLogs(String query){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getLogs;
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
	
	
	public static String getAllOutterMenu(String definedType, String casecadeId){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_getAllOutterMenu;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("definedType", definedType);
			maps.put("casecadeId", casecadeId);
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("secret", secret);
			jsonObject.put("definedType", definedType);
			jsonObject.put("casecadeId", casecadeId);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	 
	public static String insertMenuDefined(String mdJson){
		String result = StringUtils.EMPTY;
		try {	
			String url = getMyBaseUrl() + MyBaseApiUrl.mybase_insertMenuDefined;
			Map<String,Object> maps = SHA1Utils.getSha1Map();
			maps.put("mdJson", mdJson);
			
			String secret = SHA1Utils.SHA1(maps);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("mdJson", mdJson);
			jsonObject.put("secret", secret);
			result = HttpUtils.postUrl(url, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}

