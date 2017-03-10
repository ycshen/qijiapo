package com.qjp.service;

import com.qjp.entity.AttnEntity;
import com.qjp.entity.AttnEntity;
import com.qjp.util.query.AttnQuery;
import com.qjp.util.query.AttnQuery;

import java.util.List;

/**
 * Created by fengyue on 2017/2/28.
 */
public interface AttnService {
    String insertAttn(AttnEntity attnEntity);
    AttnQuery getAttnPage(AttnQuery attnQuery);
    AttnEntity getAttnById(String id);
    void deleteAttnById(String id);
    void batchDeleteAttn(List<String> ids);
    void updateAttn(AttnEntity attnEntity);
    void batchDelete(String idArr);
    void batchTransfer(String idArr);
}
