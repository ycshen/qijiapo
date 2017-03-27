package com.qjp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.ContractEntity;
import com.qjp.entity.ContractEntity;
import com.qjp.service.ContractService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.CRMApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.ContractQuery;
import com.qjp.util.query.ContractQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fengyue on 2017/3/24.
 */
@Service
public class ContractServiceImpl implements ContractService {
    
    @Override
    public String insertContract(ContractEntity contract) {
        String jsonStr = JsonUtils.json2Str(contract);
        String result = CRMApiUtils.insertContract(jsonStr);
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
    public ContractQuery getContractPage(ContractQuery contractQuery) {
        String json = JsonUtils.json2Str(contractQuery);
        String loginResult = CRMApiUtils.getContractPage(json);
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
                            List<ContractEntity> list = JSONObject.parseArray(data, ContractEntity.class);
                            contractQuery.setItems(list);
                        }

                        Object countObj = jsonObject.get("count");
                        if(countObj != null){
                            String count = countObj.toString();
                            contractQuery.setCount(Integer.parseInt(count));
                        }
                    }
                }
            }
        }

        return contractQuery;
    }

    @Override
    public ContractEntity getContractById(String id) {
        String result = CRMApiUtils.getContractById(id);
        ContractEntity contract = null;
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
                            contract = JSONObject.parseObject(data, ContractEntity.class);
                        }
                    }
                }
            }
        }

        return contract;
    }

    @Override
    public void deleteContractById(String id) {
        CRMApiUtils.deleteContractById(id);
    }

    @Override
    public void batchDeleteContract(List<String> ids) {
        String idList = JsonUtils.json2Str(ids);
        CRMApiUtils.batchDeleteContract(idList);
    }

    @Override
    public void updateContract(ContractEntity contract) {
        String jsonStr = JsonUtils.json2Str(contract);
        CRMApiUtils.updateContract(jsonStr);
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
