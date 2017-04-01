package com.qjp.service;

import com.qjp.entity.SalesOppoProductEntity;

import java.util.List;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: SaleOppoProductService.java</p>
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2017xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public interface SaleOppoProductService {

	/**
	 * 插入销售机会对应的产品信息
	 * @param sop
	 * @return
	 */
	String insertSop(SalesOppoProductEntity sop);
	List<SalesOppoProductEntity> getSopListBySaleOppoId(String saleOppoId);
}

