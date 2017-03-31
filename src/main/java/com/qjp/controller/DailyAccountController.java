package com.qjp.controller;

import com.qjp.base.ResponseStatus;
import com.qjp.entity.DailyAccountEntity;
import com.qjp.entity.LogEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.DailyAccountService;
import com.qjp.service.DepartmentService;
import com.qjp.service.LogService;
import com.qjp.service.UserService;
import com.qjp.util.JsonUtils;
import com.qjp.util.StringUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.DailyAccountQuery;
import com.qjp.util.query.LogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/inner/account")
public class DailyAccountController {
	@Autowired
	private DailyAccountService dailyAccountService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private LogService logService;
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ModelAndView list(@ModelAttribute DailyAccountQuery dailyAccountQuery, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/dailyAccount/dailyAccount_list");
		mav.addObject("dailyAccountQuery", dailyAccountQuery);
		return mav;
	}
	
	
	@RequestMapping(value = "/listAjax", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String listAjax(@ModelAttribute DailyAccountQuery dailyAccountQuery, HttpServletRequest request){
		dailyAccountQuery.init(request);
		dailyAccountQuery = dailyAccountService.getDailyAccountPage(dailyAccountQuery);
		String jsonStr = JsonUtils.json2Str(dailyAccountQuery);
		
		return jsonStr;
	}
	@RequestMapping(value = "/forwardEdit", method = RequestMethod.GET)
	public ModelAndView forwardEdit(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/dailyAccount/dailyAccount_edit");
		UserEntity user = UserUtils.getLoginUser(request);
		DailyAccountEntity dailyAccount = null;
		if(StringUtils.isNotBlank(id)){
			dailyAccount = dailyAccountService.getDailyAccountById(id);
		}
		
		mav.addObject("dailyAccount", dailyAccount);
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/dailyAccount/dailyAccount_detail");
		UserEntity user = UserUtils.getLoginUser(request);
		mav.addObject("user", user);
		DailyAccountEntity dailyAccount = dailyAccountService.getDailyAccountById(id);
		mav.addObject("dailyAccount", dailyAccount);
		LogQuery logQuery = new LogQuery();
		logQuery.setCasecadeId(id);
		logQuery.setLogType("2");
		logQuery.setSize(30);
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
			dailyAccountService.deleteDailyAccountById(id);
			UserEntity user = UserUtils.getLoginUser(request);
			result = ResponseStatus.UPDATE_SUCCESS;
		}
		
		return result;
	}

	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(@ModelAttribute DailyAccountEntity dailyAccount, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/dailyAccount/dailyAccount_edit");
		UserEntity user = UserUtils.getLoginUser(request);
		dailyAccount.setCompanyId(user.getCompanyId().toString());
		dailyAccount.setUserId(user.getId().toString());
		String id = dailyAccount.getId();
		if(StringUtils.isBlank(id)){
			String returnId = dailyAccountService.insertDailyAccount(dailyAccount);
		}else{
			dailyAccountService.updateDailyAccount(dailyAccount);
		}

		return mav;
	}
	
}
