package com.qjp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.UserEntity;
import com.qjp.service.UserService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.MyBaseApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.UserAuthQuery;
import com.qjp.util.query.UserQuery;
import com.qjp.util.query.UserRoleQuery;
import com.qjp.util.vo.UserAuthVO;
import com.qjp.util.vo.UserRoleVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
		String userJson = JsonUtils.json2Str(user);
		MyBaseApiUtils.insertUser(userJson);
	}

	@Override
	public UserQuery getUserList(UserQuery userQuery) {
		String companyId = userQuery.getCompanyId();
		String departmentId = userQuery.getDepartmentId();
		String pageSize = userQuery.getSize().toString();
		String currentPage = userQuery.getPage().toString();
		String userName = userQuery.getUserName();
		if(StringUtils.isBlank(userName)){
			userName = "";
		}
		
		String result = MyBaseApiUtils.getUserPage(userName, companyId, departmentId, pageSize, currentPage);
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
		String userJson = JsonUtils.json2Str(user);
		MyBaseApiUtils.updateUser(userJson);
	}

	@Override
	public UserEntity getUserById(String id) {
		String loginResult = MyBaseApiUtils.getUserById(id);
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
	public boolean isExistTelphone(String departmentId, String telphone, String userId) {
		
		
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
		String userName = userQuery.getUserName();
		List<UserEntity> list = null;
		String result = MyBaseApiUtils.getUserPage(userName, companyId, departmentId, pageSize, currentPage);
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

	@Override
	public void forbidUser(String id, String updateUser) {
		MyBaseApiUtils.forbidUser(id, updateUser);
	}
	
	@Override
	public void forbidLogin(String id, String updateUser) {
		MyBaseApiUtils.forbidLogin(id, updateUser);
	}

	@Override
	public void enableUser(String id, String updateUser) {
		MyBaseApiUtils.enableUser(id, updateUser);
	}

	@Override
	public void resetPassword(String id, String password, String resetType, String email) {
		MyBaseApiUtils.resetPassword(id, password, resetType, email);
	}

	@Override
	public UserAuthQuery getUserListByAuthId(UserAuthQuery userAuthQuery) {
		String authId = userAuthQuery.getAuthId();
		String pageSize = userAuthQuery.getSize().toString();
		String currentPage = userAuthQuery.getPage().toString();
		String companyId = userAuthQuery.getCompanyId();
		String result = MyBaseApiUtils.getUserListByAuthId(authId, companyId, pageSize, currentPage);
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
							List<UserAuthVO> list = JSONObject.parseArray(data, UserAuthVO.class);
							userAuthQuery.setItems(list);
						}
						
						Object countObj = jsonObject.get("count");
						if(countObj != null){
							String count = countObj.toString();
							userAuthQuery.setCount(Integer.parseInt(count));
						}
					}
				}
			}
		}
		
		return userAuthQuery;
	}

	@Override
	public List<UserAuthVO> getAuthUserByCidAndAuthId(String companyId,
			String authId, String isAuth) {
		List<UserAuthVO> list = null;
		String result = MyBaseApiUtils.getAuthUserByCidAndAuthId(companyId, authId, isAuth.toString());
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
							list = JSONObject.parseArray(data, UserAuthVO.class);
						}
					}
				}
			}
		}
		
		return list;
	}

	@Override
	public void register(UserEntity user) {
		String userJson = JsonUtils.json2Str(user);
		MyBaseApiUtils.register(userJson);
	}

	@Override
	public Integer isExistTelephone(String telephone) {
		String loginResult = MyBaseApiUtils.isExistTelephone(telephone);
		Integer isExist = 0;
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
							isExist = Integer.parseInt(data);
						}
					}
				}
			}
		}
		
		return isExist;
	}

	@Override
	public void changeCollapse(String userId, String isCollapseMenu) {
		MyBaseApiUtils.changeCollapse(userId, isCollapseMenu);
	}

	@Override
	public List<UserEntity> getUserListByCompanyId(String id) {
		List<UserEntity> list = null;
		String result = MyBaseApiUtils.getUserListByCompanyId(id);
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
						}
					}
				}
			}
		}
		
		return list;
	}

	@Override
	public UserRoleQuery getUserListByRoleId(UserRoleQuery userRoleQuery) {
		String roleId = userRoleQuery.getRoleId();
		String pageSize = userRoleQuery.getSize().toString();
		String currentPage = userRoleQuery.getPage().toString();
		String companyId = userRoleQuery.getCompanyId();
		String result = MyBaseApiUtils.getUserListByRoleId(roleId, companyId, pageSize, currentPage);
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
							List<UserRoleVO> list = JSONObject.parseArray(data, UserRoleVO.class);
							userRoleQuery.setItems(list);
						}
						
						Object countObj = jsonObject.get("count");
						if(countObj != null){
							String count = countObj.toString();
							userRoleQuery.setCount(Integer.parseInt(count));
						}
					}
				}
			}
		}
		
		return userRoleQuery;
	}

	@Override
	public List<UserRoleVO> getRoleUserByCidAndRoleId(String companyId,
			String roleId, String isRole) {
		List<UserRoleVO> list = null;
		String result = MyBaseApiUtils.getRoleUserByCidAndRoleId(companyId, roleId, isRole.toString());
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
							list = JSONObject.parseArray(data, UserRoleVO.class);
						}
					}
				}
			}
		}
		
		return list;
	}

	@Override
	public Integer getUserCountByCompanyId(String companyId) {
		Integer count = 0;
		String result = MyBaseApiUtils.getUserCountByCompanyId(companyId);
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
							count = Integer.parseInt(data);
						}
					}
				}
			}
		}

		return count;
	}

}

