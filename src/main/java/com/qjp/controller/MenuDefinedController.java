package com.qjp.controller;

import java.util.LinkedList;
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

import com.qjp.base.ResponseStatus;
import com.qjp.entity.MenuDefinedEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.AuthorityService;
import com.qjp.service.MenuDefinedService;
import com.qjp.service.PositionService;
import com.qjp.service.UserService;
import com.qjp.util.UserUtils;
import com.qjp.util.query.AuthorityVOQuery;
import com.qjp.util.query.PositionQuery;
import com.qjp.util.query.UserQuery;

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

	@Autowired
	private AuthorityService authService;
	@Autowired
	private UserService userService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private MenuDefinedService menuDefinedService;
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/menudefined/menudefined_list");
		
		return mav;
	}
	
	@RequestMapping(value = "/defineByRole", method = RequestMethod.GET)
	public ModelAndView defineByRole(@ModelAttribute AuthorityVOQuery authQuery,HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/menudefined/menudefined_role_list");
		UserEntity user = UserUtils.getLoginUser(request);
		authQuery.setCompanyId(user.getCompanyId().toString());
		authQuery = authService.getAuthorityList(authQuery);
		mav.addObject("authQuery", authQuery);
		
		return mav;
	}
	
	@RequestMapping(value = "/defineByPosition", method = RequestMethod.GET)
	public ModelAndView defineByPosition(@ModelAttribute PositionQuery positionQuery,HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/menudefined/menudefined_position_list");
		UserEntity user = UserUtils.getLoginUser(request);
		Long companyId = user.getCompanyId();
		positionQuery.setCompanyId(companyId);
		positionQuery = positionService.getPositionPage(positionQuery);
		
		return mav;
	}
	@RequestMapping(value = "/defineByDept", method = RequestMethod.GET)
	public ModelAndView defineByDept(@ModelAttribute AuthorityVOQuery authQuery,HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/menudefined/menudefined_dept_list");
		UserEntity loginUser = UserUtils.getAdminLoginUser(request);
		mav.addObject("loginUser", loginUser); 
		
		return mav;
	}
	@RequestMapping(value = "/defineByUser", method = RequestMethod.GET)
	public ModelAndView defineByUser(@ModelAttribute UserQuery userQuery,HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/menudefined/menudefined_user_list");
		UserEntity loginUser = UserUtils.getAdminLoginUser(request);
		mav.addObject("loginUser", loginUser); 
		userQuery.setPage(1);
		userQuery.setSize(10);
		userQuery.setCompanyId(loginUser.getCompanyId().toString());
		userQuery = userService.getUserList(userQuery);
		mav.addObject("userQuery", userQuery);
		
		return mav;
	}
	
	@RequestMapping(value = "/saveForDept", method = RequestMethod.GET)
	@ResponseBody
	public Integer saveForDept(String idStr, String casecadeId, Integer defineType, HttpServletRequest request){
		Integer result = ResponseStatus.INIT;
		if(StringUtils.isNotBlank(casecadeId)){
			UserEntity loginUser = UserUtils.getAdminLoginUser(request);
			String[] menuIdArr = idStr.split("\\,");
			MenuDefinedEntity menuDefined = null;
			List<MenuDefinedEntity> mdList = new LinkedList<MenuDefinedEntity>();
			for (String menuId : menuIdArr) {
				menuDefined = new MenuDefinedEntity();
				menuDefined.setCompanyId(loginUser.getCompanyId().toString());
				menuDefined.setCreateUser(loginUser.getUserName());
				menuDefined.setDefinedCasecaseId(casecadeId);
				menuDefined.setDefinedType(defineType);
				menuDefined.setIsDelete(0);
				menuDefined.setMenuId(menuId);
				mdList.add(menuDefined);
			}
			
			menuDefinedService.insertMenuDefined(mdList);
			result = ResponseStatus.UPDATE_SUCCESS;
		}
		
		return result;
	}
}

