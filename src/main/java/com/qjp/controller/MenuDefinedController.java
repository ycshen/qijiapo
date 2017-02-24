package com.qjp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.qjp.base.ResponseStatus;
import com.qjp.entity.CompanyEntity;
import com.qjp.entity.PositionEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.PositionService;
import com.qjp.util.LogUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.PositionQuery;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: CompanyController.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Controller
@RequestMapping("/inner/admin/menudefined")
public class MenuDefinedController extends BaseController{

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView editPosition(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/menudefined/menudefined_list");
		
		return mav;
	}
	
}

