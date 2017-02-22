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
	//customer
	@Value("${crm.insertCustomer}")
	private String crm_insertCustomer_url;
	public static String crm_insertCustomer;
	
	//competitor
	@Value("${crm.insertCompetitor}")
	private String crm_insertCompetitor_url;
	public static String crm_insertCompetitor;
	@Value("${crm.getCompetitorPage}")
	private String crm_getCompetitorPage_url;
	public static String crm_getCompetitorPage;
	@PostConstruct
	public void init() {
		crm = this.crm_url;
		//customer
		crm_insertCustomer = this.crm_insertCustomer_url;
		
		//competitor
		crm_insertCompetitor = this.crm_insertCompetitor_url;
		crm_getCompetitorPage = this.crm_getCompetitorPage_url;
	}
	
}

