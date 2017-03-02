package com.qjp.service;

import java.util.List;

import com.qjp.entity.RoleEntity;
import com.qjp.entity.RoleUserEntity;
import com.qjp.util.query.RoleVOQuery;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: RoleService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @roleor <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public interface RoleService {
	RoleVOQuery getRoleList(RoleVOQuery roleQuery);
	RoleEntity getRoleById(String id);
	void cancelRole(List<RoleUserEntity> roleUserList);
	void batchRole(String companyId, String roleId, String roleUserIdArray, String notRoleUserIdArray);
}

