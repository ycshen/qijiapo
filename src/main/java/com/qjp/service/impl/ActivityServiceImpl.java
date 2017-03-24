package com.qjp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.ActivityEntity;
import com.qjp.service.ActivityService;
import com.qjp.util.JsonUtils;
import com.qjp.util.StringUtils;
import com.qjp.util.api.CRMApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.ActivityQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fengyue on 2017/3/22.
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Override
    public String insertActivity(ActivityEntity activityEntity) {
        String objStr = JsonUtils.json2Str(activityEntity);
        String result = CRMApiUtils.insertActivity(objStr);
        String id = "";
        if (StringUtils.isNotBlank(result)){
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject != null){
                Object codeObj = jsonObject.get("code");
                if (codeObj != null){
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
    public ActivityQuery getActivityPages(ActivityQuery marketingActivityQuery) {
        String objStr = JsonUtils.json2Str(marketingActivityQuery);
        String result = CRMApiUtils.getActivityPage(objStr);
        String id = "";
        if (StringUtils.isNotBlank(result)){
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject != null){
                Object codeObj = jsonObject.get("code");
                if (codeObj != null){
                    String code = codeObj.toString();
                    if (ApiCode.OK.toString().equals(code)){
                        Object dataObj = jsonObject.get("data");
                        if (dataObj != null){
                            String data = dataObj.toString();
                            List<ActivityEntity> list = JSONObject.parseArray(data,ActivityEntity.class);
                            marketingActivityQuery.setItems(list);

                        }
                        Object countObj = jsonObject.get("count");
                        if(countObj != null){
                            String count = countObj.toString();
                            marketingActivityQuery.setCount(Integer.parseInt(count));
                        }
                    }
                }
            }
        }
        return marketingActivityQuery;
    }

    @Override
    public ActivityEntity getActivityById(String id) {
        ActivityEntity activityEntity = null;
        String result = CRMApiUtils.getActivityById(id);
        if (StringUtils.isNotBlank(result)){
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject != null){
                Object codeObj = jsonObject.get("code");
                if (codeObj != null){
                    String code = codeObj.toString();
                    if (ApiCode.OK.toString().equals(code)){
                        Object dataObj = jsonObject.get("data");
                        if (dataObj != null){
                            String data = dataObj.toString();
                            activityEntity = JSONObject.parseObject(data,ActivityEntity.class);

                        }

                    }
                }
            }
        }
        return activityEntity;
    }

    @Override
    public void deleteActivityById(String id) {
        CRMApiUtils.deleteActivityById(id);
    }

    @Override
    public void batchDeleteActivity(List<String> ids) {
        String idList = JsonUtils.json2Str(ids);
        CRMApiUtils.batchDeleteActivity(idList);
    }

    @Override
    public void updateActivity(ActivityEntity activityEntity) {
        String jsonStr = JsonUtils.json2Str(activityEntity);
        CRMApiUtils.updateActivity(jsonStr);
    }

    @Override
    public void batchDelete(String idArr) {
        System.out.println(idArr);
    }

    @Override
    public void batchTransfer(String idArr) {
        System.out.println(idArr);
    }

    @Override
    public boolean followActivity(String id) {
        return false;
    }
}
