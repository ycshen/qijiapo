package com.qjp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
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
	public List<UserEntity> getUserPage(UserQuery userQuery) {
		
		return null;
	}
	
}

