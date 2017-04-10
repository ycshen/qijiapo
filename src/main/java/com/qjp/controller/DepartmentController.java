package com.qjp.controller;

import com.qjp.base.ResponseStatus;
import com.qjp.entity.CompanyEntity;
import com.qjp.entity.DepartmentEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.CompanyService;
import com.qjp.service.DepartmentService;
import com.qjp.service.UserService;
import com.qjp.util.LogUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.DepartmentQuery;
import com.qjp.util.query.UserQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: DepartmentController.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Controller
@RequestMapping("/inner/department")
public class DepartmentController extends BaseController{
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public String saveOrUpdate(@ModelAttribute DepartmentEntity department, String nodeType, HttpServletRequest request){
		String result = ResponseStatus.INIT_Str;
		String departmentName = department.getDepartmentName();
		UserEntity adminLoginUser = UserUtils.getAdminLoginUser(request);
		Long companyId = adminLoginUser.getCompanyId();
		Boolean isCompany = true;
		if(StringUtils.isNotBlank(nodeType) && "3".equals(nodeType)){
			isCompany = false;
		}
		
		Long id = department.getId();
		String departmentId = "";
		if(id != null){
			departmentId = id.toString();
		}
		
		boolean isExist = departmentService.isExistDepartment(departmentName, companyId.toString(), isCompany, departmentId);
		if(isExist){
			result = ResponseStatus.EXIST_Str;
		}else{
			UserEntity user = UserUtils.getLoginUser(request);
			if(id == null){
				department.setStatus(0);
				department.setCreateUser(user.getUserName());
				department.setCompanyId(companyId);
				department.setCompanyName(adminLoginUser.getCompanyName());
				String newId = departmentService.insertDepartment(department);
				LogUtils.logAdmin("新增部门信息：" + department.toLogString(), user);
				result = ResponseStatus.INSERT_SUCCESS_Str + "_id" + newId;
			}else{
				department.setUpdateTime(new Date());
				department.setUpdateUser(user.getUserName());
				departmentService.updateDepartment(department);
				result = ResponseStatus.UPDATE_SUCCESS_Str;
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editDepartment(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/department/department_edit");
		DepartmentEntity department = null;
		if(StringUtils.isNotBlank(id)){
			department = departmentService.getDepartmentById(Integer.parseInt(id));
		}
		
		mav.addObject("department", department);
		
		return mav;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addDepartment(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/department/department_add");
		CompanyEntity company = null;
		if(StringUtils.isNotBlank(id)){
			company = companyService.getCompanyById(Long.parseLong(id));
		}
		
		mav.addObject("company", company);
		
		return mav;
	}
	
	@RequestMapping(value = "/addSub", method = RequestMethod.GET)
	public ModelAndView addSubDepartment(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/department/department_add");
		if(StringUtils.isNotBlank(id)){
			DepartmentEntity department = departmentService.getDepartmentById(Integer.parseInt(id));
			CompanyEntity company = companyService.getCompanyById(department.getCompanyId());
			mav.addObject("company", company);
			mav.addObject("parentDepartment", department);
		}
		
		
		
		return mav;
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView viewDepartment(String id, UserQuery userQuery, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/department/department_detail");
		DepartmentEntity department = null;
		if(StringUtils.isNotBlank(id)){
			department = departmentService.getDepartmentById(Integer.parseInt(id));
			userQuery.setDepartmentId(id);
			userQuery = userService.getUserList(userQuery);
			
		}
		
		mav.addObject("department", department);
		mav.addObject("userQuery", userQuery);
		
		return mav;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listDepartment(@ModelAttribute DepartmentQuery departmentQuery, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/department/department_list");
		departmentQuery = departmentService.getDepartmentList(departmentQuery);
		mav.addObject("departmentQuery", departmentQuery);
		
		return mav;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public Integer delete(String id, HttpServletRequest request){
		Integer result = ResponseStatus.ERROR;
		if(StringUtils.isNotBlank(id)){
			UserEntity adminLoginUser = UserUtils.getAdminLoginUser(request);
			Long companyId = adminLoginUser.getCompanyId();
			departmentService.deleteDepartmentById(id, companyId.toString());
			result = ResponseStatus.UPDATE_SUCCESS;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/cascade", method = RequestMethod.GET)
	public ModelAndView cascade(String userId, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/department/department_cascade");
		if(StringUtils.isNotBlank(userId)){
			UserEntity user = userService.getUserById(userId);
			Long companyId = user.getCompanyId();
			List<DepartmentEntity> departmentList = departmentService.getAllDepByCompanyId(companyId.toString());
			mav.addObject("departmentList", departmentList);
			mav.addObject("userId", userId);
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/cascadeDepartment", method = RequestMethod.GET)
	@ResponseBody
	public Integer cascade(String userId, String departmentId, String departmentName, HttpServletRequest request){
		Integer response = ResponseStatus.INIT;
		if(StringUtils.isNotBlank(userId)){
			UserEntity user = userService.getUserById(userId);
			user.setDepartmentId(Integer.parseInt(departmentId));
			userService.updateUser(user);
			UserEntity loginUser = UserUtils.getAdminLoginUser(request);
			LogUtils.logAdmin("关联员工【" + user.getUserName() + "】部门为【" + departmentName + "】", loginUser);
			response = ResponseStatus.UPDATE_SUCCESS;
		}
		
		return response;
	}
}

