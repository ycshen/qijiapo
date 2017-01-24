package com.qjp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.qjp.base.ResponseStatus;
import com.qjp.entity.PositionEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.PositionService;
import com.qjp.util.UserUtils;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: CompanyController.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Controller
@RequestMapping("/inner/position")
public class PositionController extends BaseController{
	@Autowired
	private PositionService positionService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/position/position_list");
		UserEntity user = UserUtils.getLoginUser(request);
		String companyId = user.getCompanyId().toString();
		List<PositionEntity> userPositionList = positionService.getPositionByCompanyId(companyId);
		List<PositionEntity> systemPositionList = positionService.getSystemPosition();
		mav.addObject("userPositionList", userPositionList);
		mav.addObject("systemPositionList", systemPositionList);
		
		return mav;
	}
	
	@RequestMapping(value = "/addPostion", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addPostion(HttpServletRequest request){
		UserEntity user = UserUtils.getLoginUser(request);
		Long companyId = user.getCompanyId();
		PositionEntity position = new PositionEntity();
		String positionName = request.getParameter("postionName");
		String result = ResponseStatus.EXIST_Str;
		boolean isExist = positionService.isExistPosition(companyId.toString(), positionName);
		if(!isExist){
			position.setCompanyId(companyId);
			position.setPostionName(positionName);
			String id = positionService.insertPosition(position);
			position.setId(Long.parseLong(id));
			result = new Gson().toJson(position);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/getPostionPage", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getPostionPage(HttpServletRequest request){
		UserEntity user = UserUtils.getLoginUser(request);
		Long companyId = user.getCompanyId();
		PositionEntity position = new PositionEntity();
		String positionName = request.getParameter("postionName");
		String result = ResponseStatus.EXIST_Str;
		boolean isExist = positionService.isExistPosition(companyId.toString(), positionName);
		if(!isExist){
			position.setCompanyId(companyId);
			position.setPostionName(positionName);
			String id = positionService.insertPosition(position);
			position.setId(Long.parseLong(id));
			result = new Gson().toJson(position);
		}
		
		return result;
	}
}

