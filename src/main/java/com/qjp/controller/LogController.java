package com.qjp.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.qjp.base.VipLevel;
import com.qjp.entity.CompanyEntity;
import com.qjp.entity.ConfigEntity;
import com.qjp.entity.Constant;
import com.qjp.entity.LogEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.CompanyService;
import com.qjp.service.ConfigService;
import com.qjp.service.LogService;
import com.qjp.util.LogUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.CompanyQuery;
import com.qjp.util.query.LogQuery;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: CompanyController.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Controller
@RequestMapping("/inner/log")
public class LogController extends BaseController{
	@Autowired
	private LogService logService;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String list(@ModelAttribute LogQuery logQuery, HttpServletRequest request){
		String result = StringUtils.EMPTY;
		UserEntity user = UserUtils.getLoginUser(request);
		logQuery.setCompanyId(user.getCompanyId().toString());
		logQuery = logService.getLogPage(logQuery);
		List<LogEntity> list = logQuery.getItems();
		if(list != null && list.size() > 0){
			result = new Gson().toJson(list);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/logs", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ModelAndView logs(@ModelAttribute LogQuery logQuery, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/log/log_list");
		UserEntity user = UserUtils.getLoginUser(request);
		logQuery.setCompanyId(user.getCompanyId().toString());
		logQuery = logService.getLogPage(logQuery);
		mav.addObject("logQuery", logQuery);
		
		return mav;
	}
	
}

