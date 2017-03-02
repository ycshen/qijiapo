package com.qjp.entity;

/**
 * Created by fengyue on 2017/2/28.
 * 联系人
 */
public class AttnEntity {

    private String AttnType;//联系人类型
    private String AttnOwner;//联系人所有人
    private String companyName;//公司名称
    private String name;//名称
    private String duty;//职务
    private String phoneNum;//电话号
    private String mobilePhoneNum;//手机号
    private String email;//邮箱
    private String address;//地址
    private String note;//备注
    private String department;//部门
    private String microBlog;//微博
    private String province;//省份
    private String postcode;//邮政编码
    private String gender;//性别
    private String birthDay;//生日
    private String createTime;//创建时间
    private String editTime;//修改时间

    public String getAttnType() {
        return AttnType;
    }

    public void setAttnType(String attnType) {
        AttnType = attnType;
    }

    public String getAttnOwner() {
        return AttnOwner;
    }

    public void setAttnOwner(String attnOwner) {
        AttnOwner = attnOwner;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMobilePhoneNum() {
        return mobilePhoneNum;
    }

    public void setMobilePhoneNum(String mobilePhoneNum) {
        this.mobilePhoneNum = mobilePhoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMicroBlog() {
        return microBlog;
    }

    public void setMicroBlog(String microBlog) {
        this.microBlog = microBlog;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }
}
