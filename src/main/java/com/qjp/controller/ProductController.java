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
import com.qjp.entity.ProductEntity;
import com.qjp.entity.LogEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.ProductService;
import com.qjp.service.DepartmentService;
import com.qjp.service.LogService;
import com.qjp.service.UserService;
import com.qjp.util.JsonUtils;
import com.qjp.util.LogUtils;
import com.qjp.util.StringUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.ProductQuery;
import com.qjp.util.query.LogQuery;

@Controller
@RequestMapping("/inner/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private LogService logService;
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ModelAndView list(@ModelAttribute ProductQuery productQuery, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/product/product_list");
		mav.addObject("productQuery", productQuery);
		return mav;
	}
	
	
	@RequestMapping(value = "/listAjax", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String listAjax(@ModelAttribute ProductQuery productQuery, HttpServletRequest request){
		productQuery.init(request);
		String roleType = productQuery.getRoleType();
		if(RoleEnum.DEP.getRoleId().toString().equals(roleType)){
			String idList = departmentService.getSubDepList(productQuery.getDepartmentId(), productQuery.getCompanyId());
			productQuery.setDepartmentId(idList);
		}
		
		productQuery = productService.getProductPage(productQuery);
		String jsonStr = JsonUtils.json2Str(productQuery);
		
		return jsonStr;
	}
	@RequestMapping(value = "/forwardEdit", method = RequestMethod.GET)
	public ModelAndView forwardEdit(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/product/product_edit");
		UserEntity user = UserUtils.getLoginUser(request);
		ProductEntity product = null;
		if(StringUtils.isNotBlank(id)){
			product = productService.getProductById(id);
		}
		
		mav.addObject("product", product);
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/product/product_detail");
		UserEntity user = UserUtils.getLoginUser(request);
		mav.addObject("user", user);
		ProductEntity product = productService.getProductById(id);
		mav.addObject("product", product);
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
			productService.deleteProductById(id);
			UserEntity user = UserUtils.getLoginUser(request);
			LogUtils.logCRMProduct("删除了产品(" + name + ")", id, user);
			result = ResponseStatus.UPDATE_SUCCESS;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/transferProduct", method = RequestMethod.GET)
	@ResponseBody
	public Integer transferProduct(String userId, String productId, String transferType, HttpServletRequest request){
		Integer result = ResponseStatus.INIT;
		if(StringUtils.isNotBlank(userId)){
			UserEntity transferToUser = userService.getUserById(userId);
			UserEntity loginUser = UserUtils.getLoginUser(request);
			if("1".equals(transferType)){
				this.transferProduct(productId, transferToUser, loginUser);
			}else if("2".equals(transferType)){
				String[] idArr = productId.split("\\,");
				for (String id : idArr) {
					this.transferProduct(id, transferToUser, loginUser);
				}
			}
			
			result = ResponseStatus.UPDATE_SUCCESS;
			
		}
		
		return result;
	}
	
	private void transferProduct(String productId, UserEntity transferToUser, UserEntity loginUser){
		ProductEntity oldProduct = productService.getProductById(productId);
		oldProduct.setUserId(transferToUser.getId().toString());
		String transferUserName = transferToUser.getUserName();
		oldProduct.setUserName(transferUserName);
		oldProduct.setUpdateTime(new Date());
		oldProduct.setUpdateUser(loginUser.getUserName());
		LogUtils.logCRMProduct("转移产品(" + oldProduct.getProductName() + ")到" + transferUserName, productId, loginUser);
	}
	
	@RequestMapping(value = "/batchDeleteById", method = RequestMethod.GET)
	@ResponseBody
	public Integer batchDeleteById(String ids, HttpServletRequest request){
		Integer result = ResponseStatus.INIT;
		if(StringUtils.isNotBlank(ids)){
			String[] idArr = ids.split("\\,");
			List<String> idList =  Arrays.asList(idArr);
			productService.batchDeleteProduct(idList);
			UserEntity user = UserUtils.getLoginUser(request);
			for (String id : idList) {
				LogUtils.logCRMProduct("删除了产品", id, user);
			}
			result = ResponseStatus.UPDATE_SUCCESS;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(@ModelAttribute ProductEntity product, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/product/product_edit");
		product.init(request); //初始化公司、部门、用户信息
		String provinceId = product.getProvinceId();
		product.setProvinceId(StringUtils.splitLocation(provinceId));
		String cityId = product.getCityId();
		product.setCityId(StringUtils.splitLocation(cityId));
		String areaId = product.getAreaId();
		product.setAreaId(StringUtils.splitLocation(areaId));
		Long id = product.getId();
		UserEntity user = UserUtils.getLoginUser(request);
		if(id == null){
			String returnId = productService.insertProduct(product);
			LogUtils.logCRMProduct("添加了产品(" + product.getProductName() + ")", returnId, user);
		}else{
			productService.updateProduct(product);
			LogUtils.logCRMProduct("修改了产品(" + product.getProductName() + ")", id.toString(), user);
		}

		return mav;
	}
	
}
