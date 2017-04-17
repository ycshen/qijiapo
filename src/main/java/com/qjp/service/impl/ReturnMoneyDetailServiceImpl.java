package com.qjp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.ReturnMoneyDetailEntity;
import com.qjp.entity.ReturnMoneyDetailEntity;
import com.qjp.service.ReturnMoneyDetailService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.CRMApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.ReturnMoneyDetailQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fengyue on 2017/4/7.
 */
@Service
public class ReturnMoneyDetailServiceImpl implements ReturnMoneyDetailService {
    @Override
    public String insertReturnMoneyDetail(ReturnMoneyDetailEntity returnMoneyDetailEntity) {
        String jsonStr = JsonUtils.json2Str(returnMoneyDetailEntity);
        String result = CRMApiUtils.insertReturnMoneyDetail(jsonStr);
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
    public ReturnMoneyDetailQuery getReturnMoneyDetailPage(ReturnMoneyDetailQuery returnMoneyDetailQuery) {
        String json = JsonUtils.json2Str(returnMoneyDetailQuery);
        String getResult = CRMApiUtils.getReturnMoneyDetailPage(json);
        if(StringUtils.isNotBlank(getResult)){
            JSONObject jsonObject = JSONObject.parseObject(getResult);
            if(jsonObject != null){
                Object codeObj = jsonObject.get("code");
                if(codeObj != null){
                    String code = codeObj.toString();
                    if (ApiCode.OK.toString().equals(code)) {
                        Object dataObj = jsonObject.get("data");
                        if(dataObj != null){
                            String data = dataObj.toString();
                            List<ReturnMoneyDetailEntity> list = JSONObject.parseArray(data, ReturnMoneyDetailEntity.class);
                            returnMoneyDetailQuery.setItems(list);
                        }

                        Object countObj = jsonObject.get("count");
                        if(countObj != null){
                            String count = countObj.toString();
                            returnMoneyDetailQuery.setCount(Integer.parseInt(count));
                        }
                    }
                }
            }
        }

        return returnMoneyDetailQuery;
    }

    @Override
    public ReturnMoneyDetailEntity getReturnMoneyDetailById(String id) {
        String result = CRMApiUtils.getReturnMoneyDetailById(id);
        ReturnMoneyDetailEntity returnMoneyDetail = null;
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
                            returnMoneyDetail = JSONObject.parseObject(data, ReturnMoneyDetailEntity.class);
                        }
                    }
                }
            }
        }

        return returnMoneyDetail;
    }

    @Override
    public void deleteReturnMoneyDetailById(String id) {
        CRMApiUtils.deleteReturnMoneyDetailById(id);
    }


    @Override
    public void updateReturnMoneyDetail(ReturnMoneyDetailEntity returnMoneyDetail) {
        String jsonStr = JsonUtils.json2Str(returnMoneyDetail);
        CRMApiUtils.updateReturnMoneyDetail(jsonStr);
    }


}
