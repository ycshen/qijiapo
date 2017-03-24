package com.qjp.util.query;

import com.qjp.entity.ActivityEntity;
import com.qjp.model.pageutil.Page;

/**
 * Created by fengyue on 2017/3/22.
 */
public class ActivityQuery extends Page<ActivityEntity> {

    private Long id;

    private String activityName;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
