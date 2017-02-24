package com.qjp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qjp.entity.LogEntity;
import com.qjp.entity.MenuEntity;
import com.qjp.service.MenuService;
import com.qjp.util.api.MyBaseApiUtils;
import com.qjp.util.api.model.ApiCode;
import com.qjp.util.query.MenuQuery;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: MenuService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Service
public class MenuServiceImpl implements MenuService{

	@Override
	public void insertMenu(MenuEntity menu) {
		
	}
	
	@Override
	public MenuQuery getMenuPage(MenuQuery menuQuery) {
		
		return menuQuery;
	}

	@Override
	public void updateMenu(MenuEntity menu) {
		
	}

	@Override
	public MenuEntity getMenuById(Integer id) {
		
		return null;
	}

	@Override
	public List<MenuEntity> getMenuList(String userId) {
		String result = MyBaseApiUtils.getMenus(userId);
		List<MenuEntity> list = null;
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
							list = JSONObject.parseArray(data, MenuEntity.class);
						}
					}
				}
			}
		}
		
		return list;
	}

	@Override
	public void deleteMenuById(String id) {
	}

	@Override
	public MenuEntity getMenuByNameAndType(String menuName, String menuType) {
		return null;
	}

	@Override
	public MenuEntity getMenuByNameAndSystemId(String menuName, String systemId) {
		return null;
	}

	@Override
	public String getAllOutterMenu() {
		String result = MyBaseApiUtils.getAllOutterMenu();
		String data = "";
		List<MenuEntity> list = null;
		if(StringUtils.isNotBlank(result)){
			JSONObject jsonObject = JSONObject.parseObject(result);
			if(jsonObject != null){
				Object codeObj = jsonObject.get("code");
				if(codeObj != null){
					String code = codeObj.toString();
					if (ApiCode.OK.toString().equals(code)) {
						Object dataObj = jsonObject.get("data");
						if(dataObj != null){
							data = dataObj.toString();
						}
					}
				}
			}
		}
		
		return data;
	}
}

