package com.qjp.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by fengyue on 2017/3/21.
 */
public class ActivityEntity extends CRMBaseEntity {
    private Integer activityType;//活动类型
    private Integer activityState;//活动状态
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date activityStartTime;//开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date activityEndTime;//结束时间
    private Integer invitationPopulation;//邀请人数
    private Integer realNum;//实际人数
    private Integer businessType;//业务类型
    private String activityNote;//活动说明
    private Double activityCost;//活动成本
    private Double expectedIncome;//预计收入
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recordTime;//活动最新记录时间
    private Integer expectedNum;//预计响应
    private Double realActivityCost;//实际成本
    private Double realIncome;//实际收入
    private String activityName;

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public Integer getActivityState() {
        return activityState;
    }

    public void setActivityState(Integer activityState) {
        this.activityState = activityState;
    }


    public Integer getInvitationPopulation() {
        return invitationPopulation;
    }

    public void setInvitationPopulation(Integer invitationPopulation) {
        this.invitationPopulation = invitationPopulation;
    }

    public Integer getRealNum() {
        return realNum;
    }

    public void setRealNum(Integer realNum) {
        this.realNum = realNum;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getActivityNote() {
        return activityNote;
    }

    public void setActivityNote(String activityNote) {
        this.activityNote = activityNote;
    }

    public Double getActivityCost() {
        return activityCost;
    }

    public void setActivityCost(Double activityCost) {
        this.activityCost = activityCost;
    }

    public Double getExpectedIncome() {
        return expectedIncome;
    }

    public void setExpectedIncome(Double expectedIncome) {
        this.expectedIncome = expectedIncome;
    }

    public Date getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Date activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Integer getExpectedNum() {
        return expectedNum;
    }

    public void setExpectedNum(Integer expectedNum) {
        this.expectedNum = expectedNum;
    }

    public Double getRealActivityCost() {
        return realActivityCost;
    }

    public void setRealActivityCost(Double realActivityCost) {
        this.realActivityCost = realActivityCost;
    }

    public Double getRealIncome() {
        return realIncome;
    }

    public void setRealIncome(Double realIncome) {
        this.realIncome = realIncome;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}