package com.qjp.util.query;

import com.qjp.entity.ReturnMoneyDetailEntity;
import com.qjp.model.pageutil.Page;

/**
 * Created by fengyue on 2017/4/17.
 */
public class ReturnMoneyDetailQuery extends Page<ReturnMoneyDetailEntity>{
    private String returnMoneyId;

    public String getReturnMoneyId() {
        return returnMoneyId;
    }

    public void setReturnMoneyId(String returnMoneyId) {
        this.returnMoneyId = returnMoneyId;
    }
}
