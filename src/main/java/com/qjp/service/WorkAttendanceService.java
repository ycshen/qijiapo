package com.qjp.service;

import com.qjp.entity.WorkAttendanceEntity;
import com.qjp.util.query.WorkAttendanceQuery;

import java.util.List;

/**
 * Created by fengyue on 2017/4/13.
 */
public interface WorkAttendanceService {

    String insertWorkAttendance(WorkAttendanceEntity workAttendance);
    WorkAttendanceQuery getWorkAttendancePage(WorkAttendanceQuery workAttendanceQuery);
    WorkAttendanceEntity getWorkAttendanceById(String id);
}
