package com.qjp.service;

import com.qjp.util.query.AuthorityQuery;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: AuthorityService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public interface AuthorityService {
	AuthorityQuery getAuthorityList(AuthorityQuery authQuery);
}

