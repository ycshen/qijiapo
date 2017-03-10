package com.qjp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.AttnEntity;
import com.qjp.entity.AttnEntity;
import com.qjp.service.AttnService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.CRMApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.AttnQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.TextUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fengyue on 2017/2/28.
 */
@Service
public class AttnServiceImpl implements AttnService {


    @Override
    public String insertAttn(AttnEntity attnEntity) {
        String jsonStr = JsonUtils.json2Str(attnEntity);
        String result = CRMApiUtils.insertAttn(jsonStr);
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

    @Override
    public AttnEntity getAttnById(String id) {
        String result = CRMApiUtils.getAttnById(id);
        AttnEntity attnEntity = null;
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
                            attnEntity = JSONObject.parseObject(data, AttnEntity.class);
                        }
                    }
                }
            }
        }

        return attnEntity;
    }

    @Override
    public void deleteAttnById(String id) {
        CRMApiUtils.deleteAttnById(id);
    }

    @Override
    public void batchDeleteAttn(List<String> ids) {
        String idList = JsonUtils.json2Str(ids);
        CRMApiUtils.batchDeleteAttn(idList);
    }

    @Override
    public void updateAttn(AttnEntity attnEntity) {
        String jsonStr = JsonUtils.json2Str(attnEntity);
        CRMApiUtils.updateAttn(jsonStr);
    }

    @Override
    public void batchDelete(String idArr) {
        System.out.println(idArr);
    }

    @Override
    public void batchTransfer(String idArr) {
        System.out.println(idArr);
    }
}
