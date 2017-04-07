package com.qjp.entity;

import com.qjp.util.UserUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author yuchuanshen
 * @Date Created by 2017/3/30
 * @Desc qijiapo-com.qjp.entity
 */
public class ReturnMoneyDetailEntity {
    private String id; //插入32位uuid
    private String userId; //添加人id
    private String userName; //添加人姓名
    private String departmentId; //添加人部门id
    private String departmentName;//添加人部门名称(所属部门)
    private String companyId; //添加人公司id
    private String companyName;//添加人公司名称(所有人)
    private Date createTime; //创建时间
    private Date updateTime; //更新时间
    private String updateUser; //更新人
    private String returnMoneyId; //回款期次id
    private String money;//金额
    private Date startDate; //日期
    private Integer payType; //支付方式(1-支票 2-现金 3-银行转账 4-支付宝 5-微信  6-其他)
    private String remark; //备注
    private Integer returnMoneyType; //回款类型（1-计划  2-实际  3-开票）
    //以下字段保存开票信息
    private String taxContent;// 开票内容
    private Integer taxType;// 票据类型(1-增值税 2-普通国税 3-普通地税 4-增值税专用发票 5-增值税普通发票)
    private String taxNum; //发票号码

    public String getReturnMoneyId() {
        return returnMoneyId;
    }

    public void setReturnMoneyId(String returnMoneyId) {
        this.returnMoneyId = returnMoneyId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getReturnMoneyType() {
        return returnMoneyType;
    }

    public void setReturnMoneyType(Integer returnMoneyType) {
        this.returnMoneyType = returnMoneyType;
    }

    public String getTaxContent() {
        return taxContent;
    }

    public void setTaxContent(String taxContent) {
        this.taxContent = taxContent;
    }

    public Integer getTaxType() {
        return taxType;
    }

    public void setTaxType(Integer taxType) {
        this.taxType = taxType;
    }

    public String getTaxNum() {
        return taxNum;
    }

    public void setTaxNum(String taxNum) {
        this.taxNum = taxNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }



    /**
     * 出事化
     * @param request
     */
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
