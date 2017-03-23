package com.qjp.base.url;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** 
 * <p>Project: BRP</p> 
 * <p>Title: Constant.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Component
public class CRMApiUrl {
	@Value("${qijiapoCrm}")
	private String crm_url;
	public static String crm;
	
	//competitor
	@Value("${crm.insertCompetitor}")
	private String crm_insertCompetitor_url;
	public static String crm_insertCompetitor;
	@Value("${crm.getCompetitorPage}")
	private String crm_getCompetitorPage_url;
	public static String crm_getCompetitorPage;
	@Value("${crm.getCompetitorById}")
	private String crm_getCompetitorById_url;
	public static String crm_getCompetitorById;
	@Value("${crm.batchDeleteCompetitor}")
	private String crm_batchDeleteCompetitor_url;
	public static String crm_batchDeleteCompetitor;
	@Value("${crm.deleteCompetitorById}")
	private String crm_deleteCompetitorById_url;
	public static String crm_deleteCompetitorById;
	@Value("${crm.updateCompetitor}")
	private String crm_updateCompetitor_url;
	public static String crm_updateCompetitor;
	
	//attn
	@Value("${crm.insertAttn}")
	private String crm_insertAttn_url;
	public static String crm_insertAttn;
	@Value("${crm.getAttnPage}")
	private String crm_attnPage_url;
	public static String crm_getAttnPage;
	@Value("${crm.getAttnById}")
	private String crm_getAttnById_url;
	public static String crm_getAttnById;
	@Value("${crm.deleteAttnById}")
	private String crm_deleteAttnById_url;
	public static String crm_deleteAttnById;
	@Value("${crm.batchDeleteAttn}")
	private String crm_batchDeleteAttn_url;
	public static String crm_batchDeleteAttn;
	@Value("${crm.updateAttn}")
	private String crm_updateAttn_url;
	public static String crm_updateAttn;
	
	//Product
	@Value("${crm.insertProduct}")
	private String crm_insertProduct_url;
	public static String crm_insertProduct;
	@Value("${crm.getProductPage}")
	private String crm_getProductPage_url;
	public static String crm_getProductPage;
	@Value("${crm.getProductById}")
	private String crm_getProductById_url;
	public static String crm_getProductById;
	@Value("${crm.batchDeleteProduct}")
	private String crm_batchDeleteProduct_url;
	public static String crm_batchDeleteProduct;
	@Value("${crm.deleteProductById}")
	private String crm_deleteProductById_url;
	public static String crm_deleteProductById;
	@Value("${crm.updateProduct}")
	private String crm_updateProduct_url;
	public static String crm_updateProduct;

	//activity
	@Value("${crm.insertActivity}")
	private String crm_insertActivity_url;
	public static String crm_insertActivity;
	@Value("${crm.getActivityPage}")
	private String crm_getActivityPage_url;
	public static String crm_getActivityPage;
	@Value("${crm.getActivityById}")
	private String crm_getActivityById_url;
	public static String crm_getActivityById;
	@Value("${crm.batchDeleteActivity}")
	private String crm_batchDeleteActivity_url;
	public static String crm_batchDeleteActivity;
	@Value("${crm.deleteActivityById}")
	private String crm_deleteActivityById_url;
	public static String crm_deleteActivityById;
	@Value("${crm.updateProduct}")
	private String crm_updateActivity_url;
	public static String crm_updateActivity;

