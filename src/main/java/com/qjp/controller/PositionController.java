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
@RequestMapping("/inner/position")
public class PositionController extends BaseController{
	@Autowired
	private PositionService positionService;
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editPosition(String id){
		ModelAndView mav = new ModelAndView("/position/position_edit");
		PositionEntity position = null;
		if(StringUtils.isNotBlank(id)){
			Long poId = Long.parseLong(id);
			position = positionService.getPositionById(poId.toString());
		}
		
		mav.addObject("position", position);
		
		return mav;
	}
	
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
	
	@RequestMapping(value = "/updatePostion", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updatePostion(HttpServletRequest request){
		UserEntity user = UserUtils.getLoginUser(request);
		Long companyId = user.getCompanyId();

		String positionName = request.getParameter("postionName");
		String id = request.getParameter("id");
		PositionEntity position = positionService.getPositionById(id);
		String result = ResponseStatus.UPDATE_SUCCESS_Str;
		if(StringUtils.isNotBlank(positionName) && !positionName.equals(position.getPostionName())){
			boolean isExist = positionService.isExistPosition(companyId.toString(), positionName);
			if(!isExist){
				position.setPostionName(positionName);
				positionService.updatePosition(position);
				result = new Gson().toJson(position);
			}else{
				result = ResponseStatus.EXIST_Str;
			}
		}
		
		
		
		
		return result;
	}
	@RequestMapping(value = "/listInit", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ModelAndView list(@ModelAttribute PositionQuery positionQuery, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/position/position_page_list");
		UserEntity user = UserUtils.getLoginUser(request);
		Long companyId = user.getCompanyId();
		positionQuery.setCompanyId(companyId.toString());
		positionQuery = positionService.getPositionPage(positionQuery);
		
		return mav;
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getPostionPage(@ModelAttribute PositionQuery positionQuery, HttpServletRequest request){
		UserEntity user = UserUtils.getLoginUser(request);
		Long companyId = user.getCompanyId();
		positionQuery.setCompanyId(companyId.toString());
		positionQuery = positionService.getPositionPage(positionQuery);
		String result = new Gson().toJson(positionQuery);
		
		return result;
	}
	
	@RequestMapping(value = "/stop", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Integer stopPosition(String id, String positionName, HttpServletRequest request){
		UserEntity user = UserUtils.getLoginUser(request);
		Integer isSuccess = ResponseStatus.ERROR;
		if(StringUtils.isNotBlank(id)){
			boolean result = positionService.changePositionStatus(id, "1");
			if(result){
				LogUtils.logAdmin("停用了职位【" + positionName + "】", user);
			}
			isSuccess = result ? ResponseStatus.UPDATE_SUCCESS : ResponseStatus.ERROR;
			
			
		}
		
		
		return isSuccess;
	}
	
	@RequestMapping(value = "/start", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Integer startPosition(String id, String positionName, HttpServletRequest request){
		UserEntity user = UserUtils.getLoginUser(request);
		Integer isSuccess = ResponseStatus.ERROR;
		if(StringUtils.isNotBlank(id)){
			boolean result = positionService.changePositionStatus(id, "0");
			if(result){
				LogUtils.logAdmin("启用了职位【" + positionName + "】", user);
			}
			isSuccess = result ? ResponseStatus.UPDATE_SUCCESS : ResponseStatus.ERROR;
			
		}
		
		
		return isSuccess;
	}
	
}

