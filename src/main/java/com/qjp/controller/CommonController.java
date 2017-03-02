package com.qjp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qjp.entity.UserEntity;
import com.qjp.util.UserUtils;

/** 
 * <p>Project: qijiapo</p> 
 * <p>Title: CommonController.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Controller
@RequestMapping("/inner/common")
public class CommonController {
	@RequestMapping(value = "/selectDepartment", method = RequestMethod.GET)
	public ModelAndView selectDepartment(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("common/department");
		UserEntity loginUser = UserUtils.getAdminLoginUser(request);
		mav.addObject("loginUser", loginUser); 
		
		return mav;
	}
}

