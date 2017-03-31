package com.qjp.controller;

import com.qjp.entity.SalesOppoProductEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.CustomerService;
import com.qjp.service.DepartmentService;
import com.qjp.service.LogService;
import com.qjp.service.SaleOppoProductService;
import com.qjp.service.SalesOpportunityService;
import com.qjp.service.UserService;
import com.qjp.util.UserUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/inner/sop")
public class SalesOppoProductController {
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private LogService logService;
	@Autowired
	private UserService userService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private SaleOppoProductService sopService;
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Integer saveOrUpdate(@RequestBody SalesOppoProductEntity sop, HttpServletRequest request){
		UserEntity user = UserUtils.getLoginUser(request);
		sop.setUserId(user.getId().toString());
		sop.setUserName(user.getUserName());
		sopService.insertSop(sop);
		return 1;
	}

}
