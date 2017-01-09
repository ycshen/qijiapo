package com.qjp.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qjp.base.Config;
import com.qjp.base.MenuEnum;
import com.qjp.entity.ConfigEntity;
import com.qjp.entity.MenuEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.ConfigService;
import com.qjp.service.MenuService;
import com.qjp.util.UserUtils;
import com.qjp.util.query.MenuQuery;
import com.qjp.util.vo.MenuTreeVO;
import com.google.gson.Gson;

/** 
 * <p>Project: qijiapo</p> 
 * <p>Title: AdminController.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Controller
@RequestMapping("/inner/admin")
public class AdminController extends BaseController{
	@Autowired
	private MenuService menuService;
	@Autowired
	private ConfigService configService;
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView adminIndex(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/admin/home");
		HttpSession seesion = request.getSession();
		UserEntity loginUser = (UserEntity) seesion.getAttribute("loginUser");
		mav.addObject("loginUser", loginUser); 
		return mav;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editMenu(@ModelAttribute MenuEntity menu, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/menu/menu_edit");
		Long id = menu.getId();
		if(id != null){
			menu = menuService.getMenuById(id.intValue());
		}
		
		List<ConfigEntity> configList = configService.getConfigListByCode(Config.MENU);
		mav.addObject("configList", configList);
		String formName = MenuEnum.getMenuTypeName(menu.getMenuType());
		mav.addObject("formName", formName);
		mav.addObject("menu", menu);
		return mav;
	}
	
	@RequestMapping(value = "/addSubMenu", method = RequestMethod.GET)
	public ModelAndView addSubMenu(MenuEntity menu, String isTree, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/menu/menu_edit");
		List<ConfigEntity> configList = configService.getConfigListByCode(Config.MENU);
		mav.addObject("configList", configList);
		
		String formName = MenuEnum.getMenuTypeName(menu.getMenuType());
		mav.addObject("formName", formName);
		mav.addObject("isTree", isTree);
		mav.addObject("menu", menu);
		return mav;
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView viewMenu(String id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/menu/menu_detail");
		MenuEntity menu = null;
		if(StringUtils.isNotBlank(id)){
			menu = menuService.getMenuById(Integer.parseInt(id));
		}
		
		mav.addObject("menu", menu);
		
		return mav;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listMenu(@ModelAttribute MenuQuery menuQuery, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/menu/menu_list");
		menuQuery = menuService.getMenuPage(menuQuery);
		mav.addObject("menuQuery", menuQuery);
		List<ConfigEntity> configList = configService.getConfigListByCode(Config.MENU);
		mav.addObject("configList", configList);
		
		return mav;
	}
	
	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	public ModelAndView tree(@ModelAttribute MenuQuery menuQuery, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/menu/menu_tree");
		/*menuQuery.setMenuType(MenuEnum.SYSTEM.getMenuType().toString());
		menuQuery = menuService.getMenuList(menuQuery);
		mav.addObject("treeData", menuQuery.getItems());*/
		
		return mav;
	}
	
	@RequestMapping(value = "/treeData", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String treeData(@ModelAttribute MenuQuery menuQuery, HttpServletRequest request){
		menuQuery.setMenuType(MenuEnum.SYSTEM.getMenuType().toString());
		menuQuery = menuService.getMenuPage(menuQuery);
		List<MenuEntity> list = menuQuery.getItems();
		String tree = StringUtils.EMPTY;
		List<MenuTreeVO> treeList = null;
		if(list != null && list.size() > 0){
			treeList = new LinkedList<MenuTreeVO>();
			MenuTreeVO treeVO = null;
			for (MenuEntity menuEntity : list) {
				treeVO = new MenuTreeVO();
				treeVO.setText(menuEntity.getMenuName());
				Integer idInt = menuEntity.getId().intValue();
				treeVO.setId(idInt);
				treeVO.setNodeId(idInt);
				String id = menuEntity.getId().toString();
				menuQuery = new MenuQuery();
				menuQuery.setParentMenuId(id);
				menuQuery = menuService.getMenuPage(menuQuery);
				List<MenuTreeVO> nodes = this.getNodes(menuQuery.getItems(), menuQuery);
				treeVO.setNodes(nodes);
				treeList.add(treeVO);
			}
			
			tree = new Gson().toJson(treeList);
		}
		
		return tree;
	}
	
	private List<MenuTreeVO> getNodes(List<MenuEntity> list, MenuQuery menuQuery){
		List<MenuTreeVO> treeList = null;
		if(list != null && list.size() > 0){
			treeList = new LinkedList<MenuTreeVO>();
			MenuTreeVO treeVO = null;
			for (MenuEntity menuEntity : list) {
				treeVO = new MenuTreeVO();
				treeVO.setText(menuEntity.getMenuName());
				//treeVO.setId(menuEntity.getId().toString());
				Integer idInt = menuEntity.getId().intValue();
				String id = menuEntity.getId().toString();
				menuQuery = new MenuQuery();
				menuQuery.setParentMenuId(id);
				menuQuery = menuService.getMenuPage(menuQuery);
				List<MenuTreeVO> nodes = this.getNodes(menuQuery.getItems(), menuQuery);
				treeVO.setId(idInt);
				treeVO.setNodeId(idInt);
				treeVO.setNodes(nodes);
				
				treeList.add(treeVO);
			}
			
		}
		
		return treeList;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public void delete(String id, HttpServletRequest request){
		UserEntity user = UserUtils.getLoginUser(request);
		if(StringUtils.isNotBlank(id)){
			MenuEntity menu = menuService.getMenuById(Integer.parseInt(id));
			menu.setUpdateTime(new Date());
			menu.setUpdateUser(user.getUserName());
			menu.setIsDelete(1);
			menuService.updateMenu(menu);
		}
	}
	
	@RequestMapping(value = "/isSystem", method = RequestMethod.GET)
	@ResponseBody
	public Integer isSystem(Integer id, HttpServletRequest request){
		MenuEntity menu = menuService.getMenuById(id);
		Integer isSystem = 0;
		if(menu != null && MenuEnum.SYSTEM.getMenuType() == menu.getMenuType()){
			isSystem = 1;
		}
		
		return isSystem;
	}
	
	@RequestMapping(value = "/isSystemOrUrl", method = RequestMethod.GET)
	@ResponseBody
	public Integer isSystemOrUrl(Integer id, HttpServletRequest request){
		MenuEntity menu = menuService.getMenuById(id);
		Integer isSystemOrUrl = 0;
		if(menu != null){
			Integer menuType = menu.getMenuType();
			if(MenuEnum.SYSTEM.getMenuType() == menuType){
				isSystemOrUrl = 1;
			}else if(MenuEnum.URL.getMenuType() == menuType){
				isSystemOrUrl = 2;
			}
		}
		
		return isSystemOrUrl;
	}
	
	@RequestMapping(value = "/isExistMenu", method = RequestMethod.GET)
	@ResponseBody
	public Integer isExistMenu(Integer parentMenuId, String menuName, HttpServletRequest request){
		Integer isExist = 0;
		MenuEntity menu = null;
		if(parentMenuId != null){
			menu = menuService.getMenuById(parentMenuId);
			String systemId = menu.getBeyondOfSystemId();
			if(StringUtils.isBlank(systemId)){
				systemId = menu.getId().toString();
			}
			
			MenuEntity oldMenu = menuService.getMenuByNameAndSystemId(menuName, systemId);
			
			if(oldMenu != null){
				Integer menuType = oldMenu.getMenuType();
				if(MenuEnum.SYSTEM.getMenuType() == menuType || MenuEnum.URL.getMenuType() == menuType){
					isExist = 1;
				}
			}
		}else{
			menu = menuService.getMenuByNameAndType(menuName, MenuEnum.SYSTEM.getMenuType().toString());
			if(menu != null){
				isExist = 2;
			}
		}
		
		return isExist;
	}
}

