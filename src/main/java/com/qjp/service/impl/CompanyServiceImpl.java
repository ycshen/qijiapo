package com.qjp.service.impl;

import org.springframework.stereotype.Service;

import com.qjp.entity.CompanyEntity;
import com.qjp.service.CompanyService;
import com.qjp.util.query.CompanyQuery;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: CompanyService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class CompanyServiceImpl implements CompanyService{

	@Override
	public void insertCompany(CompanyEntity company) {
	}
	
	@Override
	public CompanyQuery getCompanyList(CompanyQuery companyQuery) {
		
		return companyQuery;
	}

	@Override
	public void updateCompany(CompanyEntity company) {
	}

	@Override
	public CompanyEntity getCompanyById(Long id) {
		
		return null;
	}
}

