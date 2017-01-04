package com.qjp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.qjp.entity.UserEntity;
import com.qjp.model.BRPUserInfo;
import com.qjp.model.LoginUser;
import com.qjp.util.CommonUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.api.PMSApiUtils;
import com.qjp.util.cookie.LoggedCookie;

/** 
 * <p>Project: BRP</p> 
 * <p>Title: BaseController.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public class BaseController {
	public String getToken(HttpServletRequest request){
		String token = LoggedCookie.checkTokenCookie(request);
		if(StringUtils.isBlank(token) && CommonUtils.getLocalEnv()){
			//token = PMSApiUtils.getLocalToken();
		}
		
		return token;
	}
	
	public UserEntity getLoginUser(HttpServletRequest request){
		UserEntity user = UserUtils.getLoginUser(request);
		return user;
	}
	
}

