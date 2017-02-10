package com.qjp.service;

import com.qjp.entity.AuthorityEntity;
import com.qjp.util.query.AuthorityVOQuery;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: AuthorityService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public interface AuthorityService {
	AuthorityVOQuery getAuthorityList(AuthorityVOQuery authQuery);
	AuthorityEntity getAuthById(String id);
}

