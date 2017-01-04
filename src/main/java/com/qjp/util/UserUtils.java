package com.qjp.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.qjp.base.Constant;
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
		if(CommonUtils.getLocalEnv() && loginUser == null){
			//本地随便取一个
			loginUser = new UserEntity();
			loginUser.setUserName("申鱼川");
			loginUser.setId(1l);
			loginUser.setUpdateUser("申鱼川");
			loginUser.setCompanyId(15l);
			loginUser.setCompanyName("嘉年华");
			request.getSession().setAttribute("loginUser", loginUser);
		}
		
		return loginUser;
	}
	
}

