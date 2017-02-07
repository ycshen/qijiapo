package com.qjp.service;

import com.qjp.entity.UserEntity;
import com.qjp.util.query.UserQuery;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: UserService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public interface UserService {
	void insertUser(UserEntity user);
	UserQuery getUserList(UserQuery userQuery);
	void updateUser(UserEntity user);
	UserEntity getUserById(String id);
	UserEntity login(String account, String password);
	boolean isExistTelphone(String departmentId, String telphone);
	void deleteUserById(String id);
	UserQuery getUserPage(UserQuery userQuery);
	void forbidUser(String id, String updateUser);
	void forbidLogin(String id, String updateUser);
	void enableUser(String id, String updateUser);
	void resetPassword(String id, String password);
	
}

