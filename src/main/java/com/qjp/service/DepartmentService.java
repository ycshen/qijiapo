package com.qjp.service;

import java.util.List;

import com.qjp.entity.DepartmentEntity;
import com.qjp.util.query.DepartmentQuery;

/** 
 * <p>Project: qijiapo</p> 
 * <p>Title: ConfigService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public interface DepartmentService {
	/**
	 * 
	 * @param department
	 * @return 返回ID
	 */
	String insertDepartment(DepartmentEntity department);
	DepartmentQuery getDepartmentList(DepartmentQuery departmentQuery);
	void updateDepartment(DepartmentEntity department);
	DepartmentEntity getDepartmentById(Integer id);
	boolean isExistDepartment(String departmentName, String pid, Boolean isCompany);
	List<DepartmentEntity> getListByCompanyId(String companyId);
	List<DepartmentEntity> getDepartmentByParentId(String parentDepartmentId);
	List<DepartmentEntity> getNoSubDeptListByCompanyId(String id);
	void deleteDepartmentById(String id, String companyId);
}

