package com.qjp.util.query;

import com.qjp.entity.ContractEntity;
import com.qjp.model.pageutil.Page;

/**
 * Created by fengyue on 2017/3/24.
 */
public class ContractQuery extends Page<ContractEntity> {

    private Long id;
    private String contractName;

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
