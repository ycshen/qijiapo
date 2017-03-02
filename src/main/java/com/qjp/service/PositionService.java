package com.qjp.service;

import java.util.List;

import com.qjp.entity.PositionEntity;
import com.qjp.util.query.PositionQuery;

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
	String insertPosition(PositionEntity position);
	void updatePosition(PositionEntity position);
	Boolean isExistPosition(String companyId, String positionName);
	PositionQuery getPositionPage(PositionQuery positionQuery);
	PositionEntity getPositionById(String id);
	boolean changePositionStatus(String id, String status);
}

