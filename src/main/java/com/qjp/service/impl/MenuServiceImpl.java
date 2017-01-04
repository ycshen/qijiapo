package com.qjp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qjp.entity.MenuEntity;
import com.qjp.service.MenuService;
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
	public List<MenuEntity> getMenuListByCode(String code) {
		
		
		return null;
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
}

