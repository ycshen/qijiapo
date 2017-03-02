package com.qjp.service;

import com.qjp.entity.AttnEntity;
import com.qjp.util.query.AttnQuery;

/**
 * Created by fengyue on 2017/2/28.
 */
public interface AttnService {
    void insertAttn(AttnEntity attnEntity);
    AttnQuery getAttnPage(AttnQuery attnQuery);
}
