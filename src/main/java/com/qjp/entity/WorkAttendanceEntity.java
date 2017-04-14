package com.qjp.entity;

import com.qjp.util.UserUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by fengyue on 7/4/.
 */
public class WorkAttendanceEntity {
    private Long id;

    private String userId;

    private String userName;

    private String createUserId;
    private String createUser;
    private String departmentId;
    private String departmentName;
    private String location;
    private Date attendanceTime;
    private Integer state;
    private String lateCause;
    private String leaveEarlyReason;
    private String onWorkAttendanceLocation;
    private String offWorkAttendanceLocation;
    private Date onWorkTime;
    private Date offWorkTime;
    private Date createTime;
    private String companyId;
    private String companyName;
    private Integer isDelete;

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(Date attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getLateCause() {
        return lateCause;
    }

    public void setLateCause(String lateCause) {
        this.lateCause = lateCause;
    }

    public String getLeaveEarlyReason() {
        return leaveEarlyReason;
    }

    public void setLeaveEarlyReason(String leaveEarlyReason) {
        this.leaveEarlyReason = leaveEarlyReason;
    }

    public String getOnWorkAttendanceLocation() {
        return onWorkAttendanceLocation;
    }

    public void setOnWorkAttendanceLocation(String onWorkAttendanceLocation) {
        this.onWorkAttendanceLocation = onWorkAttendanceLocation;
    }

    public String getOffWorkAttendanceLocation() {
        return offWorkAttendanceLocation;
    }

    public void setOffWorkAttendanceLocation(String offWorkAttendanceLocation) {
        this.offWorkAttendanceLocation = offWorkAttendanceLocation;
    }

    public Date getOnWorkTime() {
        return onWorkTime;
    }

    public void setOnWorkTime(Date onWorkTime) {
        this.onWorkTime = onWorkTime;
    }

    public Date getOffWorkTime() {
        return offWorkTime;
    }

    public void setOffWorkTime(Date offWorkTime) {
        this.offWorkTime = offWorkTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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


    }
}
