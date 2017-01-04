package com.qjp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qjp.entity.DepartmentEntity;
import com.qjp.service.DepartmentService;
import com.qjp.util.query.DepartmentQuery;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: DepartmentServiceImpl.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Override
	public void insertDepartment(DepartmentEntity department) {
	}
	
	@Override
	public DepartmentQuery getDepartmentList(DepartmentQuery departmentQuery) {
		
		return departmentQuery;
	}

	@Override
	public void updateDepartment(DepartmentEntity department) {
	}

	@Override
	public DepartmentEntity getDepartmentById(Integer id) {
		
		return null;
	}

	@Override
	public boolean isExistDepartment(String departmentName, String companyId) {
		
		
		return false;
	}

	@Override
	public List<DepartmentEntity> getListByCompanyId(String companyId) {
		return null;
	}

	@Override
	public List<DepartmentEntity> getDepartmentByParentId(
			String parentDepartmentId) {
		return null;
	}
}

