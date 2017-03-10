package com.qjp.entity;

/**
 * Created by fengyue on 2017/2/28.
 * 联系人
 */
public class AttnEntity  extends CRMBaseEntity{

    private String attnType;//联系人类型
//    private String attnOwner;//联系人所有人
//    private String companyName;//公司名称
    private String attnName;//名称
    private String duty;//职务
    private String phoneNum;//电话号
    private String mobilePhoneNum;//手机号
    private String email;//邮箱
    private String address;//地址
    private String gender;//性别
    private String birthDay;//生日


    public String getAttnType() {
        return attnType;
    }

    public void setAttnType(String attnType) {
        this.attnType = attnType;
    }

    public String getAttnName() {
        return attnName;
    }

    public void setAttnName(String attnName) {
        this.attnName = attnName;
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
}