	//SalesOpportunity
	@Value("${crm.insertSalesOpportunity}")
	private String crm_insertSalesOpportunity_url;
	public static String crm_insertSalesOpportunity;
	@Value("${crm.getSalesOpportunityPage}")
	private String crm_getSalesOpportunityPage_url;
	public static String crm_getSalesOpportunityPage;
	@Value("${crm.getSalesOpportunityById}")
	private String crm_getSalesOpportunityById_url;
	public static String crm_getSalesOpportunityById;
	@Value("${crm.batchDeleteSalesOpportunity}")
	private String crm_batchDeleteSalesOpportunity_url;
	public static String crm_batchDeleteSalesOpportunity;
	@Value("${crm.deleteSalesOpportunityById}")
	private String crm_deleteSalesOpportunityById_url;
	public static String crm_deleteSalesOpportunityById;
	@Value("${crm.updateSalesOpportunity}")
	private String crm_updateSalesOpportunity_url;
	public static String crm_updateSalesOpportunity;
	//Customer
	@Value("${crm.insertCustomer}")
	private String crm_insertCustomer_url;
	public static String crm_insertCustomer;
	@Value("${crm.getCustomerPage}")
	private String crm_getCustomerPage_url;
	public static String crm_getCustomerPage;
	@Value("${crm.getCustomerById}")
	private String crm_getCustomerById_url;
	public static String crm_getCustomerById;
	@Value("${crm.batchDeleteCustomer}")
	private String crm_batchDeleteCustomer_url;
	public static String crm_batchDeleteCustomer;
	@Value("${crm.deleteCustomerById}")
	private String crm_deleteCustomerById_url;
	public static String crm_deleteCustomerById;
	@Value("${crm.updateCustomer}")
	private String crm_updateCustomer_url;
	public static String crm_updateCustomer;
	@PostConstruct
	public void init() {
		crm = this.crm_url;
		//customer
		crm_insertCustomer = this.crm_insertCustomer_url;

		//competitor
		crm_insertCompetitor = this.crm_insertCompetitor_url;
		crm_getCompetitorPage = this.crm_getCompetitorPage_url;
		crm_getCompetitorById = this.crm_getCompetitorById_url;
		crm_batchDeleteCompetitor = this.crm_batchDeleteCompetitor_url;
		crm_deleteCompetitorById = this.crm_deleteCompetitorById_url;
		crm_updateCompetitor = this.crm_updateCompetitor_url;

		//attn
		crm_insertAttn = this.crm_insertAttn_url;
		crm_getAttnPage = this.crm_attnPage_url;
		crm_getAttnById = this.crm_getAttnById_url;
		crm_deleteAttnById = this.crm_deleteAttnById_url;
		crm_batchDeleteAttn = this.crm_batchDeleteAttn_url;
		crm_updateAttn = this.crm_updateAttn_url;

		//Product
		crm_insertProduct = this.crm_insertProduct_url;
		crm_getProductPage = this.crm_getProductPage_url;
		crm_getProductById = this.crm_getProductById_url;
		crm_batchDeleteProduct = this.crm_batchDeleteProduct_url;
		crm_deleteProductById = this.crm_deleteProductById_url;
		crm_updateProduct = this.crm_updateProduct_url;

		//SalesOpportunity
		crm_insertSalesOpportunity = this.crm_insertSalesOpportunity_url;
		crm_getSalesOpportunityPage = this.crm_getSalesOpportunityPage_url;
		crm_getSalesOpportunityById = this.crm_getSalesOpportunityById_url;
		crm_batchDeleteSalesOpportunity = this.crm_batchDeleteSalesOpportunity_url;
		crm_deleteSalesOpportunityById = this.crm_deleteSalesOpportunityById_url;
		crm_updateSalesOpportunity = this.crm_updateSalesOpportunity_url;

		//Customer
		crm_insertCustomer = this.crm_insertCustomer_url;
		crm_getCustomerPage = this.crm_getCustomerPage_url;
		crm_getCustomerById = this.crm_getCustomerById_url;
		crm_batchDeleteCustomer = this.crm_batchDeleteCustomer_url;
		crm_deleteCustomerById = this.crm_deleteCustomerById_url;
		crm_updateCustomer = this.crm_updateCustomer_url;
		//activity
		crm_insertActivity = this.crm_insertActivity_url;
		crm_getActivityPage = this.crm_getActivityPage_url;
		crm_getActivityById = this.crm_getActivityById_url;
		crm_batchDeleteActivity = this.crm_batchDeleteActivity_url;
		crm_deleteActivityById = this.crm_deleteActivityById_url;
		crm_updateActivity = this.crm_updateActivity_url;
	}
	
}

