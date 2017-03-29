package com.qjp.util.api;

import com.alibaba.fastjson.JSONObject;
import com.qjp.base.url.CRMApiUrl;
import com.qjp.util.CommonUtils;
import com.qjp.util.HttpUtils;
import com.qjp.util.SHA1Utils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * <p>Project: qijiapo</p>
 * <p>Title: CRMApiUtils.java</p>
 * <p>Description: TODO</p>
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public class CRMApiUtils {
    /**
     * 获取CRM地址
     *
     * @return
     */
    private static String getCRMUrl() {
        if (CommonUtils.getProdEnv()) {
            return CRMApiUrl.crm;
        } else {
            return CRMApiUrl.crm;
        }
    }


    /**
     * 新增竞争对手
     *
     * @param competitor 竞争对手
     * @return
     */
    public static String insertCompetitor(String competitor) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_insertCompetitor;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("competitor", competitor);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("competitor", competitor);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 更新竞争对手
     *
     * @param competitor 竞争对手
     * @return
     */
    public static String updateCompetitor(String competitor) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_updateCompetitor;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("competitor", competitor);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("competitor", competitor);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getCompetitorPage(String query) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_getCompetitorPage;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("query", query);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("query", query);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 新增联系人
     *
     * @param attn 竞争对手
     * @return
     */
    public static String insertAttn(String attn) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_insertAttn;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("attn", attn);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("attn", attn);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getAttnPage(String query) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_getAttnPage;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("query", query);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject object = new JSONObject();
            object.put("query", query);
            object.put("secret", secret);
            result = HttpUtils.postUrl(url, object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String getCompetitorById(String id) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_getCompetitorById;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("id", id);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("id", id);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getAttnById(String id) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_getAttnById;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("id", id);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("id", id);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量删除竞争对手
     *
     * @param idList
     * @return
     */
    public static String batchDeleteCompetitor(String idList) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_batchDeleteCompetitor;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("idList", idList);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("idList", idList);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 根据id删除竞争对手
     *
     * @param id
     * @return
     */
    public static String deleteCompetitorById(String id) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_deleteCompetitorById;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("id", id);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("id", id);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    //Product product
    public static String insertProduct(String product) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_insertProduct;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("product", product);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("product", product);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 更新产品
     *
     * @param product 产品
     * @return
     */
    public static String updateProduct(String product) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_updateProduct;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("product", product);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("product", product);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getProductPage(String query) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_getProductPage;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("query", query);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("query", query);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getProductById(String id) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_getProductById;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("id", id);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("id", id);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 批量删除产品
     *
     * @param idList
     * @return
     */
    public static String batchDeleteProduct(String idList) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_batchDeleteProduct;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("idList", idList);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("idList", idList);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 根据id删除产品
     *
     * @param id
     * @return
     */
    public static String deleteProductById(String id) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_deleteProductById;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("id", id);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("id", id);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String deleteAttnById(String id) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_deleteAttnById;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("id", id);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("id", id);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String batchDeleteAttn(String idList) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_batchDeleteAttn;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("idList", idList);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("idList", idList);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String updateAttn(String attn) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_updateAttn;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("competitor", attn);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("competitor", attn);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //SalesOpportunity
    public static String insertSalesOpportunity(String salesOpportunity) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_insertSalesOpportunity;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("salesOpportunity", salesOpportunity);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("salesOpportunity", salesOpportunity);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 更新销售机会
     *
     * @param salesOpportunity 销售机会
     * @return
     */
    public static String updateSalesOpportunity(String salesOpportunity) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_updateSalesOpportunity;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("salesOpportunity", salesOpportunity);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("salesOpportunity", salesOpportunity);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getSalesOpportunityPage(String query) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_getSalesOpportunityPage;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("query", query);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("query", query);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getSalesOpportunityById(String id) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_getSalesOpportunityById;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("id", id);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("id", id);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 批量删除销售机会
     *
     * @param idList
     * @return
     */
    public static String batchDeleteSalesOpportunity(String idList) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_batchDeleteSalesOpportunity;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("idList", idList);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("idList", idList);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 根据id删除销售机会
     *
     * @param id
     * @return
     */
    public static String deleteSalesOpportunityById(String id) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_deleteSalesOpportunityById;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("id", id);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("id", id);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //Customer
    public static String insertCustomer(String customer) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_insertCustomer;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("customer", customer);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("customer", customer);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 更新客户
     *
     * @param customer 客户
     * @return
     */
    public static String updateCustomer(String customer) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_updateCustomer;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("customer", customer);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("customer", customer);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getCustomerPage(String query) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_getCustomerPage;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("query", query);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("query", query);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getSelfCustomerCount(String query) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_getSelfCustomerCount;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("query", query);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("query", query);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getCustomerById(String id) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_getCustomerById;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("id", id);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("id", id);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 批量删除客户
     *
     * @param idList
     * @return
     */
    public static String batchDeleteCustomer(String idList) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_batchDeleteCustomer;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("idList", idList);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("idList", idList);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 根据id删除客户
     *
     * @param id
     * @return
     */
    public static String deleteCustomerById(String id) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_deleteCustomerById;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("id", id);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("id", id);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 添加市场活动
     * @param objStr
     * @return
     */
    public static String insertActivity(String objStr) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_insertActivity;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("activity", objStr);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("activity", objStr);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 获取市场活动列表
     * @param objStr
     * @return
     */
    public static String getActivityPage(String objStr) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_getActivityPage;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("query", objStr);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("query", objStr);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String deleteActivityById(String id) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_deleteActivityById;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("id", id);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("id", id);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String batchDeleteActivity(String idList) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_batchDeleteActivity;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("idList", idList);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("idList", idList);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String updateActivity(String activity) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_updateActivity;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("activity", activity);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("activity", activity);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getActivityById(String id) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_getActivityById;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("id", id);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("id", id);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String insertContract(String jsonStr) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_insertContract;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("contract", jsonStr);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("contract", jsonStr);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getContractPage(String json) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_getContractPage;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("query", json);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("query", json);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getContractById(String id) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_getContractById;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("id", id);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("id", id);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    public static String deleteContractById(String id) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_deleteContractById;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("id", id);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("id", id);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    public static String batchDeleteContract(String idList) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_batchDeleteContract;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("idList", idList);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("idList", idList);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String updateContract(String jsonStr) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_updateContract;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("contract", jsonStr);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("contract", jsonStr);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String insertSalesLeads(String jsonStr) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_insertSalesLeads;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("salesLeads", jsonStr);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("salesLeads", jsonStr);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getSalesLeadsPage(String json) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_getSalesLeadsPage;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("query", json);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("query", json);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String getSalesLeadsById(String id) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_getSalesLeadsById;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("id", id);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("id", id);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    public static String deleteSalesLeadsById(String id) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_deleteSalesLeadsById;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("id", id);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("id", id);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    public static String batchDeleteSalesLeads(String idList) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_batchDeleteSalesLeads;
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("idList", idList);
            String secret = SHA1Utils.SHA1(maps);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("secret", secret);
            jsonObject.put("idList", idList);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String updateSalesLeads(String jsonStr) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_updateSalesLeads;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("salesLeads", jsonStr);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("salesLeads", jsonStr);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 插入销售机会和产品的对应关系
     * @param jsonStr
     * @return
     */
    public static String insertSop(String jsonStr) {
        String result = StringUtils.EMPTY;
        try {
            String url = getCRMUrl() + CRMApiUrl.crm_insertSop;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sop", jsonStr);
            Map<String, Object> maps = SHA1Utils.getSha1Map();
            maps.put("sop", jsonStr);
            String secret = SHA1Utils.SHA1(maps);
            jsonObject.put("secret", secret);
            result = HttpUtils.postUrl(url, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}

