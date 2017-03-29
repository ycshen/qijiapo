package com.qjp.entity;

/**
 * Created by fengyue on 2017/3/28.
 */
public class SalesLeadsEntity extends CRMBaseEntity {

    private String customerName;//姓名
    private String customerCompanyName;//公司名称
    private Long activityId;//市场活动ID，应该关联使用
    private Integer followUpStatus;//跟进状态
    private Integer gender;//性别
    private String customerDepartmentName;//部门
    private String customerDuty;//职务
    private String customerPhoneNum;//电话
    private String customerMobileNum;//手机
    private String address;//地址
    private Integer clueSource;//线索来源
    private String email;//邮箱

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCompanyName() {
        return customerCompanyName;
    }

    public void setCustomerCompanyName(String customerCompanyName) {
        this.customerCompanyName = customerCompanyName;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Integer getFollowUpStatus() {
        return followUpStatus;
    }

    public void setFollowUpStatus(Integer followUpStatus) {
        this.followUpStatus = followUpStatus;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCustomerDepartmentName() {
        return customerDepartmentName;
    }

    public void setCustomerDepartmentName(String customerDepartmentName) {
        this.customerDepartmentName = customerDepartmentName;
    }

    public String getCustomerDuty() {
        return customerDuty;
    }

    public void setCustomerDuty(String customerDuty) {
        this.customerDuty = customerDuty;
    }

    public String getCustomerPhoneNum() {
        return customerPhoneNum;
    }

    public void setCustomerPhoneNum(String customerPhoneNum) {
        this.customerPhoneNum = customerPhoneNum;
    }

    public String getCustomerMobileNum() {
        return customerMobileNum;
    }

    public void setCustomerMobileNum(String customerMobileNum) {
        this.customerMobileNum = customerMobileNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getClueSource() {
        return clueSource;
    }

    public void setClueSource(Integer clueSource) {
        this.clueSource = clueSource;
    }
}
