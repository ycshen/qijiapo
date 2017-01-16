package com.qjp.service;

import java.util.List;

import com.qjp.entity.MenuEntity;
import com.qjp.util.query.MenuQuery;

/** 
 * <p>Project: MyBase</p> 
 * <p>Title: MenuService.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public interface MenuService {
	void insertMenu(MenuEntity menu);
	MenuQuery getMenuPage(MenuQuery menuQuery);
	void updateMenu(MenuEntity menu);
	MenuEntity getMenuById(Integer id);
	List<MenuEntity> getMenuList(String userId);
	void deleteMenuById(String id);
	MenuEntity getMenuByNameAndType(String menuName, String menuType);
	MenuEntity getMenuByNameAndSystemId(String menuName, String systemId);
}

