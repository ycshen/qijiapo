package com.qjp.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.qjp.base.UserStatus;
import com.qjp.entity.Constant;
import com.qjp.entity.DepartmentEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.ConfigService;
import com.qjp.service.DepartmentService;
import com.qjp.service.UserService;
import com.qjp.util.LogUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.ValidateUtils;
import com.qjp.util.query.UserQuery;

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
	
	@RequestMapping(value = "/forbidLogin", method = RequestMethod.GET)
	@ResponseBody
	public Integer forbidLogin(String id, String userName,  HttpServletRequest request){
		Integer result = ResponseStatus.ERROR;
		UserEntity loginUser = UserUtils.getLoginUser(request);
		if(StringUtils.isNotBlank(id)){
			String updateUser = loginUser.getUserName();
			userService.forbidLogin(id, updateUser);
			LogUtils.logAdmin("员工(" + userName + ")禁止登陆", loginUser);
			result = ResponseStatus.UPDATE_SUCCESS;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editUser(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/user/user_edit");
		UserEntity user = null;
		List<DepartmentEntity> departmentList = null;
		Integer editType = 1; //新增
		if(StringUtils.isNotBlank(id)){
			user = userService.getUserById(id);
			Long companyId = user.getCompanyId();
			departmentList = departmentService.getNoSubDeptListByCompanyId(companyId.toString());
			editType = 2;//编辑
		}
		
		mav.addObject("editType", editType);
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
			mav.addObject("editType", 1);
		}else{
			UserEntity loginUser = UserUtils.getLoginUser(request);
			Long companyId = loginUser.getCompanyId();
			List<DepartmentEntity> departmentList = departmentService.getListByCompanyId(companyId.toString());
			mav.addObject("departmentList", departmentList);
			mav.addObject("editType", 2);
		}
		
		
		
		return mav;
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView viewUser(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/user/user_detail");
		UserEntity user = null;
		if(StringUtils.isNotBlank(id)){
			user = userService.getUserById(id);
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
	public Integer isExistTelphone(String departmentId, String telphone, String userId, HttpServletRequest request){
		Integer result = Constant.EXIST;
		if(StringUtils.isNotBlank(telphone) && StringUtils.isNotBlank(departmentId)){
			boolean isExist = userService.isExistTelphone(departmentId, telphone, userId);
			result = isExist ? Constant.EXIST : Constant.NO_EXIST;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/changeCollapse", method = RequestMethod.GET)
	@ResponseBody
	public void changeCollapse(HttpServletRequest request){
		UserEntity loginUser = UserUtils.getLoginUser(request);
		userService.changeCollapse(loginUser.getId().toString(), "");
		if(loginUser.getIsCollapseMenu() == 0){
			loginUser.setIsCollapseMenu(1);
		}else{
			loginUser.setIsCollapseMenu(0);
		}
		
		HttpSession seesion = request.getSession();
		seesion.setAttribute("loginUser",loginUser);	
	}
	
	@RequestMapping(value = "/leave", method = RequestMethod.GET)
	@ResponseBody
	public Integer leave(String id, HttpServletRequest request){
		UserEntity loginUser = UserUtils.getLoginUser(request);
		if(StringUtils.isNotBlank(id)){
			UserEntity user = userService.getUserById(id);
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
			UserEntity user = userService.getUserById(id);
			user.setUpdateTime(new Date());
			user.setUpdateUser(loginUser.getUserName());
			user.setStatus(UserStatus.FORBIDDEN_INT);
			userService.updateUser(user);
		}
			
		return ResponseStatus.UPDATE_SUCCESS;
	}
	
	@RequestMapping(value = "/enable", method = RequestMethod.GET)
	@ResponseBody
	public Integer enableUser(String id, String userName, HttpServletRequest request){
		Integer result = ResponseStatus.ERROR;
		UserEntity loginUser = UserUtils.getLoginUser(request);
		if(StringUtils.isNotBlank(id)){
			String updateUser = loginUser.getUserName();
			userService.enableUser(id, updateUser);
			LogUtils.logAdmin("启用员工(" + userName + ")", loginUser);
			result = ResponseStatus.UPDATE_SUCCESS;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public ModelAndView reset(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/user/user_reset_pass");
		if(StringUtils.isNotBlank(id)){
			UserEntity user = userService.getUserById(id);
			mav.addObject("user", user);
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/resetByEmail", method = RequestMethod.GET)
	@ResponseBody
	public Integer resetByEmail(String id, String email, String userName, HttpServletRequest request){
		Integer result = ResponseStatus.ERROR;
		UserEntity loginUser = UserUtils.getLoginUser(request);
		if(StringUtils.isNotBlank(id) && ValidateUtils.isEmail(email)){
			userService.resetPassword(id, "", "2", email);
			LogUtils.logAdmin("邮件随机重置了员工(" + userName + ")密码", loginUser);
			result = ResponseStatus.UPDATE_SUCCESS;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/resetByPass", method = RequestMethod.GET)
	@ResponseBody
	public Integer resetByPass(String id, String password, String userName, HttpServletRequest request){
		Integer result = ResponseStatus.ERROR;
		UserEntity loginUser = UserUtils.getLoginUser(request);
		if(StringUtils.isNotBlank(id)){
			userService.resetPassword(id, password, "1", "");
			LogUtils.logAdmin("密码填写重置了员工(" + userName + ")密码", loginUser);
			result = ResponseStatus.UPDATE_SUCCESS;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/selectAllUser", method = RequestMethod.GET)
	public ModelAndView selectAllUser(String id, String name, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/user/user_allcompany_select");
		UserEntity loginUser = UserUtils.getLoginUser(request);
		List<UserEntity> userList = userService.getUserListByCompanyId(loginUser.getCompanyId().toString());
		mav.addObject("userList", userList);
		mav.addObject("id", id);
		mav.addObject("name", name);
		
		return mav;
	}
	@RequestMapping(value = "/selectAllUserAttn", method = RequestMethod.GET)
	public ModelAndView selectAllUserAttn(String id, String name, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/user/user_allcompany_attn_select");
		UserEntity loginUser = UserUtils.getLoginUser(request);
		List<UserEntity> userList = userService.getUserListByCompanyId(loginUser.getCompanyId().toString());
		mav.addObject("userList", userList);
		mav.addObject("id", id);
		mav.addObject("name", name);

		return mav;
	}
	
}

