package com.qjp.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qjp.entity.CompanyEntity;
import com.qjp.util.query.CompanyQuery;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: CompanyMapper.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Repository
public interface CompanyMapper {
	void insertCompany(CompanyEntity company);
	List<CompanyEntity> getCompanyPage(CompanyQuery companyQuery);
	void updateCompany(CompanyEntity company);
	CompanyEntity getCompanyById(Integer id);
}

