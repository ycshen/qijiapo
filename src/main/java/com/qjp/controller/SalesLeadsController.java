package com.qjp.controller;

import com.qjp.base.ResponseStatus;
import com.qjp.base.RoleEnum;
import com.qjp.entity.LogEntity;
import com.qjp.entity.SalesLeadsEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.DepartmentService;
import com.qjp.service.LogService;
import com.qjp.service.SalesLeadsService;
import com.qjp.service.UserService;
import com.qjp.util.JsonUtils;
import com.qjp.util.LogUtils;
import com.qjp.util.StringUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.LogQuery;
import com.qjp.util.query.SalesLeadsQuery;
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
@RequestMapping("/inner/salesLeads")
public class SalesLeadsController {
	@Autowired
	private SalesLeadsService salesLeadsService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private LogService logService;
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ModelAndView list(@ModelAttribute SalesLeadsQuery salesLeadsQuery, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/salesLeads/salesLeads_list");
		mav.addObject("salesLeadsQuery", salesLeadsQuery);
		return mav;
	}
	
	
	@RequestMapping(value = "/listAjax", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String listAjax(@ModelAttribute SalesLeadsQuery salesLeadsQuery, HttpServletRequest request){
		salesLeadsQuery.init(request);
		String roleType = salesLeadsQuery.getRoleType();
		if(RoleEnum.DEP.getRoleId().toString().equals(roleType)){
			String idList = departmentService.getSubDepList(salesLeadsQuery.getDepartmentId(), salesLeadsQuery.getCompanyId());
			salesLeadsQuery.setDepartmentId(idList);
		}
		
		salesLeadsQuery = salesLeadsService.getSalesLeadsPage(salesLeadsQuery);
		String jsonStr = JsonUtils.json2Str(salesLeadsQuery);
		
		return jsonStr;
	}
	@RequestMapping(value = "/forwardEdit", method = RequestMethod.GET)
	public ModelAndView forwardEdit(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/salesLeads/salesLeads_edit");
		UserEntity user = UserUtils.getLoginUser(request);
		SalesLeadsEntity salesLeads = null;
		if(StringUtils.isNotBlank(id)){
			salesLeads = salesLeadsService.getSalesLeadsById(id);
		}
		
		mav.addObject("salesLeads", salesLeads);
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/salesLeads/salesLeads_detail");
		UserEntity user = UserUtils.getLoginUser(request);
		mav.addObject("user", user);
		SalesLeadsEntity salesLeads = salesLeadsService.getSalesLeadsById(id);
		mav.addObject("salesLeads", salesLeads);
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
			salesLeadsService.deleteSalesLeadsById(id);
			UserEntity user = UserUtils.getLoginUser(request);
			LogUtils.logCRMSalesLeads("删除了销售机会(" + name + ")", id, user);
			result = ResponseStatus.UPDATE_SUCCESS;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/transferSalesLeads", method = RequestMethod.GET)
	@ResponseBody
	public Integer transferSalesLeads(String userId, String salesLeadsId, String transferType, HttpServletRequest request){
		Integer result = ResponseStatus.INIT;
		if(StringUtils.isNotBlank(userId)){
			UserEntity transferToUser = userService.getUserById(userId);
			UserEntity loginUser = UserUtils.getLoginUser(request);
			if("1".equals(transferType)){
				this.transferSalesLeads(salesLeadsId, transferToUser, loginUser);
			}else if("2".equals(transferType)){
				String[] idArr = salesLeadsId.split("\\,");
				for (String id : idArr) {
					this.transferSalesLeads(id, transferToUser, loginUser);
				}
			}
			
			result = ResponseStatus.UPDATE_SUCCESS;
			
		}
		
		return result;
	}
	
	private void transferSalesLeads(String salesLeadsId, UserEntity transferToUser, UserEntity loginUser){
		SalesLeadsEntity oldSalesLeads = salesLeadsService.getSalesLeadsById(salesLeadsId);
		oldSalesLeads.setUserId(transferToUser.getId().toString());
		String transferUserName = transferToUser.getUserName();
		oldSalesLeads.setUserName(transferUserName);
		oldSalesLeads.setUpdateTime(new Date());
		oldSalesLeads.setUpdateUser(loginUser.getUserName());
		LogUtils.logCRMSalesLeads("转移销售机会(" + oldSalesLeads.getCustomerName() + ")到" + transferUserName, salesLeadsId, loginUser);
	}
	
	@RequestMapping(value = "/batchDeleteById", method = RequestMethod.GET)
	@ResponseBody
	public Integer batchDeleteById(String ids, HttpServletRequest request){
		Integer result = ResponseStatus.INIT;
		if(StringUtils.isNotBlank(ids)){
			String[] idArr = ids.split("\\,");
			List<String> idList =  Arrays.asList(idArr);
			salesLeadsService.batchDeleteSalesLeads(idList);
			UserEntity user = UserUtils.getLoginUser(request);
			for (String id : idList) {
				LogUtils.logCRMSalesLeads("删除了销售机会", id, user);
			}
			result = ResponseStatus.UPDATE_SUCCESS;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(@ModelAttribute SalesLeadsEntity salesLeads, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/salesLeads/salesLeads_edit");
		salesLeads.init(request); //初始化公司、部门、用户信息
		String provinceId = salesLeads.getProvinceId();
		salesLeads.setProvinceId(StringUtils.splitLocation(provinceId));
		String cityId = salesLeads.getCityId();
		salesLeads.setCityId(StringUtils.splitLocation(cityId));
		String areaId = salesLeads.getAreaId();
		salesLeads.setAreaId(StringUtils.splitLocation(areaId));
		Long id = salesLeads.getId();
		UserEntity user = UserUtils.getLoginUser(request);
		if(id == null){
			String returnId = salesLeadsService.insertSalesLeads(salesLeads);
			LogUtils.logCRMSalesLeads("添加了销售机会(" + salesLeads.getCustomerName() + ")", returnId, user);
		}else{
			salesLeadsService.updateSalesLeads(salesLeads);
			LogUtils.logCRMSalesLeads("修改了销售机会(" + salesLeads.getCustomerName() + ")", id.toString(), user);
		}

		return mav;
	}
	
}
