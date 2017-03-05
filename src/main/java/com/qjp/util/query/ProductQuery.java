package com.qjp.util.query;

import com.qjp.entity.ProductEntity;
import com.qjp.model.pageutil.Page;

/** 
 * <p>Project: qijiapo-crm</p> 
 * <p>Title: ProductQuery.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public class ProductQuery extends Page<ProductEntity>{
	
	private String productName;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}

