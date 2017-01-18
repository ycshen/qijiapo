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

	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	@Value("${mybase}")
	private String mybase_url;
	public static String mybase;
	@Value("${mybase.getSubCompanyPage}")
	private String mybase_getSubCompanyPage_url;
	public static String mybase_getSubCompanyPage;
	@Value("${mybase.insertCompany}")
	private String mybase_insertCompany_url;
	public static String mybase_insertCompany;
	@Value("${mybase.updateCompany}")
	private String mybase_updateCompany_url;
	public static String mybase_updateCompany;
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
	@Value("${mybase.getMenus}")
	private String mybase_getMenus_url;
	public static String mybase_getMenus;
	@Value("${mybase.getCompanyById}")
	private String mybase_getCompanyById_url;
	public static String mybase_getCompanyById;
	
	//department
	@Value("${mybase.getDepByCompanyId}")
	private String mybase_getDepByCompanyId_url;
	public static String mybase_getDepByCompanyId;
	@Value("${mybase.isExistDepartment}")
	private String mybase_isExistDepartment_url;
	public static String mybase_isExistDepartment;
	@Value("${mybase.insertDepartment}")
	private String mybase_insertDepartment_url;
	public static String mybase_insertDepartment;
	@Value("${mybase.updateDepartment}")
	private String mybase_updateDepartment_url; //更新部门信息
	public static String mybase_updateDepartment; 
	@Value("${mybase.getDepById}")
	private String mybase_getDepById_url; //根据部门id获取部门信息
	public static String mybase_getDepById;
	
	
	//User
	@Value("${mybase.getUserPage}")
	private String mybase_getUserPage_url;
	public static String mybase_getUserPage;
	@Value("${mybase.updateUser}")
	private String mybase_updateUser_url;
	public static String mybase_updateUser;
	@Value("${mybase.insertUser}")
	private String mybase_insertUser_url;
	public static String mybase_insertUser;
	
	
	//Company
	@Value("${mybase.getCompanyStaffTreeById}")
	private String mybase_getCompanyStaffTreeById_url;
	public static String mybase_getCompanyStaffTreeById;
	
	
	@PostConstruct
	public void init() {
		mybase_login = this.mybase_login_url;
		mybase = this.mybase_url;
		mybase_getSubCompanyPage = this.mybase_getSubCompanyPage_url;
		mybase_insertCompany = this.mybase_insertCompany_url;
		mybase_updateCompany = this.mybase_updateCompany_url;
		mybase_close = this.mybase_close_url;
		mybase_activate = this.mybase_activate_url;
		mybase_log = this.mybase_log_url;
		mybase_getAdminLogs = this.mybase_getAdminLogs_url;
		mybase_getMenus = this.mybase_getMenus_url;
		mybase_getCompanyById = this.mybase_getCompanyById_url;
		mybase_getCompanyStaffTreeById = this.mybase_getCompanyStaffTreeById_url;
		//department
		mybase_getDepByCompanyId = this.mybase_getDepByCompanyId_url;
		mybase_isExistDepartment = this.mybase_isExistDepartment_url;
		mybase_updateDepartment = this.mybase_updateDepartment_url;
		mybase_insertDepartment = this.mybase_insertDepartment_url;
		mybase_getDepById = this.mybase_getDepById_url;
		
		//user
		mybase_updateUser = this.mybase_updateUser_url;
		mybase_insertUser = this.mybase_insertUser_url;
		mybase_getUserPage = this.mybase_getUserPage_url;
	}
	
}

