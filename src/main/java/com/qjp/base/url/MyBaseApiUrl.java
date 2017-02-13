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
public class MyBaseApiUrl {
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
	
	//log
	@Value("${mybase.log}")
	private String mybase_log_url;
	public static String mybase_log;
	@Value("${mybase.getAdminLogs}")
	private String mybase_getAdminLogs_url;	
	public static String mybase_getAdminLogs;
	
	//menu
	
	@Value("${mybase.getOuterSystemPage}")
	private String mybase_getOuterSystemPage_url;
	public static String mybase_getOuterSystemPage;
	@Value("${mybase.getMenus}")
	private String mybase_getMenus_url;
	public static String mybase_getMenus;
	
	
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
	@Value("${mybase.getNoSubDepListByCId}")
	private String mybase_getNoSubDepListByCId_url; //获取无子节点的部门信息
	public static String mybase_getNoSubDepListByCId;
	@Value("${mybase.deleteDepartmentById}")
	private String mybase_deleteDepartmentById_url; //根据部门id删除部门信息
	public static String mybase_deleteDepartmentById;
	
	
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
	@Value("${mybase.forbidUser}")
	private String mybase_forbidUser_url;
	public static String mybase_forbidUser;
	@Value("${mybase.resetPassword}")
	private String mybase_resetPassword_url;
	public static String mybase_resetPassword;
	@Value("${mybase.getUserById}")
	private String mybase_getUserById_url;
	public static String mybase_getUserById;
	@Value("${mybase.getUserListByAuthId}")
	private String mybase_getUserListByAuthId_url;
	public static String mybase_getUserListByAuthId;
	@Value("${mybase.getAuthUserByCidAndAuthId}")
	private String mybase_getAuthUserByCidAndAuthId_url;
	public static String mybase_getAuthUserByCidAndAuthId;
	
	
	//Company
	@Value("${mybase.getCompanyStaffTreeById}")
	private String mybase_getCompanyStaffTreeById_url;
	public static String mybase_getCompanyStaffTreeById;
	@Value("${mybase.getCompanyById}")
	private String mybase_getCompanyById_url;
	public static String mybase_getCompanyById;
	
	//Position
	@Value("${mybase.getSystemPosition}")
	private String mybase_getSystemPosition_url;
	public static String mybase_getSystemPosition;
	@Value("${mybase.getPositionByCompanyId}")
	private String mybase_getPositionByCompanyId_url;
	public static String mybase_getPositionByCompanyId;	
	@Value("${mybase.updatePosition}")
	private String mybase_updatePosition_url;
	public static String mybase_updatePosition;	
	@Value("${mybase.isExistPosition}")
	private String mybase_isExistPosition_url;
	public static String mybase_isExistPosition;	
	@Value("${mybase.insertPosition}")
	private String mybase_insertPosition_url;
	public static String mybase_insertPosition;	
	@Value("${mybase.getPositionPage}")
	private String mybase_getPositionPage_url;
	public static String mybase_getPositionPage;	
	@Value("${mybase.changePositionStatus}")
	private String mybase_changePositionStatus_url;
	public static String mybase_changePositionStatus;
	@Value("${mybase.getPositionById}")
	private String mybase_getPositionById_url;
	public static String mybase_getPositionById;

	//authority
	@Value("${mybase.getAuthPage}")
	private String mybase_getAuthPage_url;
	public static String mybase_getAuthPage;
	@Value("${mybase.getAuthById}")
	private String mybase_getAuthById_url;
	public static String mybase_getAuthById;
	@Value("${mybase.cancelAuth}")
	private String mybase_cancelAuth_url;
	public static String mybase_cancelAuth;
	@Value("${mybase.insertAuth}")
	private String mybase_insertAuth_url;
	public static String mybase_insertAuth;
	
	@PostConstruct
	public void init() {
		mybase_login = this.mybase_login_url;
		mybase = this.mybase_url;
		mybase_getSubCompanyPage = this.mybase_getSubCompanyPage_url;
		
		//company
		mybase_insertCompany = this.mybase_insertCompany_url;
		mybase_updateCompany = this.mybase_updateCompany_url;
		mybase_close = this.mybase_close_url;
		mybase_activate = this.mybase_activate_url;
		mybase_log = this.mybase_log_url;
		mybase_getAdminLogs = this.mybase_getAdminLogs_url;
		//menu
		mybase_getOuterSystemPage = this.mybase_getOuterSystemPage_url;
		mybase_getMenus = this.mybase_getMenus_url;
		mybase_getCompanyById = this.mybase_getCompanyById_url;
		mybase_getCompanyStaffTreeById = this.mybase_getCompanyStaffTreeById_url;
		//department
		mybase_getDepByCompanyId = this.mybase_getDepByCompanyId_url;
		mybase_isExistDepartment = this.mybase_isExistDepartment_url;
		mybase_updateDepartment = this.mybase_updateDepartment_url;
		mybase_insertDepartment = this.mybase_insertDepartment_url;
		mybase_getDepById = this.mybase_getDepById_url;
		mybase_getNoSubDepListByCId = this.mybase_getNoSubDepListByCId_url;
		mybase_deleteDepartmentById = this.mybase_deleteDepartmentById_url;
		//user
		mybase_updateUser = this.mybase_updateUser_url;
		mybase_insertUser = this.mybase_insertUser_url;
		mybase_getUserPage = this.mybase_getUserPage_url;
		mybase_forbidUser = this.mybase_forbidUser_url;
		mybase_resetPassword = this.mybase_resetPassword_url;
		mybase_getUserById = this.mybase_getUserById_url;
		mybase_getUserListByAuthId = this.mybase_getUserListByAuthId_url;
		mybase_getAuthUserByCidAndAuthId = this.mybase_getAuthUserByCidAndAuthId_url;
		//position
		mybase_getSystemPosition = this.mybase_getSystemPosition_url;
		mybase_getPositionByCompanyId = this.mybase_getPositionByCompanyId_url;
		mybase_updatePosition = this.mybase_updatePosition_url;
		mybase_isExistPosition = this.mybase_isExistPosition_url;
		mybase_insertPosition = this.mybase_insertPosition_url;
		mybase_getPositionPage = this.mybase_getPositionPage_url;
		mybase_changePositionStatus = this.mybase_changePositionStatus_url;
		mybase_getPositionById = this.mybase_getPositionById_url;
		
		//authority
		mybase_getAuthPage = this.mybase_getAuthPage_url;
		mybase_getAuthById = this.mybase_getAuthById_url;
		mybase_cancelAuth = this.mybase_cancelAuth_url;
		mybase_insertAuth = this.mybase_insertAuth_url;
	}
	
}

