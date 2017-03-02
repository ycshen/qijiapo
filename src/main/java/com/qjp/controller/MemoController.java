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
import com.qjp.util.JsonUtils;
import com.qjp.util.LogUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.MenuQuery;
import com.qjp.util.vo.BTreeVO;
import com.qjp.util.vo.MemoCanlendarVO;
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
		mav.addObject("events", this.getEvents("", "", request));
		return mav;
	}
	
	@RequestMapping(value = "/getEvents", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getEvents(String startTime, String endTime, HttpServletRequest request){
		String eventsStr  = "";
		String format = "yyyy-MM";
		if(StringUtils.isBlank(startTime)){
			startTime = DateUtils.getCurrentDate(format)  + "-01 00:00:00";
		}else{
			startTime += " 00:00:00";
		}
		
		if(StringUtils.isBlank(endTime)){
			endTime = DateUtils.getDateStr(DateUtils.addMonths(DateUtils.str2date(startTime, "yyyy-MM-dd HH:mm:ss"), 1), "yyyy-MM-dd HH:mm:ss");
		}else{
			endTime += " 24:00:00";
		}
		
		
		UserEntity user = UserUtils.getLoginUser(request);
		List<MemoEntity> monthMemos = memoService.getMonthMemo(startTime, endTime, user.getId().toString());
		if(monthMemos != null && monthMemos.size() > 0){
			/*for (MemoEntity memo : monthMemos) {
				eventsStr += this.getEventsStr(memo) + ",";
			}

			if(StringUtils.isNotBlank(eventsStr)){
				eventsStr = eventsStr.substring(0, eventsStr.length() - 1);
			}*/
			
			eventsStr = this.getEventsJsonStr(monthMemos);
		}
		
		return eventsStr;
	}
	
	
	private String getEventsJsonStr(List<MemoEntity> monthMemos){
		List<MemoCanlendarVO> mcList = new LinkedList<MemoCanlendarVO>();
		if(monthMemos != null && monthMemos.size() > 0){
			String[] colors = {"#f56954", "#0073b7", "#eb9316", "#ff9f89", "#00CED1"};
			MemoCanlendarVO mc = null;
			String format ="yyyy-MM-dd HH:mm:ss";
			for (MemoEntity memo : monthMemos) {
				mc = new MemoCanlendarVO();
				java.util.Random random=new java.util.Random();
				int index = random.nextInt(5);
				mc.setAllDay(false);
				mc.setBackgroundColor(colors[index]);
				mc.setBorderColor(colors[index]);
				mc.setEnd(DateUtils.getDateStr(memo.getMemoEndTime(), format));
				mc.setStart(DateUtils.getDateStr(memo.getMemoStartTime(), format));
				mc.setTitle(memo.getMemoName());
				mcList.add(mc);
			}
		}
		
		return JsonUtils.json2Str(mcList);
	}
	
	private String getEventsStr(MemoEntity memo){
		String[] colors = {"#f56954", "#0073b7", "#257e4a", "#ff9f89", "#00CED1"};
		String format ="yyyy-MM-dd HH:mm:ss";
		String str = "{";
        str +="title: \"" + memo.getMemoName() + "\",";
        str +=" start: \"" + DateUtils.getDateStr(memo.getMemoStartTime(), format) + "\",";
        str +=" end: \"" + DateUtils.getDateStr(memo.getMemoEndTime(), format) + "\",";
        str +=" allDay: false,";
        // 定义随机类

		java.util.Random random=new java.util.Random();
		int index = random.nextInt(5);
        str +=" backgroundColor: \"" + colors[index] + "\",";
        str +="borderColor: \"" + colors[index] + "\" ";
        
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
	public String saveOrUpdate(@ModelAttribute MemoEntity memo, HttpServletRequest request){
		String result = "0";
		try{
			Long id = memo.getId();
			UserEntity user = UserUtils.getLoginUser(request);
			if(id == null){
				memo.setCompanyId(user.getCompanyId().toString());
				memo.setCreateTime(new Date());
				memo.setCreateUser(user.getUserName());
				memo.setIsDelete(0);
				memo.setUserId(user.getId());
				String idStr = memoService.insertMemo(memo);
				result = 1 + "_" + idStr;
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
				result = 2 + "_" + id;
			}
		}catch(Exception e){
			//暂时不记录监控
			e.printStackTrace();
		}
		
		return result;
	}
}

