package com.qjp.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qjp.base.ResponseStatus;
import com.qjp.base.RoleEnum;
import com.qjp.entity.CustomerEntity;
import com.qjp.entity.DepartmentEntity;
import com.qjp.entity.LogEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.CustomerService;
import com.qjp.service.DepartmentService;
import com.qjp.service.LogService;
import com.qjp.service.UserService;
import com.qjp.util.JsonUtils;
import com.qjp.util.LogUtils;
import com.qjp.util.StringUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.CustomerQuery;
import com.qjp.util.query.LogQuery;

@Controller
@RequestMapping("/inner/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private LogService logService;
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ModelAndView list(@ModelAttribute CustomerQuery customerQuery, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/customer/customer_list");
		mav.addObject("customerQuery", customerQuery);
		return mav;
	}
	
	
	@RequestMapping(value = "/listAjax", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String listAjax(@ModelAttribute CustomerQuery customerQuery, HttpServletRequest request){
		customerQuery.init(request);
		String roleType = customerQuery.getRoleType();
		if(RoleEnum.DEP.getRoleId().toString().equals(roleType)){
			String idList = departmentService.getSubDepList(customerQuery.getDepartmentId(), customerQuery.getCompanyId());
			customerQuery.setDepartmentId(idList);
		}
		
		customerQuery = customerService.getCustomerPage(customerQuery);
		String jsonStr = JsonUtils.json2Str(customerQuery);
		
		return jsonStr;
	}
	@RequestMapping(value = "/forwardEdit", method = RequestMethod.GET)
	public ModelAndView forwardEdit(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/customer/customer_edit");
		UserEntity user = UserUtils.getLoginUser(request);
		CustomerEntity customer = null;
		if(StringUtils.isNotBlank(id)){
			customer = customerService.getCustomerById(id);
			if(customer != null){
				String departmentId = customer.getDepartmentId();
				if(StringUtils.isNotBlank(departmentId)){
					if(departmentId.contains("-")){
						customer.setDepartmentName("全公司");
					}else{
						DepartmentEntity department = departmentService.getDepartmentById(Integer.parseInt(departmentId));
						if(department != null){
							customer.setDepartmentName(department.getDepartmentName());
						}
					}
				}
				
			}
		}
		
		mav.addObject("customer", customer);
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/customer/customer_detail");
		UserEntity user = UserUtils.getLoginUser(request);
		mav.addObject("user", user);
		CustomerEntity customer = customerService.getCustomerById(id);
		if(customer != null){
			String departmentId = customer.getDepartmentId();
			if(StringUtils.isNotBlank(departmentId)){
				if(departmentId.contains("-")){
					customer.setDepartmentName("全公司");
				}else{
					DepartmentEntity department = departmentService.getDepartmentById(Integer.parseInt(departmentId));
					if(department != null){
						customer.setDepartmentName(department.getDepartmentName());
					}
				}
			}
			
		}
		mav.addObject("customer", customer);
		LogQuery logQuery = new LogQuery();
		logQuery.setCasecadeId(id);
		logQuery.setLogType("3");
		logQuery.setCompanyId(user.getCompanyId().toString());
		logQuery = logService.getLogPage(logQuery);
		List<LogEntity> logList = logQuery.getItems();
		mav.addObject("logList", logList);
		
		return mav;
	}
	
	@RequestMapping(value = "/deleteById", method = RequestMethod.GET)
	@ResponseBody
	public Integer deleteById(String id, String name,HttpServletRequest request){
		Integer result = ResponseStatus.INIT;
		if(StringUtils.isNotBlank(id)){
			customerService.deleteCustomerById(id);
			UserEntity user = UserUtils.getLoginUser(request);
			LogUtils.log(3, "删除了客户", id, "CRM-客户ID", user);
			result = ResponseStatus.UPDATE_SUCCESS;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/transferCustomer", method = RequestMethod.GET)
	@ResponseBody
	public Integer transferCustomer(String userId, String customerId, String transferType, HttpServletRequest request){
		Integer result = ResponseStatus.INIT;
		if(StringUtils.isNotBlank(userId)){
			UserEntity transferToUser = userService.getUserById(userId);
			UserEntity loginUser = UserUtils.getLoginUser(request);
			if("1".equals(transferType)){
				this.transferCustomer(customerId, transferToUser, loginUser);
			}else if("2".equals(transferType)){
				String[] idArr = customerId.split("\\,");
				for (String id : idArr) {
					this.transferCustomer(id, transferToUser, loginUser);
				}
			}
			
			result = ResponseStatus.UPDATE_SUCCESS;
			
		}
		
		return result;
	}
	
	private void transferCustomer(String customerId, UserEntity transferToUser, UserEntity loginUser){
		CustomerEntity oldCustomer = customerService.getCustomerById(customerId);
		oldCustomer.setUserId(transferToUser.getId().toString());
		String transferUserName = transferToUser.getUserName();
		oldCustomer.setUserName(transferUserName);
		oldCustomer.setUpdateTime(new Date());
		oldCustomer.setUpdateUser(loginUser.getUserName());	}
	
	@RequestMapping(value = "/batchDeleteById", method = RequestMethod.GET)
	@ResponseBody
	public Integer batchDeleteById(String ids, HttpServletRequest request){
		Integer result = ResponseStatus.INIT;
		if(StringUtils.isNotBlank(ids)){
			String[] idArr = ids.split("\\,");
			List<String> idList =  Arrays.asList(idArr);
			customerService.batchDeleteCustomer(idList);
			
			result = ResponseStatus.UPDATE_SUCCESS;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(@ModelAttribute CustomerEntity customer, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/customer/customer_edit");
		customer.init(request); //初始化公司、部门、用户信息
		String provinceId = customer.getProvinceId();
		customer.setProvinceId(StringUtils.splitLocation(provinceId));
		String cityId = customer.getCityId();
		customer.setCityId(StringUtils.splitLocation(cityId));
		String areaId = customer.getAreaId();
		customer.setAreaId(StringUtils.splitLocation(areaId));
		Long id = customer.getId();
		UserEntity user = UserUtils.getLoginUser(request);
		if(id == null){
			String returnId = customerService.insertCustomer(customer);
			LogUtils.log(3, "添加了新客户", returnId, "CRM-客户ID", user);
		}else{
			customerService.updateCustomer(customer);
			LogUtils.log(3, "更新了新客户", customer.getId().toString(), "CRM-客户ID", user);
		}

		return mav;
	}
	
}
