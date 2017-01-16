package com.qjp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.DepartmentEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.UserService;
import com.qjp.util.api.MyBaseApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.UserQuery;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: UserServiceImpl.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Override
	public void insertUser(UserEntity user) {
		
	}

	@Override
	public UserQuery getUserList(UserQuery userQuery) {
		String companyId = userQuery.getCompanyId();
		String departmentId = userQuery.getDepartmentId();
		String pageSize = userQuery.getSize().toString();
		String currentPage = userQuery.getPage().toString();
		String status = userQuery.getStatus().toString();
		String result = MyBaseApiUtils.getUserPage(status, companyId, departmentId, pageSize, currentPage);
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
							List<UserEntity> list = JSONObject.parseArray(data, UserEntity.class);
							userQuery.setItems(list);
						}
						
						Object countObj = jsonObject.get("count");
						if(countObj != null){
							String count = countObj.toString();
							userQuery.setCount(Integer.parseInt(count));
						}
					}
				}
			}
		}
		
		return userQuery;
	}

	@Override
	public void updateUser(UserEntity user) {
		
	}

	@Override
	public UserEntity getUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity login(String account, String password) {
		String loginResult = MyBaseApiUtils.login(account, password);
		UserEntity user = null;
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
							user = JSONObject.parseObject(data, UserEntity.class);
						}
					}
				}
			}
		}
		
		return user;
	}

	@Override
	public boolean isExistTelphone(String departmentId, String telphone) {
		
		
		return false;
	}

	@Override
	public void deleteUserById(String id) {
		
	}

	@Override
	public UserQuery getUserPage(UserQuery userQuery) {
		String companyId = userQuery.getCompanyId();
		String departmentId = userQuery.getDepartmentId();
		String pageSize = userQuery.getSize().toString();
		String currentPage = userQuery.getPage().toString();
		String status = userQuery.getStatus().toString();
		List<UserEntity> list = null;
		String result = MyBaseApiUtils.getUserPage(status, companyId, departmentId, pageSize, currentPage);
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
							list = JSONObject.parseArray(data, UserEntity.class);
							userQuery.setItems(list);
						}
						
						Object countObj = jsonObject.get("count");
						if(countObj != null){
							String count = countObj.toString();
							userQuery.setCount(Integer.parseInt(count));
						}
					}
				}
			}
		}
		
		return userQuery;
	}
	
}

