package com.qjp.entity;

import com.qjp.util.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by fengyue on 2017/4/13.
 */
public class WorkAttendancePlaceEntity {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String placeName;
    private String address;
    private String workTime;
    private String onWorkTime;
    private String offWorkTime;
    private String departmentName;
    private String departmentId;
    private String errorRange;

    private String companyId;
    private String companyName;
    private String updateUser;

    private String updateUserId;
    private String createUser;
    private String createUserId;
    private String userId;
    private String userName;

    private Integer state;//状态（启用，1停用）
    private Integer isDelete;
    private String latitude;
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getOnWorkTime() {
        return onWorkTime;
    }

    public void setOnWorkTime(String onWorkTime) {
        this.onWorkTime = onWorkTime;
    }

    public String getOffWorkTime() {
        return offWorkTime;
    }

    public void setOffWorkTime(String offWorkTime) {
        this.offWorkTime = offWorkTime;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getErrorRange() {
        return errorRange;
    }

    public void setErrorRange(String errorRange) {
        this.errorRange = errorRange;
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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public void init(HttpServletRequest request) {
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
