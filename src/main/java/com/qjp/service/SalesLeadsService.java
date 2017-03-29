package com.qjp.service;

import com.qjp.entity.SalesLeadsEntity;
import com.qjp.util.query.SalesLeadsQuery;

import java.util.List;

/**
 * Created by fengyue on 2017/3/28.
 */
public interface SalesLeadsService {
    String insertSalesLeads(SalesLeadsEntity salesLeads);
    SalesLeadsQuery getSalesLeadsPage(SalesLeadsQuery salesLeadsQuery);
    SalesLeadsEntity getSalesLeadsById(String id);
    void deleteSalesLeadsById(String id);
    void batchDeleteSalesLeads(List<String> ids);
    void updateSalesLeads(SalesLeadsEntity salesLeads);
    void batchDelete(String idArr);
    void batchTransfer(String idArr);
}
