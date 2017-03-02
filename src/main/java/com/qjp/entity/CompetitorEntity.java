package com.qjp.entity;


/**
 * 竞争对手
 * @author Administrator
 *
 */
public class CompetitorEntity extends CRMBaseEntity{
	private String competitorName; //竞争对手名称
	private String beyondDept; //竞争对手所属部门
	private String beyondDeptName; //竞争对手所属部门
	private String beyondDeptType; //竞争对手所属部门类型 1-部门 2-公司
	private String address;
	private String website; //公司网址
	private Integer staffNum; //员工数量
	private String saleMoney; //公司销售额
	private String mobile; //电话
	private String beyondOf; //所属人
	private String beyondOfName; //所属人名称
	public String getCompetitorName() {
		return competitorName;
	}
	public void setCompetitorName(String competitorName) {
		this.competitorName = competitorName;
	}
	public String getBeyondDept() {
		return beyondDept;
	}
	public void setBeyondDept(String beyondDept) {
		this.beyondDept = beyondDept;
	}
	public String getBeyondDeptType() {
		return beyondDeptType;
	}
	
	public String getBeyondDeptName() {
		return beyondDeptName;
	}
	public void setBeyondDeptName(String beyondDeptName) {
		this.beyondDeptName = beyondDeptName;
	}
	public void setBeyondDeptType(String beyondDeptType) {
		this.beyondDeptType = beyondDeptType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Integer getStaffNum() {
		return staffNum;
	}
	public void setStaffNum(Integer staffNum) {
		this.staffNum = staffNum;
	}
	public String getSaleMoney() {
		return saleMoney;
	}
	public void setSaleMoney(String saleMoney) {
		this.saleMoney = saleMoney;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBeyondOf() {
		return beyondOf;
	}
	public void setBeyondOf(String beyondOf) {
		this.beyondOf = beyondOf;
	}
	public String getBeyondOfName() {
		return beyondOfName;
	}
	public void setBeyondOfName(String beyondOfName) {
		this.beyondOfName = beyondOfName;
	}
	
	
}
