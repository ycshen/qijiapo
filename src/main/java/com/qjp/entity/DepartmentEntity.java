package com.qjp.entity;
/** 
 * <p>Project: MyBase</p> 
 * <p>Title: DepartmentEntity.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public class DepartmentEntity extends BaseEntity{
	private Long companyId;
	private String companyName;
	private String departmentName;
	private Integer status;
	private Long parentDepartmentId;
	private String parentDepartmentName;
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getParentDepartmentId() {
		return parentDepartmentId;
	}
	public void setParentDepartmentId(Long parentDepartmentId) {
		this.parentDepartmentId = parentDepartmentId;
	}
	public String getParentDepartmentName() {
		return parentDepartmentName;
	}
	public void setParentDepartmentName(String parentDepartmentName) {
		this.parentDepartmentName = parentDepartmentName;
	}
	@Override
	public String toString() {
		return "DepartmentEntity [companyId=" + companyId + ", companyName="
				+ companyName + ", departmentName=" + departmentName
				+ ", status=" + status + ", parentDepartmentId="
				+ parentDepartmentId + ", parentDepartmentName="
				+ parentDepartmentName + "]";
	}
	
	public String toLogString() {
		return "部门信息 [部门名称=" + departmentName + ", 上级部门="
				+ parentDepartmentName+ "]";
	}
	
}

