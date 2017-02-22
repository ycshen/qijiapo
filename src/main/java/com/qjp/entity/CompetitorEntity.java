package com.qjp.entity;
/**
 * 竞争对手
 * @author Administrator
 *
 */
public class CompetitorEntity extends BaseEntity{
	private String competitorName; //竞争对手名称
	private String beyondUser; //竞争对手所有人
	private String beyondUserType; //竞争对手类型 1-个人 2-团队or部门 3-公司
	private String proviceId;
	private String provinceName;
	private String cityId;
	private String cityName;
	private String areaId;
	private String areaName;
	private String address;
	private String website; //公司网址
	private Integer staffNum; //员工数量
	private String saleMoney; //公司销售额
	private String remark; //备注
	private String postcode; //邮编
	private String facsimile; //传真
	private String mobile; //电话
	private String weibo; //微博
	private String beyondOf; //所属人
	public String getCompetitorName() {
		return competitorName;
	}
	public void setCompetitorName(String competitorName) {
		this.competitorName = competitorName;
	}
	public String getBeyondUser() {
		return beyondUser;
	}
	public void setBeyondUser(String beyondUser) {
		this.beyondUser = beyondUser;
	}
	public String getBeyondUserType() {
		return beyondUserType;
	}
	public void setBeyondUserType(String beyondUserType) {
		this.beyondUserType = beyondUserType;
	}
	public String getProviceId() {
		return proviceId;
	}
	public void setProviceId(String proviceId) {
		this.proviceId = proviceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getFacsimile() {
		return facsimile;
	}
	public void setFacsimile(String facsimile) {
		this.facsimile = facsimile;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getWeibo() {
		return weibo;
	}
	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}
	public String getBeyondOf() {
		return beyondOf;
	}
	public void setBeyondOf(String beyondOf) {
		this.beyondOf = beyondOf;
	}
}
