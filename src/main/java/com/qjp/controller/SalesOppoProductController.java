package com.qjp.controller;

import com.qjp.entity.SalesOppoProductEntity;
import com.qjp.entity.SalesOpportunityEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.*;
import com.qjp.util.JsonUtils;
import com.qjp.util.LogUtils;
import com.qjp.util.StringUtils;
import com.qjp.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
	@Autowired
	private SalesOpportunityService salesOpportunityService;
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Integer saveOrUpdate(@RequestBody SalesOppoProductEntity sop, HttpServletRequest request) {
		UserEntity user = UserUtils.getLoginUser(request);
		sop.setUserId(user.getId().toString());
		sop.setUserName(user.getUserName());
		sopService.insertSop(sop);
		String saleOppoId = sop.getSalesOppoId();
		LogUtils.log(LogUtils.SALES_OPPORTUNITY, "添加了销售机会的对应产品" + sop.getProductName(), saleOppoId, "销售机会ID", UserUtils.getLoginUser(request));
		//更新销售金额
		String newSaleMoney = sop.getSaleMoney();
		if (StringUtils.isNotBlank(newSaleMoney)) {
			SalesOpportunityEntity saleOppo = salesOpportunityService.getSalesOpportunityById(saleOppoId);
			if (saleOppo != null) {
				String saleMoney = saleOppo.getSaleMoney();
				Float oldSaleMoney = 0f;
				if(StringUtils.isNotBlank(saleMoney)){
					oldSaleMoney = Float.parseFloat(saleMoney);
				}

				oldSaleMoney += Float.parseFloat(newSaleMoney);
				salesOpportunityService.updateSaleMoneyById(saleOppoId, oldSaleMoney.toString());
				LogUtils.log(LogUtils.SALES_OPPORTUNITY, "添加销售机会后更新对应销售金额【添加前（元）： " + saleOppo.getSaleMoney() +",添加后（元）：" + oldSaleMoney + "】", saleOppoId, "销售机会ID", UserUtils.getLoginUser(request));
			}
		}

		return 1;
	}

	@RequestMapping(value = "/listProduct")
	@ResponseBody
	public String listProduct(String saleOppoId){
		String result = StringUtils.EMPTY;
		if(StringUtils.isNotBlank(saleOppoId)){
			List<SalesOppoProductEntity> list = sopService.getSopListBySaleOppoId(saleOppoId);
			result = JsonUtils.json2Str(list);
		}

		return result;
	}

}
