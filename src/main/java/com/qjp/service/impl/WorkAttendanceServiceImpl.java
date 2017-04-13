package com.qjp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.WorkAttendanceEntity;
import com.qjp.entity.WorkAttendanceEntity;
import com.qjp.service.WorkAttendanceService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.CRMApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.WorkAttendanceQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fengyue on 2017/4/13.
 */
@Service
public class WorkAttendanceServiceImpl implements WorkAttendanceService {
    @Override
    public String insertWorkAttendance(WorkAttendanceEntity workAttendance) {
        String jsonStr = JsonUtils.json2Str(workAttendance);
        String result = CRMApiUtils.insertWorkAttendance(jsonStr);
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
    public WorkAttendanceQuery getWorkAttendancePage(WorkAttendanceQuery workAttendanceQuery) {
        String json = JsonUtils.json2Str(workAttendanceQuery);
        String loginResult = CRMApiUtils.getWorkAttendancePage(json);
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
                            List<WorkAttendanceEntity> list = JSONObject.parseArray(data, WorkAttendanceEntity.class);
                            workAttendanceQuery.setItems(list);
                        }

                        Object countObj = jsonObject.get("count");
                        if(countObj != null){
                            String count = countObj.toString();
                            workAttendanceQuery.setCount(Integer.parseInt(count));
                        }
                    }
                }
            }
        }

        return workAttendanceQuery;
    }

    @Override
    public WorkAttendanceEntity getWorkAttendanceById(String id) {
        String result = CRMApiUtils.getWorkAttendanceById(id);
        WorkAttendanceEntity workAttendance = null;
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
                            workAttendance = JSONObject.parseObject(data, WorkAttendanceEntity.class);
                        }
                    }
                }
            }
        }

        return workAttendance;
    }
}
