package com.qjp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.LogEntity;
import com.qjp.service.LogService;
import com.qjp.util.api.MyBaseApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.LogQuery;



/** 
 * <p>Project: MyBase</p> 
 * <p>Title: ConfigService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class LogServiceImpl implements LogService{

	@Override
	public LogQuery getLogPage(LogQuery logQuery) {
		String companyId = logQuery.getCompanyId();
		Integer pageSize = logQuery.getSize();
		Integer currentPage = logQuery.getPage();
		String loginResult = MyBaseApiUtils.getAdminLogs(companyId, pageSize.toString(), currentPage.toString());
		if(StringUtils.isNotBlank(loginResult)){
			JSONObject jsonObject = JSONObject.parseObject(loginResult);
			if(jsonObject != null){
				Object codeObj = jsonObject.get("code");
				if(codeObj != null){
					String code = codeObj.toString();
					if (ApiCode.OK.toString().equals(code)) {
						Object dataObj = jsonObject.get("data");
						if(dataObj != null){
							String data = dataObj.toString();
							List<LogEntity> list = JSONObject.parseArray(data, LogEntity.class);
							logQuery.setItems(list);
						}
						
						Object countObj = jsonObject.get("count");
						if(countObj != null){
							String count = countObj.toString();
							logQuery.setCount(Integer.parseInt(count));
						}
					}
				}
			}
		}
		
		return logQuery;
	}
	
	
}

