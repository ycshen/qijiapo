package com.qjp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.ReturnMoneyEntity;
import com.qjp.entity.ReturnMoneyEntity;
import com.qjp.service.ReturnMoneyService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.CRMApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.ReturnMoneyQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fengyue on 2017/4/5.
 */
@Service
public class ReturnMoneyServiceImpl implements ReturnMoneyService {

    @Override
    public String insertReturnMoney(ReturnMoneyEntity returnMoneyEntity) {
        String jsonStr = JsonUtils.json2Str(returnMoneyEntity);
        String result = CRMApiUtils.insertReturnMoney(jsonStr);
        String id = "";
        if(StringUtils.isNotBlank(result)){
            JSONObject jsonObject = JSONObject.parseObject(result);
            if(jsonObject != null){
                Object codeObj = jsonObject.get("code");
                if(codeObj != null){
                    String code = codeObj.toString();
                    if (ApiCode.OK.toString().equals(code)) {
                        Object dataObj = jsonObject.get("data");
                        if(dataObj != null){
                            id = dataObj.toString();

                        }
                    }
                }
            }
        }

        return id;
    }

    @Override
    public ReturnMoneyEntity getReturnMoneyById(String id) {
        String result = CRMApiUtils.getReturnMoneyById(id);
        ReturnMoneyEntity returnMoneyEntity = null;
        if(StringUtils.isNotBlank(result)){
            JSONObject jsonObject = JSONObject.parseObject(result);
            if(jsonObject != null){
                Object codeObj = jsonObject.get("code");
                if(codeObj != null){
                    String code = codeObj.toString();
                    if (ApiCode.OK.toString().equals(code)) {
                        Object dataObj = jsonObject.get("data");
                        if(dataObj != null){
                            String data = dataObj.toString();
                            returnMoneyEntity = JSONObject.parseObject(data, ReturnMoneyEntity.class);
                        }
                    }
                }
            }
        }

        return returnMoneyEntity;
    }

    @Override
    public void updateReturnMoney(ReturnMoneyEntity returnMoneyEntity) {

    }

    @Override
    public ReturnMoneyQuery getReturnMoneyByContractId(ReturnMoneyQuery returnMoneyQuery) {
        String json = JsonUtils.json2Str(returnMoneyQuery);
        String loginResult = CRMApiUtils.getReturnMoneyPageByContractId(json);
        if(StringUtils.isNotBlank(loginResult)){
            JSONObject jsonObject = JSONObject.parseObject(loginResult);
            if(jsonObject != null){
                Object codeObj = jsonObject.get("code");
                if(codeObj != null){
                    String code = codeObj.toString();
                    if (ApiCode.OK.toString().equals(code)) {
                        Object dataObj = jsonObject.get("data");
                        if(dataObj != null){
                            String data = dataObj.toString();
                            List<ReturnMoneyEntity> list = JSONObject.parseArray(data, ReturnMoneyEntity.class);
                            returnMoneyQuery.setItems(list);
                        }

                        Object countObj = jsonObject.get("count");
                        if(countObj != null){
                            String count = countObj.toString();
                            returnMoneyQuery.setCount(Integer.parseInt(count));
                        }
                    }
                }
            }
        }

        return returnMoneyQuery;
    }
}
