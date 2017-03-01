package com.qjp.controller;

import java.util.Date;
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

import com.google.gson.Gson;
import com.qjp.base.ResponseStatus;
import com.qjp.entity.CompanyEntity;
import com.qjp.entity.RoleEntity;
import com.qjp.entity.RoleUserEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.CompanyService;
import com.qjp.service.RoleService;
import com.qjp.service.UserService;
import com.qjp.util.JsonUtils;
import com.qjp.util.LogUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.RoleVOQuery;
import com.qjp.util.query.UserRoleQuery;
import com.qjp.util.vo.RoleVO;
import com.qjp.util.vo.UserRoleVO;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: RoleController.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @roleor <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Controller
@RequestMapping("/inner/role")
public class RoleController extends BaseController{
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@RequestMapping(value = "/listAsync", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String listAsync(@ModelAttribute RoleVOQuery roleQuery, HttpServletRequest request){
		String result = StringUtils.EMPTY;
		UserEntity user = UserUtils.getLoginUser(request);
		roleQuery.setCompanyId(user.getCompanyId().toString());
		roleQuery = roleService.getRoleList(roleQuery);
		List<RoleVO> list = roleQuery.getItems();
		if(list != null && list.size() > 0){
			result = new Gson().toJson(list);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ModelAndView list(@ModelAttribute RoleVOQuery roleQuery, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/role/role_list");
		UserEntity user = UserUtils.getLoginUser(request);
		roleQuery.setCompanyId(user.getCompanyId().toString());
		roleQuery = roleService.getRoleList(roleQuery);
		mav.addObject("roleQuery", roleQuery);
		
		return mav;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editCompany(String id){
		ModelAndView mav = new ModelAndView("/company/sub_company_edit");
		CompanyEntity company = null;
		if(StringUtils.isNotBlank(id)){
			Long companyId = Long.parseLong(id);
			company = companyService.getCompanyById(companyId);
		}
		mav.addObject("company", company);
		
		return mav;
	}
	
	@RequestMapping(value = "/roleUserList", method = RequestMethod.GET)
	public ModelAndView roleUserList(UserRoleQuery userRoleQuery, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/role/role_user_list");
		RoleEntity role = null;
		String roleId = userRoleQuery.getRoleId();
		if(StringUtils.isNotBlank(roleId)){
			UserEntity loginUser = UserUtils.getLoginUser(request);
			String companyId = loginUser.getCompanyId().toString();
			userRoleQuery.setCompanyId(companyId);
			role = roleService.getRoleById(roleId);
			userRoleQuery = userService.getUserListByRoleId(userRoleQuery);
		}
		mav.addObject("role", role);
		mav.addObject("userRoleQuery", userRoleQuery);
		
		return mav;
	}
	
	@RequestMapping(value = "/roleUserListAsyc", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String roleUserListAsyc(UserRoleQuery userRoleQuery, HttpServletRequest request){
		String roleId = userRoleQuery.getRoleId();
		if(StringUtils.isNotBlank(roleId)){
			UserEntity loginUser = UserUtils.getLoginUser(request);
			String companyId = loginUser.getCompanyId().toString();
			userRoleQuery.setCompanyId(companyId);
			userRoleQuery = userService.getUserListByRoleId(userRoleQuery);
		}
		
		return JsonUtils.json2Str(userRoleQuery);
	}
	
	@RequestMapping(value = "/roleUserPage", method = RequestMethod.GET)
	public ModelAndView roleUserPage(UserRoleQuery userRoleQuery, HttpServletRequest request){
		String roleId = userRoleQuery.getRoleId();
		ModelAndView mav = new ModelAndView("/role/role_user");
		List<UserRoleVO> roleUserList = new LinkedList<UserRoleVO>();
		if(StringUtils.isNotBlank(roleId)){
			UserEntity loginUser = UserUtils.getLoginUser(request);
			String companyId = loginUser.getCompanyId().toString();
			List<UserRoleVO> roleList = userService.getRoleUserByCidAndRoleId(companyId, roleId, "1");
			List<UserRoleVO> notRoleList = userService.getRoleUserByCidAndRoleId(companyId, roleId, "2");
			if(roleList != null && roleList.size() > 0){
				roleUserList.addAll(roleList);
			}
			
			if(notRoleList != null && notRoleList.size() > 0){
				roleUserList.addAll(notRoleList);
			}
			
			if(roleUserList != null && roleUserList.size() > 0){
				String roleStr = "";
				String notRoleStr = "";
				Integer roleCount = 0;
				Integer notRoleCount = 0;
				for (UserRoleVO userRole : roleUserList) {
					Integer roleUserId = userRole.getRoleUserId();
					if(roleUserId != null && roleUserId.toString().equals(roleId)){
						roleCount ++;
						roleStr += userRole.getId() + "^";
					}else{
						notRoleCount ++;
						notRoleStr += userRole.getId() + "^";
					}
				}
				if(StringUtils.isNotBlank(roleStr)){
					roleStr = roleStr.substring(0, roleStr.length() - 1);
				}
				if(StringUtils.isNotBlank(notRoleStr)){
					notRoleStr = notRoleStr.substring(0, notRoleStr.length() - 1);
				}
				mav.addObject("roleStr", roleStr);
				mav.addObject("notRoleStr", notRoleStr);
				mav.addObject("roleCount", roleCount);
				mav.addObject("notRoleCount", notRoleCount);
			}
			mav.addObject("roleUserList", roleUserList);
		}
		
		mav.addObject("userRoleQuery", userRoleQuery);
		
		return mav;
	}
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Integer saveOrUpdateCompany(@ModelAttribute CompanyEntity company, HttpServletRequest request){
		Integer result = 0;
		try{
			Long id = company.getId();
			UserEntity user = UserUtils.getLoginUser(request);
			if(id == null){
				company.setCreateUser(user.getUserName());
				company.setParentCompanyId(user.getCompanyId().toString());
				company.setParentCompanyName(user.getCompanyName());
				companyService.insertCompany(company);
				LogUtils.logAdmin("新增分公司信息：" + company.toLogString(), user);
				result = 1;
			}else{
				CompanyEntity oldCompany = companyService.getCompanyById(id);
				String oldCompanyStr = oldCompany.toLogString();
				oldCompany.setUpdateTime(new Date());
				oldCompany.setCompanyAddress(company.getCompanyAddress());
				oldCompany.setCompanyCeo(company.getCompanyCeo());
				oldCompany.setCompanyName(company.getCompanyName());
				oldCompany.setCompanySite(company.getCompanySite());
				oldCompany.setCompanyTelephone(company.getCompanyTelephone());
				oldCompany.setUpdateUser(user.getUserName());
				companyService.updateCompany(oldCompany);
				LogUtils.logAdmin("更新分公司信息,更新前：" + oldCompanyStr + "，更新后：" + oldCompany.toLogString(), user);
				result = 2;
			}
		}catch(Exception e){
			//暂时不记录监控
			e.printStackTrace();
			result = 0;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public Integer deleteCompany(@ModelAttribute String id, HttpServletRequest request){
		Integer result = 0;
		if(StringUtils.isNotBlank(id)){
			companyService.deleteCompany(id);
			result = 1;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/cancelRole", method = RequestMethod.GET)
	@ResponseBody
	public Integer cancelRole(String idList, String roleId, String userList, String roleName, HttpServletRequest request){
		Integer result = 0;
		if(StringUtils.isNotBlank(idList) && StringUtils.isNotBlank(roleId)){
			
			UserEntity user = UserUtils.getLoginUser(request);
			String[] userIdArr = idList.split("\\^");
			RoleUserEntity role = null;
			List<RoleUserEntity> roleUserList = new LinkedList<RoleUserEntity>();
			for (String id : userIdArr) {
				role = new RoleUserEntity();
				role.setRoleId(Integer.parseInt(roleId));
				role.setCompanyId(user.getCompanyId().intValue());
				role.setId(Integer.parseInt(id));
				roleUserList.add(role);
			}
			
			roleService.cancelRole(roleUserList);
			LogUtils.logAdmin("取消【" + userList +"】的权限【"+ roleName+ "】", user);
			result = 1;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	@ResponseBody
	public Integer activateCompany(@ModelAttribute String id, HttpServletRequest request){
		Integer result = 0;
		if(StringUtils.isNotBlank(id)){
			companyService.activateCompany(id);
			result = 1;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/addRole", method = RequestMethod.GET)
	@ResponseBody
	public Integer addRole(String roleStr, String notRoleStr, String roleId, HttpServletRequest request){
		Integer result = 0;
		if(StringUtils.isNotBlank(roleId)){
			UserEntity user = UserUtils.getLoginUser(request);
			String companyId = user.getCompanyId().toString();
			roleService.batchRole(companyId, roleId, roleStr, notRoleStr);
			result = ResponseStatus.INSERT_SUCCESS;
		}
		
		return result;
	}
}

