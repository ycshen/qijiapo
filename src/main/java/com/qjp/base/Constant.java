package com.qjp.base;

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
public class Constant {
	public static final String ZREO = "0";
	public static final String ONE = "1";
	public static final String TWO = "2";
	public static final String THREE = "3";
	public static final String FOUR = "4";
	public static final String RETURN_SUCCESS = "1";
	public static final String RETURN_EXISTCODE = "2";
	public static final String RETURN_EXISTNUM = "3";
	public static final String HK_CODE = "852";
	public static final String MO_CODE = "853";
	public static final Integer AREA_CODE_START = 10;
	public static final Integer AREA_CODE_END = 30;
	
	public static final String[] DIRECT_CONTROLLER_CODE = {"010", "021", "022", "023"}; //直辖市
	public static final String[] DIRECT_CONTROLLER_NAME = {"北京", "上海", "天津", "重庆"}; //直辖市
	public static final String CODE_200 = "200";
	/**
	 * 环境常量
	 */
	public static final String local = "local";
	public static final String test = "test";
	public static final String prod = "prod";
	
	@Value("${mybase}")
	private String mybase_url;
	public static String mybase;
	@Value("${mybase.getSubCompanyPage}")
	private String mybase_getSubCompanyPage_url;
	public static String mybase_getSubCompanyPage;
	@Value("${mybase.insertCompany}")
	private String mybase_insertCompany_url;
	public static String mybase_insertCompany;
	@Value("${mybase.login}")
	private String mybase_login_url;
	public static String mybase_login;
	@Value("${mybase.close}")
	private String mybase_close_url;
	public static String mybase_close;
	@Value("${mybase.activate}")
	private String mybase_activate_url;
	public static String mybase_activate;
	@Value("${mybase.log}")
	private String mybase_log_url;
	public static String mybase_log;
	@Value("${mybase.getAdminLogs}")
	private String mybase_getAdminLogs_url;
	public static String mybase_getAdminLogs;
	@PostConstruct
	public void init() {
		mybase_login = this.mybase_login_url;
		mybase = this.mybase_url;
		mybase_getSubCompanyPage = this.mybase_getSubCompanyPage_url;
		mybase_insertCompany = this.mybase_insertCompany_url;
		mybase_close = this.mybase_close_url;
		mybase_activate = this.mybase_activate_url;
		mybase_log = this.mybase_log_url;
		mybase_getAdminLogs = this.mybase_getAdminLogs_url;
	}
	
}

