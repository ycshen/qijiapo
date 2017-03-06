package com.qjp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.ProductEntity;
import com.qjp.service.ProductService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.CRMApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.ProductQuery;

/** 
 * <p>Project: qijiapo</p> 
 * <p>Title: ProductServiceImpl.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class ProductServiceImpl implements ProductService{

	@Override
	public String insertProduct(ProductEntity product) {
		String jsonStr = JsonUtils.json2Str(product);
		String result = CRMApiUtils.insertProduct(jsonStr);
		String id = "";
		if(StringUtils.isNotBlank(result)){
			JSONObject jsonObject = JSONObject.parseObject(result);
			if(jsonObject != null){
				Object codeObj = jsonObject.get("code");
				if(codeObj != null){
					String code = codeObj.toString();
					if (ApiCode.OK.toString().equals(code)) {
						Object dataObj = jsonObject.get("data");
						if(dataObj != null){
							id = dataObj.toString();
							
						}
					}
				}
			}
		}
		
		return id;
	}

	@Override
	public ProductQuery getProductPage(ProductQuery productQuery) {
		String json = JsonUtils.json2Str(productQuery);
		String loginResult = CRMApiUtils.getProductPage(json);
		if(StringUtils.isNotBlank(loginResult)){
			JSONObject jsonObject = JSONObject.parseObject(loginResult);
			if(jsonObject != null){
				Object codeObj = jsonObject.get("code");
				if(codeObj != null){
					String code = codeObj.toString();
					if (ApiCode.OK.toString().equals(code)) {
						Object dataObj = jsonObject.get("data");
						if(dataObj != null){
							String data = dataObj.toString();
							List<ProductEntity> list = JSONObject.parseArray(data, ProductEntity.class);
							productQuery.setItems(list);
						}
						
						Object countObj = jsonObject.get("count");
						if(countObj != null){
							String count = countObj.toString();
							productQuery.setCount(Integer.parseInt(count));
						}
					}
				}
			}
		}
		
		return productQuery;
	}

	@Override
	public ProductEntity getProductById(String id) {
		String result = CRMApiUtils.getProductById(id);
		ProductEntity product = null;
		if(StringUtils.isNotBlank(result)){
			JSONObject jsonObject = JSONObject.parseObject(result);
			if(jsonObject != null){
				Object codeObj = jsonObject.get("code");
				if(codeObj != null){
					String code = codeObj.toString();
					if (ApiCode.OK.toString().equals(code)) {
						Object dataObj = jsonObject.get("data");
						if(dataObj != null){
							String data = dataObj.toString();
							product = JSONObject.parseObject(data, ProductEntity.class);
						}
					}
				}
			}
		}
		
		return product;
	}

	@Override
	public void deleteProductById(String id) {
		CRMApiUtils.deleteProductById(id);
	}

	@Override
	public void batchDeleteProduct(List<String> ids) {
		String idList = JsonUtils.json2Str(ids);
		CRMApiUtils.batchDeleteProduct(idList);
	}

	@Override
	public void updateProduct(ProductEntity product) {
		String jsonStr = JsonUtils.json2Str(product);
		CRMApiUtils.updateProduct(jsonStr);
	}

	@Override
	public void batchDelete(String idArr) {
		System.out.println(idArr);
	}

	@Override
	public void batchTransfer(String idArr) {
		System.out.println(idArr);
		
	}
	
}

