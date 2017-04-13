package com.qjp.entity;

/**
 * <p>Project: qijiapo-crm</p> 
 * <p>Title: CustomerEntity.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public class CustomerEntity extends CRMBaseEntity{
	private String customerName; //客户名称
	private String address; //详细地址
	private String mobile; //电话
	private Integer level;// 客户级别
	private String parentCustomerId;//上级客户
	private String parentCustomerName; //上级客户名称
	private Integer industry;  //行业
	private String website; //公司网址
	private Integer totalStaff; //总人数
	private String saleMoney;  //销售额
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getParentCustomerId() {
		return parentCustomerId;
	}
	public void setParentCustomerId(String parentCustomerId) {
		this.parentCustomerId = parentCustomerId;
	}
	public Integer getIndustry() {
		return industry;
	}
	public void setIndustry(Integer industry) {
		this.industry = industry;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Integer getTotalStaff() {
		return totalStaff;
	}
	public void setTotalStaff(Integer totalStaff) {
		this.totalStaff = totalStaff;
	}
	public String getSaleMoney() {
		return saleMoney;
	}
	public void setSaleMoney(String saleMoney) {
		this.saleMoney = saleMoney;
	}

	public String getParentCustomerName() {
		return parentCustomerName;
	}

	public void setParentCustomerName(String parentCustomerName) {
		this.parentCustomerName = parentCustomerName;
	}
}

