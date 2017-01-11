package com.qjp.util.query;

import com.qjp.entity.CompanyEntity;
import com.qjp.model.pageutil.Page;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: CompanyQuery.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public class CompanyQuery extends Page<CompanyEntity>{
	private String level;
	private String companyName;
	private Long companyId;
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}

