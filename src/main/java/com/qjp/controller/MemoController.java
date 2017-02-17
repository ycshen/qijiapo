package com.qjp.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.qjp.service.MemoService;
import com.qjp.service.MenuService;
import com.qjp.util.UserUtils;
import com.qjp.util.query.MenuQuery;
import com.qjp.util.vo.BTreeVO;
import com.google.gson.Gson;

/** 
 * <p>Project: qijiapo</p> 
 * <p>Title: MemoController.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2016 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
@Controller
@RequestMapping("/inner/memo")
public class MemoController extends BaseController{
	@Autowired
	private MemoService memoService;
	@Autowired
	private ConfigService configService;
	@RequestMapping(value = "/memo", method = RequestMethod.GET)
	public ModelAndView memoIndex(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/memo/memo");
		
		return mav;
	}
	
	@RequestMapping(value = "/addMemo", method = RequestMethod.GET)
	public ModelAndView addMemo(@ModelAttribute MenuEntity menu, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/memo/memo_edit");
		List<ConfigEntity> configList = configService.getConfigListByCode(Config.MEMOTYPE);
		mav.addObject("configList", configList);
		
		return mav;
	}
	
}

