package com.qjp.service;

import com.qjp.entity.WorkAttendancePlaceEntity;
import com.qjp.util.query.WorkAttendancePlaceQuery;

import java.util.List;

/**
 * Created by fengyue on 2017/4/13.
 */
public interface WorkAttendancePlaceService {

    String insertWorkAttendancePlace(WorkAttendancePlaceEntity workAttendancePlace);
    WorkAttendancePlaceQuery getWorkAttendancePlacePage(WorkAttendancePlaceQuery workAttendancePlaceQuery);
    WorkAttendancePlaceEntity getWorkAttendancePlaceById(String id);
    void deleteWorkAttendancePlaceById(String id);
    void batchDeleteWorkAttendancePlace(List<String> ids);
    String updateWorkAttendancePlace(WorkAttendancePlaceEntity workAttendancePlace);
    void enableOrDisableWorkAttendancePlaceById(String id);
}
