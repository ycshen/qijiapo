package com.qjp.service.impl;

import com.qjp.entity.ActivityEntity;
import com.qjp.service.ActivityService;
import com.qjp.util.query.ActivityQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fengyue on 2017/3/22.
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Override
    public String insertActivity(ActivityEntity activityEntity) {
        return null;
    }

    @Override
    public ActivityQuery getActivityPages(ActivityQuery marketingActivityQuery) {
        return null;
    }

    @Override
    public ActivityEntity getActivityById(String id) {
        return null;
    }

    @Override
    public void deleteActivityById(String id) {

    }

    @Override
    public void batchDeleteActivity(List<String> ids) {

    }

    @Override
    public void updateActivity(ActivityEntity competitor) {

    }

    @Override
    public void batchDelete(String idArr) {

    }

    @Override
    public void batchTransfer(String idArr) {

    }

    @Override
    public boolean followActivity(String id) {
        return false;
    }
}
