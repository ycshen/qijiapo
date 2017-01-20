package com.qjp.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qjp.base.Config;
import com.qjp.base.ResponseStatus;
import com.qjp.base.UserStatus;
import com.qjp.entity.ConfigEntity;
import com.qjp.entity.Constant;
import com.qjp.entity.DepartmentEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.ConfigService;
import com.qjp.service.DepartmentService;
import com.qjp.service.UserService;
import com.qjp.util.LogUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.UserQuery;
import com.google.gson.Gson;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: UserController.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Controller
@RequestMapping("/inner/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private ConfigService configService;
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Integer saveOrUpdate(@ModelAttribute UserEntity user, HttpServletRequest request){
		Integer result = 0;
		Long id = user.getId();
		UserEntity loginUser = UserUtils.getLoginUser(request);
		if(id == null){
			user.setCreateUser(loginUser.getUserName());
			String initPass = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
			user.setCompanyId(loginUser.getCompanyId());
			user.setCompanyName(user.getCompanyName());
			user.setPassword(initPass);
			user.setStatus(UserStatus.NORMAL_INT);
			userService.insertUser(user);
			
			result = 1;
		}else{
			user.setUpdateTime(new Date());
			user.setUpdateUser(loginUser.getUserName());
			userService.updateUser(user);
			result = 2;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/forbidUser", method = RequestMethod.GET)
	@ResponseBody
	public Integer forbidUser(String id, String userName,  HttpServletRequest request){
		Integer result = ResponseStatus.ERROR;
		UserEntity loginUser = UserUtils.getLoginUser(request);
		if(StringUtils.isNotBlank(id)){
			String updateUser = loginUser.getUserName();
			userService.forbidUser(id, updateUser);
			LogUtils.logAdmin("停用员工(" + userName + ")", loginUser);
			result = ResponseStatus.UPDATE_SUCCESS;
		}
		
		return result;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editUser(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/user/user_edit");
		UserEntity user = null;
		List<DepartmentEntity> departmentList = null;
		if(StringUtils.isNotBlank(id)){
			user = userService.getUserById(Integer.parseInt(id));
			Long companyId = user.getCompanyId();
			departmentList = departmentService.getListByCompanyId(companyId.toString());
		}
		
		mav.addObject("user", user);
		mav.addObject("departmentList", departmentList);
		
		return mav;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addUser(String did, HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/user_edit");
		DepartmentEntity department = null;
		if(StringUtils.isNotBlank(did)){
			department = departmentService.getDepartmentById(Integer.parseInt(did));
			mav.addObject("department", department);
		}else{
			UserEntity loginUser = UserUtils.getLoginUser(request);
			Long companyId = loginUser.getCompanyId();
			List<DepartmentEntity> departmentList = departmentService.getListByCompanyId(companyId.toString());
			mav.addObject("departmentList", departmentList);
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView viewUser(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/user/user_detail");
		UserEntity user = null;
		if(StringUtils.isNotBlank(id)){
			user = userService.getUserById(Integer.parseInt(id));
		}
		
		mav.addObject("user", user);
		
		return mav;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listUser(@ModelAttribute UserQuery userQuery, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/user/user_list");
		userQuery = userService.getUserList(userQuery);
		mav.addObject("userQuery", userQuery);
		
		return mav;
	}
	
	@RequestMapping(value = "/getUserListByDepartmentId", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getUserListByDepartmentId(@ModelAttribute UserQuery userQuery, HttpServletRequest request){
		String result = StringUtils.EMPTY;
		List<UserEntity> list = userService.getUserPage(userQuery).getItems();
		if(list != null && list.size() > 0){
			result = new Gson().toJson(list);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/isExistTelphone", method = RequestMethod.GET)
	@ResponseBody
	public Integer isExistTelphone(String departmentId, String telphone, HttpServletRequest request){
		Integer result = Constant.EXIST;
		if(StringUtils.isNotBlank(telphone) && StringUtils.isNotBlank(departmentId)){
			boolean isExist = userService.isExistTelphone(departmentId, telphone);
			result = isExist ? Constant.EXIST : Constant.NO_EXIST;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/leave", method = RequestMethod.GET)
	@ResponseBody
	public Integer leave(String id, HttpServletRequest request){
		UserEntity loginUser = UserUtils.getLoginUser(request);
		if(StringUtils.isNotBlank(id)){
			UserEntity user = userService.getUserById(Integer.parseInt(id));
			user.setUpdateTime(new Date());
			user.setUpdateUser(loginUser.getUserName());
			user.setStatus(UserStatus.LEAVE_INT);
			userService.updateUser(user);
		}
			
		return ResponseStatus.UPDATE_SUCCESS;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public Integer deleteUser(String id, HttpServletRequest request){
		UserEntity loginUser = UserUtils.getLoginUser(request);
		if(StringUtils.isNotBlank(id)){
			UserEntity user = userService.getUserById(Integer.parseInt(id));
			user.setUpdateTime(new Date());
			user.setUpdateUser(loginUser.getUserName());
			user.setStatus(UserStatus.FORBIDDEN_INT);
			userService.updateUser(user);
		}
			
		return ResponseStatus.UPDATE_SUCCESS;
	}
}

