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
import com.qjp.entity.CompanyEntity;
import com.qjp.entity.ConfigEntity;
import com.qjp.entity.MemoEntity;
import com.qjp.entity.MenuEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.ConfigService;
import com.qjp.service.MemoService;
import com.qjp.service.MenuService;
import com.qjp.util.DateUtils;
import com.qjp.util.LogUtils;
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
		UserEntity user = UserUtils.getLoginUser(request);
		List<MemoEntity> todayMemos = memoService.getTodayMemo(user.getId().toString());
		mav.addObject("todayMemos", todayMemos);
		/*List<MemoEntity> weekMemos = memoService.getWeekMemo(user.getId().toString());
		mav.addObject("weekMemos", weekMemos);*/
		List<MemoEntity> monthMemos = memoService.getMonthMemo(user.getId().toString());
		if(monthMemos != null && monthMemos.size() > 0){
			String eventsStr  = "";
			for (MemoEntity memo : monthMemos) {
				eventsStr += this.getEventsStr(memo) + ",";
			}

			if(StringUtils.isNotBlank(eventsStr)){
				eventsStr = eventsStr.substring(0, eventsStr.length() - 1);
			}
 
			mav.addObject("eventsStr", eventsStr);
		}
		
		return mav;
	}
	
	private String getEventsStr(MemoEntity memo){
		String format ="yyyy-MM-dd HH:mm:ss";
		String str = "{";
        str +="title: '" + memo.getMemoName() + "',";
        str +=" start: '" + DateUtils.getDateStr(memo.getMemoStartTime(), format) + "',";
        str +=" end: '" + DateUtils.getDateStr(memo.getMemoEndTime(), format) + "',";
        str +=" allDay: false,";
        Date startDate = memo.getMemoStartTime();
        String startTime = DateUtils.transDateToString(startDate, "yyyy-MM-dd");
        String today =DateUtils.getCurrentDate("yyyy-MM-dd");
        if(today.equals(startTime)){
            str +=" backgroundColor: \"#f56954\",";
            str +="borderColor: \"#f56954\" ";
        }else{
            str +=" backgroundColor: \"#0073b7\",";
            str +="borderColor: \"#0073b7\" ";
        }
	    str +="}";
		return str;
	}
	
	@RequestMapping(value = "/addMemo", method = RequestMethod.GET)
	public ModelAndView addMemo(@ModelAttribute MemoEntity mmemo, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/memo/memo_edit");
		List<ConfigEntity> configList = configService.getConfigListByCode(Config.MEMOTYPE);
		mav.addObject("configList", configList);
		return mav;
	}
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Integer saveOrUpdate(@ModelAttribute MemoEntity memo, HttpServletRequest request){
		Integer result = 0;
		try{
			Long id = memo.getId();
			UserEntity user = UserUtils.getLoginUser(request);
			if(id == null){
				memo.setCompanyId(user.getCompanyId().toString());
				memo.setCreateTime(new Date());
				memo.setCreateUser(user.getUserName());
				memo.setIsDelete(0);
				memo.setUserId(user.getId());
				memoService.insertMemo(memo);
				result = 1;
			}else{
				/*CompanyEntity oldCompany = companyService.getCompanyById(id);
				String oldCompanyStr = oldCompany.toLogString();
				oldCompany.setUpdateTime(new Date());
				oldCompany.setCompanyAddress(company.getCompanyAddress());
				oldCompany.setCompanyCeo(company.getCompanyCeo());
				oldCompany.setCompanyName(company.getCompanyName());
				oldCompany.setCompanySite(company.getCompanySite());
				oldCompany.setCompanyTelephone(company.getCompanyTelephone());
				oldCompany.setUpdateUser(user.getUserName());
				companyService.updateCompany(oldCompany);*/
				result = 2;
			}
		}catch(Exception e){
			//暂时不记录监控
			e.printStackTrace();
			result = 0;
		}
		
		return result;
	}
}

