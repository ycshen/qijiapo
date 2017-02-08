package com.qjp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.qjp.base.Constant;
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
	public String insertDepartment(DepartmentEntity department) {
		String departmentJson = new Gson().toJson(department);
		String id = StringUtils.EMPTY;
		String result = MyBaseApiUtils.insertDepartment(departmentJson);
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
							id = data;
						}
					}
				}
			}
		}
		
		return id;
	}
	
	@Override
	public DepartmentQuery getDepartmentList(DepartmentQuery departmentQuery) {
		
		return departmentQuery;
	}

	@Override
	public void updateDepartment(DepartmentEntity department) {
		String departmentJson = new Gson().toJson(department);
		MyBaseApiUtils.updateDepartment(departmentJson);
	}

	@Override
	public DepartmentEntity getDepartmentById(Integer id) {
		String result = MyBaseApiUtils.getDepById(id.toString());
		DepartmentEntity department = null;
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
							department = JSONObject.parseObject(data, DepartmentEntity.class);
						}
					}
				}
			}
		}
		
		return department;
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

	@Override
	public boolean isExistDepartment(String departmentName, String pid,
			Boolean isCompany) {
		boolean isExist = true;
		String result = MyBaseApiUtils.isExistDepartment(pid, departmentName, isCompany.toString());
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
							if(Constant.FALSE.equals(data)){
								isExist = false;
							}
						}
					}
				}
			}
		}
		
		return isExist;
	}

	@Override
	public List<DepartmentEntity> getNoSubDeptListByCompanyId(String id) {
		List<DepartmentEntity> list = null;
		String result = MyBaseApiUtils.getNoSubDeptListByCompanyId(id);
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
}

