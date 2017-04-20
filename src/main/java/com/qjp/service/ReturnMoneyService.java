package com.qjp.service;

import com.qjp.entity.ReturnMoneyEntity;
import com.qjp.util.query.ReturnMoneyQuery;

import java.util.List;

/**
 * @Author yuchuanshen
 * @Date Created by 2017/3/30
 * @Desc qijiapo-com.qjp.service
 */
public interface ReturnMoneyService {
    String insertReturnMoney(ReturnMoneyEntity returnMoneyEntity);
    ReturnMoneyEntity getReturnMoneyById(String id);
    void updateReturnMoney(ReturnMoneyEntity returnMoneyEntity);
    ReturnMoneyQuery getReturnMoneyByContractId(ReturnMoneyQuery returnMoneyQuery);
}
