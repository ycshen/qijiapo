package com.qjp.service;

import com.qjp.entity.ContractEntity;
import com.qjp.util.query.ContractQuery;

import java.util.List;

/**
 * Created by fengyue on 2017/3/24.
 */
public interface ContractService {

    String insertContract(ContractEntity contract);
    ContractQuery getContractPage(ContractQuery contractQuery);
    ContractEntity getContractById(String id);
    void deleteContractById(String id);
    void batchDeleteContract(List<String> ids);
    void updateContract(ContractEntity contract);
    void batchDelete(String idArr);
    void batchTransfer(String idArr);
}
