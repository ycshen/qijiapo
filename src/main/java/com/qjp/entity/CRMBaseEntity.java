package com.qjp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.qjp.util.UserUtils;

/**
 * @Author shenyuchuan
 * @Date 2016/4/7.
 */
public class CRMBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	/**
	 * 创建人账号
	 */
	private String createUser;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
	private Date createTime;
	/**
	 * 更新人账号
	 */
	private String updateUser;
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
	private Date updateTime;

	private String postcode; //邮编
	private String provinceId;
	private String provinceName;
	private String cityId;
	private String cityName;
	
	private String areaId;
	private String areaName;
	private String facsimile; //传真
	private String weibo; //微博
	private String remark; //备注
	private String companyId;
	private String companyName;
	private String departmentId;
	private String departmentName;
	private String userId;
	private String userName;
	private Integer isDelete;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
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
	public String getFacsimile() {
		return facsimile;
	}
	public void setFacsimile(String facsimile) {
		this.facsimile = facsimile;
	}
	public String getWeibo() {
		return weibo;
	}
	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void init(HttpServletRequest request){
		UserEntity loginUser = UserUtils.getLoginUser(request);
		if(StringUtils.isBlank(this.getUserId())){
			this.setUserId(loginUser.getId().toString());
		}
		
		if(StringUtils.isBlank(this.getUserName())){
			this.setUserName(loginUser.getUserName());
		}
		
		if(StringUtils.isBlank(this.getDepartmentId())){
			this.setDepartmentId(loginUser.getDepartmentId().toString());
		}
		
		if(StringUtils.isBlank(this.getDepartmentName())){
			this.setDepartmentName(loginUser.getDepartmentName());
		}
		
		if(StringUtils.isBlank(this.getCompanyId())){
			this.setCompanyId(loginUser.getCompanyId().toString());
		}
		
		if(StringUtils.isBlank(this.getCompanyName())){
			this.setCompanyName(loginUser.getCompanyName());
		}
		
		if(StringUtils.isBlank(this.getCreateUser())){
			this.setCreateUser(loginUser.getUserName());
		}
		
		if(this.getCreateTime() == null){
			this.setCreateTime(new Date());
		}
		
		if(this.getUpdateTime() == null){
			this.setUpdateTime(new Date());
		}
		
		if(StringUtils.isBlank(this.getUpdateUser())){
			this.setUpdateUser(loginUser.getUserName());
		}
		
	}
	

}
