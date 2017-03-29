package com.qjp.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 销售机会
 * @author Administrator
 *
 */
public class SalesOpportunityEntity extends CRMBaseEntity{
	private String salesOpportunityName; //机会名称
	private String customerId; //客户ID
	private String customerName; //客户名称
	private Integer oppotunityType; //机会类型
	private String saleMoney; //销售金额
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endOppoTime; //结单日期
	private Integer saleStage;//销售阶段
	private String winRate; //赢率
	private Date stageUpdateTime; //阶段更新时间
	private String marketActivityId; //市场活动
	private Integer lockStatus; //锁定状态
	private Integer failStage; //输单阶段
	private Integer failOrderReason; //输单原因
	private String failDesc; //输单描述
	private String productId; //产品
	private Integer oppoSource; //机会来源
	
	public String getSalesOpportunityName() {
		return salesOpportunityName;
	}
	public void setSalesOpportunityName(String salesOpportunityName) {
		this.salesOpportunityName = salesOpportunityName;
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
	public Integer getOppotunityType() {
		return oppotunityType;
	}
	public void setOppotunityType(Integer oppotunityType) {
		this.oppotunityType = oppotunityType;
	}
	public String getSaleMoney() {
		return saleMoney;
	}
	public void setSaleMoney(String saleMoney) {
		this.saleMoney = saleMoney;
	}
	public Date getEndOppoTime() {
		return endOppoTime;
	}
	public void setEndOppoTime(Date endOppoTime) {
		this.endOppoTime = endOppoTime;
	}
	public Integer getSaleStage() {
		return saleStage;
	}
	public void setSaleStage(Integer saleStage) {
		this.saleStage = saleStage;
	}
	public String getWinRate() {
		return winRate;
	}
	public void setWinRate(String winRate) {
		this.winRate = winRate;
	}
	public Date getStageUpdateTime() {
		return stageUpdateTime;
	}
	public void setStageUpdateTime(Date stageUpdateTime) {
		this.stageUpdateTime = stageUpdateTime;
	}
	public String getMarketActivityId() {
		return marketActivityId;
	}
	public void setMarketActivityId(String marketActivityId) {
		this.marketActivityId = marketActivityId;
	}
	public Integer getLockStatus() {
		return lockStatus;
	}
	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}
	public Integer getFailStage() {
		return failStage;
	}
	public void setFailStage(Integer failStage) {
		this.failStage = failStage;
	}
	public Integer getFailOrderReason() {
		return failOrderReason;
	}
	public void setFailOrderReason(Integer failOrderReason) {
		this.failOrderReason = failOrderReason;
	}
	public String getFailDesc() {
		return failDesc;
	}
	public void setFailDesc(String failDesc) {
		this.failDesc = failDesc;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getOppoSource() {
		return oppoSource;
	}
	public void setOppoSource(Integer oppoSource) {
		this.oppoSource = oppoSource;
	}
	
}
