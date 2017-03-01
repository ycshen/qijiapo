package com.qjp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.RoleEntity;
import com.qjp.entity.RoleUserEntity;
import com.qjp.service.RoleService;
import com.qjp.util.JsonUtils;
import com.qjp.util.api.MyBaseApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.RoleVOQuery;
import com.qjp.util.vo.RoleVO;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: RoleServiceImpl.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @roleor <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class RoleServiceImpl implements RoleService{

	@Override
	public RoleVOQuery getRoleList(RoleVOQuery roleQuery) {
		String queryJson = JsonUtils.json2Str(roleQuery);
		String loginResult = MyBaseApiUtils.getRolePage(queryJson);
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
							List<RoleVO> list = JSONObject.parseArray(data, RoleVO.class);
							roleQuery.setItems(list);
						}
						
						Object countObj = jsonObject.get("count");
						if(countObj != null){
							String count = countObj.toString();
							roleQuery.setCount(Integer.parseInt(count));
						}
					}
				}
			}
		}
		
		return roleQuery;
	}

	@Override
	public RoleEntity getRoleById(String id) {
		String result = MyBaseApiUtils.getRoleById(id);
		RoleEntity role = null;
		if(StringUtils.isNotBlank(result)){
			JSONObject jsonObject = JSONObject.parseObject(result);
			if(jsonObject != null){
				Object codeObj = jsonObject.get("code");
				if(codeObj != null){
					String code = codeObj.toString();
					if (ApiCode.OK.toString().equals(code)) {
						Object dataObj = jsonObject.get("data");
						if(dataObj != null){
							String data = dataObj.toString();
							role = JSONObject.parseObject(data, RoleEntity.class);
						}
					}
				}
			}
		}
		
		return role;
	}

	@Override
	public void cancelRole(List<RoleUserEntity> roleUserList) {
		String roleUserJson = JsonUtils.json2Str(roleUserList);
		MyBaseApiUtils.cancelRoles(roleUserJson);
	}

	@Override
	public void batchRole(String companyId, String roleId,
			String roleUserIdArray, String notRoleUserIdArray) {
		MyBaseApiUtils.batchRole(companyId, roleId, roleUserIdArray, notRoleUserIdArray);
	}

	
}

