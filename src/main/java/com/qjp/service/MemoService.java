package com.qjp.service;

import java.util.List;

import com.qjp.entity.ConfigEntity;
import com.qjp.entity.MemoEntity;
import com.qjp.util.query.ConfigQuery;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: ConfigService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public interface MemoService {
	void insertMemo(MemoEntity memo);
	List<MemoEntity> getTodayMemo(String userId);
	List<MemoEntity> getWeekMemo(String userId);
	List<MemoEntity> getMonthMemo(String userId);
}

