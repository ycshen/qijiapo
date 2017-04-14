package com.qjp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.WorkAttendancePlaceEntity;
import com.qjp.entity.WorkAttendancePlaceEntity;
import com.qjp.entity.WorkAttendancePlaceEntity;
import com.qjp.service.WorkAttendancePlaceService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.CRMApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.WorkAttendancePlaceQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fengyue on 2017/4/13.
 */
@Service
public class WorkAttendancePlaceServiceImpl implements WorkAttendancePlaceService {

    
    @Override
    public String insertWorkAttendancePlace(WorkAttendancePlaceEntity workAttendancePlace) {
        String jsonStr = JsonUtils.json2Str(workAttendancePlace);
        String result = CRMApiUtils.insertWorkAttendancePlace(jsonStr);
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
    public WorkAttendancePlaceQuery getWorkAttendancePlacePage(WorkAttendancePlaceQuery workAttendancePlaceQuery) {
        String json = JsonUtils.json2Str(workAttendancePlaceQuery);
        String loginResult = CRMApiUtils.getWorkAttendancePlacePage(json);
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
                            List<WorkAttendancePlaceEntity> list = JSONObject.parseArray(data, WorkAttendancePlaceEntity.class);
                            workAttendancePlaceQuery.setItems(list);
                        }

                        Object countObj = jsonObject.get("count");
                        if(countObj != null){
                            String count = countObj.toString();
                            workAttendancePlaceQuery.setCount(Integer.parseInt(count));
                        }
                    }
                }
            }
        }

        return workAttendancePlaceQuery;
    }

    @Override
    public WorkAttendancePlaceEntity getWorkAttendancePlaceById(String id) {
        String result = CRMApiUtils.getWorkAttendancePlaceById(id);
        WorkAttendancePlaceEntity workAttendancePlace = null;
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
                            workAttendancePlace = JSONObject.parseObject(data, WorkAttendancePlaceEntity.class);
                        }
                    }
                }
            }
        }

        return workAttendancePlace;
    }

    @Override
    public void deleteWorkAttendancePlaceById(String id) {
        CRMApiUtils.deleteWorkAttendancePlaceById(id);
    }

    @Override
    public void batchDeleteWorkAttendancePlace(List<String> ids) {
        String idList = JsonUtils.json2Str(ids);
        CRMApiUtils.batchDeleteWorkAttendancePlace(idList);
    }

    @Override
    public String updateWorkAttendancePlace(WorkAttendancePlaceEntity workAttendancePlace) {
        String jsonStr = JsonUtils.json2Str(workAttendancePlace);
        return CRMApiUtils.updateWorkAttendancePlace(jsonStr);
    }

    @Override
    public void enableOrDisableWorkAttendancePlaceById(String id) {
        CRMApiUtils.enableOrDisableWorkAttendancePlaceById(id);
    }
}
