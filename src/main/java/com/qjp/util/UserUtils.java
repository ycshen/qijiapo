package com.qjp.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.qjp.base.Constant;
import com.qjp.base.RoleEnum;
import com.qjp.entity.RoleEntity;
import com.qjp.entity.UserEntity;
import com.qjp.model.BRPUserInfo;
import com.qjp.model.LoginUser;
import com.qjp.util.api.MyBaseApiUtils;
import com.qjp.util.cookie.LoggedCookie;
import com.google.gson.Gson;

/** 
 * <p>Project: BRP</p> 
 * <p>Title: UserUtils.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public class UserUtils {
	private static String THIS_TOKEN = "c32f5089-2d3b-4cd9-b3c2-8210bbecd70b";
	private static String USER_ID = "55560700";
	
	/**
	 * 获取用户登录信息
	 * @param request
	 * @return
	 */
	public static UserEntity getLoginUser(HttpServletRequest request){
		UserEntity loginUser = (UserEntity) request.getSession().getAttribute("loginUser");

		return loginUser;
	}
	
	/**
	 * 获取系统管理员登录信息(该方法废弃)
	 * @param request
	 * @return
	 */
	public static UserEntity getAdminLoginUser(HttpServletRequest request){
		UserEntity loginUser = (UserEntity) request.getSession().getAttribute("loginUser");
		
		return loginUser;
	}
	
	/**
	 * 获取用户角色
	 * @param request
	 * @return
	 */
	public static RoleEnum getUserRole(HttpServletRequest request){
		UserEntity loginUser = (UserEntity) request.getSession().getAttribute("loginUser");
		List<RoleEntity> roleList = loginUser.getRoleList();
		if(roleList != null && roleList.size() > 0){
			RoleEnum roleEnum = getHighestRole(roleList);

			return roleEnum;
		}else{
			return null;
		}
	}
	
	private static RoleEnum getHighestRole(List<RoleEntity> roleList){
		RoleEnum roleEnum = RoleEnum.USR;
		for (RoleEntity role : roleList) {
			if(role.getId() < roleEnum.getRoleId()){
				roleEnum = RoleEnum.getRoleEnum(role.getId().intValue());
			}
		}
		
		return roleEnum;
	}
	
	
	
}

