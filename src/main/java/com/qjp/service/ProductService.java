package com.qjp.service;

import java.util.List;

import com.qjp.entity.ProductEntity;
import com.qjp.util.query.ProductQuery;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: ProductService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public interface ProductService {
	String insertProduct(ProductEntity product);
	ProductQuery getProductPage(ProductQuery productQuery);
	ProductEntity getProductById(String id);
	void deleteProductById(String id);
	void batchDeleteProduct(List<String> ids);
	void updateProduct(ProductEntity product);
	void batchDelete(String idArr);
	void batchTransfer(String idArr);
}

