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
public class ReturnMoneyEntity {
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
    private String code; //回款编号  日期yyyyMMdd+回款期次  20170330+0001=201703300001
    private String contractId; //合同id
    private String customerId; //客户id
    private Integer returnMoneyNum; //回款期次
    private Date planReturnDate; //计划回款时间
    private String planReturnMoney; //计划回款金额
    private Date actualReturnDate;//实际回款时间
    private String actualReturnMoney;//实际回款金额
    private String actualNotReturnMoney;//未回款金额
    private Integer returnMoneyStatus; //本期回款状态 0-未逾期 1-有逾期
    private String totalMoney; //总金额
    private String remark; //备注
    private Integer lockStatus; //锁定状态（0-未锁定， 1-锁定）
    ReturnMoneyDetailEntity returnMoneyDetail; //回款详情信息
    private Integer isDelete;
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    public String getId() {
        return id;
    }

    public Date getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(Date actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getReturnMoneyNum() {
        return returnMoneyNum;
    }

    public void setReturnMoneyNum(Integer returnMoneyNum) {
        this.returnMoneyNum = returnMoneyNum;
    }

    public Date getPlanReturnDate() {
        return planReturnDate;
    }

    public void setPlanReturnDate(Date planReturnDate) {
        this.planReturnDate = planReturnDate;
    }

    public String getPlanReturnMoney() {
        return planReturnMoney;
    }

    public void setPlanReturnMoney(String planReturnMoney) {
        this.planReturnMoney = planReturnMoney;
    }

    public String getActualReturnMoney() {
        return actualReturnMoney;
    }

    public void setActualReturnMoney(String actualReturnMoney) {
        this.actualReturnMoney = actualReturnMoney;
    }

    public String getActualNotReturnMoney() {
        return actualNotReturnMoney;
    }

    public void setActualNotReturnMoney(String actualNotReturnMoney) {
        this.actualNotReturnMoney = actualNotReturnMoney;
    }

    public Integer getReturnMoneyStatus() {
        return returnMoneyStatus;
    }

    public void setReturnMoneyStatus(Integer returnMoneyStatus) {
        this.returnMoneyStatus = returnMoneyStatus;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }

    public ReturnMoneyDetailEntity getReturnMoneyDetail() {
        return returnMoneyDetail;
    }

    public void setReturnMoneyDetail(ReturnMoneyDetailEntity returnMoneyDetail) {
        this.returnMoneyDetail = returnMoneyDetail;
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
