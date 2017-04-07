package com.qjp.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by fengyue on 2017/3/24.
 * 合同
 */
public class ContractEntity extends CRMBaseEntity {
    private String contractName;//合同名称
    private Integer contractType;//合同类型
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date contractStartTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date contractEndTime;
    private Double totalPrice;//总金额
    private String returnMoney;//回款金额
    private String notReturnMoney;//未回款金额
    private Integer returnMoneyState;//回款状态（0未回款，1部分回款，2全部回款）
    private Integer contractState;//合同状态
    private Integer paymentMethod;//付款方式
    private String contractBody;//合同正文
    private String contractNum;//合同编号
    private String customerSigner;//客户方签约人
    private Long signerId;//我放签约人
    /**
     * 签约日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date signTime;
    private Integer businessType;//业务类型
    private String customerId; //客户ID
    private String customerName; //客户名称


    public String getReturnMoney() {
        return returnMoney;
    }

    public void setReturnMoney(String returnMoney) {
        this.returnMoney = returnMoney;
    }

    public String getNotReturnMoney() {
        return notReturnMoney;
    }

    public void setNotReturnMoney(String notReturnMoney) {
        this.notReturnMoney = notReturnMoney;
    }

    public Integer getReturnMoneyState() {
        return returnMoneyState;
    }

    public void setReturnMoneyState(Integer returnMoneyState) {
        this.returnMoneyState = returnMoneyState;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }
    public Date getContractStartTime() {
        return contractStartTime;
    }
    public void setContractStartTime(Date contractStartTime) {
        this.contractStartTime = contractStartTime;
    }

    public Date getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(Date contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public Integer getContractState() {
        return contractState;
    }

    public void setContractState(Integer contractState) {
        this.contractState = contractState;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getContractBody() {
        return contractBody;
    }

    public void setContractBody(String contractBody) {
        this.contractBody = contractBody;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getCustomerSigner() {
        return customerSigner;
    }

    public void setCustomerSigner(String customerSigner) {
        this.customerSigner = customerSigner;
    }

    public Long getSignerId() {
        return signerId;
    }

    public void setSignerId(Long signerId) {
        this.signerId = signerId;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }
}
