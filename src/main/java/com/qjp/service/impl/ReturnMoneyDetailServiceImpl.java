package com.qjp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.ReturnMoneyDetailEntity;
import com.qjp.service.ReturnMoneyDetailService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.CRMApiUtils;
import com.qjp.util.api.model.ApiCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
}
