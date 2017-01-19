package com.qjp.service;

import java.util.List;

import com.qjp.entity.PositionEntity;

/** 
 * <p>Project: qijiapo</p> 
 * <p>Title: PositionService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public interface PositionService {
	List<PositionEntity> getSystemPosition();
	List<PositionEntity> getPositionByCompanyId(String companyId);
}

