package com.qjp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.CompanyEntity;
import com.qjp.entity.DepartmentEntity;
import com.qjp.service.DepartmentService;
import com.qjp.util.api.MyBaseApiUtils;
import com.qjp.util.api.model.ApiCode;
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
		List<DepartmentEntity> list = null;
		String result = MyBaseApiUtils.getDepartmentByCompanyId(companyId);
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
							list = JSONObject.parseArray(data, DepartmentEntity.class);
						}
					}
				}
			}
		}
		
		return list;
	}

	@Override
	public List<DepartmentEntity> getDepartmentByParentId(
			String parentDepartmentId) {
		return null;
	}
}

