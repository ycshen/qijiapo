package com.qjp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.AttnEntity;
import com.qjp.entity.CompetitorEntity;
import com.qjp.service.AttnService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.CRMApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.AttnQuery;
import org.apache.http.util.TextUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fengyue on 2017/2/28.
 */
@Service
public class AttnServiceImpl implements AttnService {


    @Override
    public void insertAttn(AttnEntity attnEntity) {

    }

    @Override
    public AttnQuery getAttnPage(AttnQuery attnQuery) {
        String json = JsonUtils.json2Str(attnQuery);
        String attnResult = CRMApiUtils.getAttnPage(json);
        if (!TextUtils.isBlank(attnResult)){
            JSONObject object = JSONObject.parseObject(attnResult);
            if(object != null) {
                Object codeObj = object.get("code");
                if (codeObj != null) {
                    String code = codeObj.toString();
                    if (ApiCode.OK.toString().equals(code)) {
                        Object dataObj = object.get("data");
                        if (dataObj != null) {
                            String data = dataObj.toString();
                            List<AttnEntity> list = JSONObject.parseArray(data, AttnEntity.class);
                            attnQuery.setItems(list);
                        }

                        Object countObj = object.get("count");
                        if (countObj != null) {
                            String count = countObj.toString();
                            attnQuery.setCount(Integer.parseInt(count));
                        }
                    }
                }
            }
        }
        return attnQuery;
    }
}
