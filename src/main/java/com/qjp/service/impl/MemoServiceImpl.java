package com.qjp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjp.entity.ConfigEntity;
import com.qjp.entity.MemoEntity;
import com.qjp.service.ConfigService;
import com.qjp.service.MemoService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.MyBaseApiUtils;
import com.qjp.util.query.ConfigQuery;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: ConfigService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class MemoServiceImpl implements MemoService{

	@Override
	public void insertMemo(MemoEntity memo) {
		String memoJson = JsonUtils.json2Str(memo);
		MyBaseApiUtils.insertMemo(memoJson);
	}

}

