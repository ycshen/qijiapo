package com.qjp.controller;

import com.qjp.base.ResponseStatus;
import com.qjp.base.RoleEnum;
import com.qjp.entity.CustomerEntity;
import com.qjp.entity.LogEntity;
import com.qjp.entity.SalesOpportunityEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.*;
import com.qjp.util.JsonUtils;
import com.qjp.util.LogUtils;
import com.qjp.util.StringUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.LogQuery;
import com.qjp.util.query.SalesOpportunityQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/inner/salesOpportunity")
public class SalesOpportunityController {
	@Autowired
	private SalesOpportunityService salesOpportunityService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private LogService logService;
	@Autowired
	private UserService userService;
	@Autowired
	private CustomerService customerService;
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ModelAndView list(@ModelAttribute SalesOpportunityQuery salesOpportunityQuery, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/salesOpportunity/salesOpportunity_list");
		mav.addObject("salesOpportunityQuery", salesOpportunityQuery);
		return mav;
	}
	
	
	@RequestMapping(value = "/listAjax", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String listAjax(@ModelAttribute SalesOpportunityQuery salesOpportunityQuery, HttpServletRequest request){
		salesOpportunityQuery.init(request);
		String roleType = salesOpportunityQuery.getRoleType();
		if(RoleEnum.DEP.getRoleId().toString().equals(roleType)){
			String idList = departmentService.getSubDepList(salesOpportunityQuery.getDepartmentId(), salesOpportunityQuery.getCompanyId());
			salesOpportunityQuery.setDepartmentId(idList);
		}
		
		salesOpportunityQuery = salesOpportunityService.getSalesOpportunityPage(salesOpportunityQuery);
		String jsonStr = JsonUtils.json2Str(salesOpportunityQuery);
		
		return jsonStr;
	}
	@RequestMapping(value = "/forwardEdit", method = RequestMethod.GET)
	public ModelAndView forwardEdit(String id, String customerId, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/salesOpportunity/salesOpportunity_edit");
		UserEntity user = UserUtils.getLoginUser(request);
		SalesOpportunityEntity salesOpportunity = null;
		if(StringUtils.isNotBlank(id)){
			salesOpportunity = salesOpportunityService.getSalesOpportunityById(id);
		}

		if(StringUtils.isNotBlank(customerId)){
			CustomerEntity customer = customerService.getCustomerById(customerId);
			mav.addObject("customer", customer);
		}
		
		mav.addObject("salesOpportunity", salesOpportunity);
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/salesOpportunity/salesOpportunity_detail");
		UserEntity user = UserUtils.getLoginUser(request);
		mav.addObject("user", user);
		SalesOpportunityEntity salesOpportunity = salesOpportunityService.getSalesOpportunityById(id);
		mav.addObject("salesOpportunity", salesOpportunity);
		String customerId = salesOpportunity.getCustomerId();
		if(StringUtils.isNotBlank(customerId)){
			CustomerEntity customer = customerService.getCustomerById(customerId);
			mav.addObject("customer", customer);
		}
		LogQuery logQuery = new LogQuery();
		logQuery.setCasecadeId(id);
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
			salesOpportunityService.deleteSalesOpportunityById(id);
			UserEntity user = UserUtils.getLoginUser(request);
			result = ResponseStatus.UPDATE_SUCCESS;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/transferSalesOpportunity", method = RequestMethod.GET)
	@ResponseBody
	public Integer transferSalesOpportunity(String userId, String salesOpportunityId, String transferType, HttpServletRequest request){
		Integer result = ResponseStatus.INIT;
		if(StringUtils.isNotBlank(userId)){
			UserEntity transferToUser = userService.getUserById(userId);
			UserEntity loginUser = UserUtils.getLoginUser(request);
			if("1".equals(transferType)){
				this.transferSalesOpportunity(salesOpportunityId, transferToUser, loginUser);
			}else if("2".equals(transferType)){
				String[] idArr = salesOpportunityId.split("\\,");
				for (String id : idArr) {
					this.transferSalesOpportunity(id, transferToUser, loginUser);
				}
			}
			
			result = ResponseStatus.UPDATE_SUCCESS;
			
		}
		
		return result;
	}
	
	private void transferSalesOpportunity(String salesOpportunityId, UserEntity transferToUser, UserEntity loginUser){
		SalesOpportunityEntity oldSalesOpportunity = salesOpportunityService.getSalesOpportunityById(salesOpportunityId);
		oldSalesOpportunity.setUserId(transferToUser.getId().toString());
		String transferUserName = transferToUser.getUserName();
		oldSalesOpportunity.setUserName(transferUserName);
		oldSalesOpportunity.setUpdateTime(new Date());
		oldSalesOpportunity.setUpdateUser(loginUser.getUserName());	}
	
	@RequestMapping(value = "/batchDeleteById", method = RequestMethod.GET)
	@ResponseBody
	public Integer batchDeleteById(String ids, HttpServletRequest request){
		Integer result = ResponseStatus.INIT;
		if(StringUtils.isNotBlank(ids)){
			String[] idArr = ids.split("\\,");
			List<String> idList =  Arrays.asList(idArr);
			salesOpportunityService.batchDeleteSalesOpportunity(idList);
			
			result = ResponseStatus.UPDATE_SUCCESS;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(@ModelAttribute SalesOpportunityEntity salesOpportunity, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/salesOpportunity/salesOpportunity_edit");
		salesOpportunity.init(request); //初始化公司、部门、用户信息
		String provinceId = salesOpportunity.getProvinceId();
		salesOpportunity.setProvinceId(StringUtils.splitLocation(provinceId));
		String cityId = salesOpportunity.getCityId();
		salesOpportunity.setCityId(StringUtils.splitLocation(cityId));
		String areaId = salesOpportunity.getAreaId();
		salesOpportunity.setAreaId(StringUtils.splitLocation(areaId));
		String winRate = this.getWinRateBySaleStage(salesOpportunity.getSaleStage());
		salesOpportunity.setWinRate(winRate);
		Long id = salesOpportunity.getId();
		UserEntity user = UserUtils.getLoginUser(request);
		if(id == null){
			String returnId = salesOpportunityService.insertSalesOpportunity(salesOpportunity);
			LogUtils.log(LogUtils.SALES_OPPORTUNITY, "添加了销售机会", returnId, "销售机会ID", user);
			LogUtils.log(LogUtils.CUSTOMER, "添加了销售机会", salesOpportunity.getCustomerId(), "客户ID", user);
		}else{
			salesOpportunityService.updateSalesOpportunity(salesOpportunity);
			LogUtils.log(LogUtils.SALES_OPPORTUNITY, "更新了销售机会", salesOpportunity.getId().toString(), "销售机会ID", user);
		}

		return mav;
	}

	/**
	 *
	 * @param saleStage
	 * @return
	 */
	private String getWinRateBySaleStage(Integer saleStage){
		String winRate = "0%";
		switch (saleStage){
			case 1 : //初步接洽
				winRate = "10%";
				break;
			case 2 : //需求确定
				winRate = "30%";
				break;
			case 3 : //方案/报价
				winRate = "60%";
				break;
			case 4 : //谈判审核
				winRate = "80%";
				break;
			case 5 : //赢单
				winRate = "100%";
				break;
			case 6 : //输单
				winRate = "0%";
				break;
		}

		return winRate;
	}
}
