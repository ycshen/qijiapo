package com.qjp.service;

import com.qjp.entity.ActivityEntity;
import com.qjp.util.query.ActivityQuery;

import java.util.List;

/**
 * Created by fengyue on 2017/3/22.
 */
public interface ActivityService {
    /**
     * 添加市场活动
     * @param activityEntity
     * @return
     */
    String insertActivity(ActivityEntity activityEntity);
    ActivityQuery getActivityPages(ActivityQuery activityQuery);
    ActivityEntity getActivityById(String id);
    void deleteActivityById(String id);
    void batchDeleteActivity(List<String> ids);
    void updateActivity(ActivityEntity competitor);
    void batchDelete(String idArr);
    void batchTransfer(String idArr);
    boolean followActivity(String id);

}
