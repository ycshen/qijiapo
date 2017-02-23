package com.qjp.util.query;

import com.qjp.entity.LogEntity;
import com.qjp.model.pageutil.Page;


/** 
 * <p>Project: qjp</p> 
 * <p>Title: LogQuery.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public class LogQuery extends Page<LogEntity>{
	private String userId;
	private String departmentId;
	private String companyId;
	private String casecadeId;
	private String logType;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCasecadeId() {
		return casecadeId;
	}
	public void setCasecadeId(String casecadeId) {
		this.casecadeId = casecadeId;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
}

