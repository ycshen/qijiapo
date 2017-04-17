package com.qjp.service;

import com.qjp.entity.ReturnMoneyDetailEntity;
import com.qjp.entity.ReturnMoneyDetailEntity;
import com.qjp.util.query.ReturnMoneyDetailQuery;

import java.util.List;

/**
 * Created by fengyue on 2017/4/7.
 */
public interface ReturnMoneyDetailService {

    String insertReturnMoneyDetail(ReturnMoneyDetailEntity returnMoneyDetailEntity);
    ReturnMoneyDetailQuery getReturnMoneyDetailPage(ReturnMoneyDetailQuery returnMoneyDetailQuery);
    ReturnMoneyDetailEntity getReturnMoneyDetailById(String id);
    void deleteReturnMoneyDetailById(String id);
//    void batchDeleteReturnMoneyDetail(List<String> ids);
    void updateReturnMoneyDetail(ReturnMoneyDetailEntity returnMoneyDetail);
}
