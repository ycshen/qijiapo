package com.qjp.util.query;


import com.qjp.entity.WorkAttendanceEntity;
import com.qjp.model.pageutil.Page;

import java.util.Date;

/**
 * Created by fengyue on 2017/4/11.
 */
public class WorkAttendanceQuery extends Page<WorkAttendanceEntity> {

    private Long id;

    private Date startTime;

    private Date endTime;

    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
