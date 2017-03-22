package com.qjp.controller;

import com.qjp.base.ResponseStatus;
import com.qjp.base.RoleEnum;
import com.qjp.entity.LogEntity;
import com.qjp.entity.ActivityEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.*;
import com.qjp.util.JsonUtils;
import com.qjp.util.LogUtils;
import com.qjp.util.StringUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.LogQuery;
import com.qjp.util.query.ActivityQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by fengyue on 2017/3/22.
 */
@Controller
@RequestMapping("/inner/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private LogService logService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ModelAndView list(@ModelAttribute ActivityQuery activityQuery, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/activity/activity_list");
        mav.addObject("activityQuery", activityQuery);
        return mav;
    }


    @RequestMapping(value = "/listAjax", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String listAjax(@ModelAttribute ActivityQuery activityQuery, HttpServletRequest request){
        activityQuery.init(request);
        String roleType = activityQuery.getRoleType();
        if(RoleEnum.DEP.getRoleId().toString().equals(roleType)){
            String idList = departmentService.getSubDepList(activityQuery.getDepartmentId(), activityQuery.getCompanyId());
            activityQuery.setDepartmentId(idList);
        }

        activityQuery = activityService.getActivityPages(activityQuery);
        String jsonStr = JsonUtils.json2Str(activityQuery);

        return jsonStr;
    }
    @RequestMapping(value = "/forwardEdit", method = RequestMethod.GET)
    public ModelAndView forwardEdit(String id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/activity/activity_edit");
        UserEntity user = UserUtils.getLoginUser(request);
        ActivityEntity activity = null;
        if(StringUtils.isNotBlank(id)){
            activity = activityService.getActivityById(id);
        }

        mav.addObject("activity", activity);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView detail(String id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/activity/activity_detail");
        UserEntity user = UserUtils.getLoginUser(request);
        mav.addObject("user", user);
        ActivityEntity activity = activityService.getActivityById(id);
        mav.addObject("activity", activity);
        LogQuery logQuery = new LogQuery();
        logQuery.setCasecadeId(id);
        logQuery.setLogType("2");
        logQuery.setSize(30);
        logQuery.setCompanyId(user.getCompanyId().toString());
        logQuery = logService.getLogPage(logQuery);
        List<LogEntity> logList = logQuery.getItems();
        mav.addObject("logList", logList);

        return mav;
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    @ResponseBody
    public Integer deleteById(String id, String name,HttpServletRequest request){
        Integer result = ResponseStatus.INIT;
        if(StringUtils.isNotBlank(id)){
            activityService.deleteActivityById(id);
            UserEntity user = UserUtils.getLoginUser(request);
            LogUtils.logCRMActivity("删除了产品(" + name + ")", id, user);
            result = ResponseStatus.UPDATE_SUCCESS;
        }

        return result;
    }

    @RequestMapping(value = "/transferActivity", method = RequestMethod.GET)
    @ResponseBody
    public Integer transferActivity(String userId, String activityId, String transferType, HttpServletRequest request){
        Integer result = ResponseStatus.INIT;
        if(StringUtils.isNotBlank(userId)){
            UserEntity transferToUser = userService.getUserById(userId);
            UserEntity loginUser = UserUtils.getLoginUser(request);
            if("1".equals(transferType)){
                this.transferActivity(activityId, transferToUser, loginUser);
            }else if("2".equals(transferType)){
                String[] idArr = activityId.split("\\,");
                for (String id : idArr) {
                    this.transferActivity(id, transferToUser, loginUser);
                }
            }

            result = ResponseStatus.UPDATE_SUCCESS;

        }

        return result;
    }

    private void transferActivity(String activityId, UserEntity transferToUser, UserEntity loginUser){
        ActivityEntity oldActivity = activityService.getActivityById(activityId);
        oldActivity.setUserId(transferToUser.getId().toString());
        String transferUserName = transferToUser.getUserName();
        oldActivity.setUserName(transferUserName);
        oldActivity.setUpdateTime(new Date());
        oldActivity.setUpdateUser(loginUser.getUserName());
        LogUtils.logCRMActivity("转移产品(" + oldActivity.getActivityName() + ")到" + transferUserName, activityId, loginUser);
    }

    @RequestMapping(value = "/batchDeleteById", method = RequestMethod.GET)
    @ResponseBody
    public Integer batchDeleteById(String ids, HttpServletRequest request){
        Integer result = ResponseStatus.INIT;
        if(StringUtils.isNotBlank(ids)){
            String[] idArr = ids.split("\\,");
            List<String> idList =  Arrays.asList(idArr);
            activityService.batchDeleteActivity(idList);
            UserEntity user = UserUtils.getLoginUser(request);
            for (String id : idList) {
                LogUtils.logCRMActivity("删除了产品", id, user);
            }
            result = ResponseStatus.UPDATE_SUCCESS;
        }

        return result;
    }

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@ModelAttribute ActivityEntity activity, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/activity/activity_edit");
        activity.init(request); //初始化公司、部门、用户信息
        String provinceId = activity.getProvinceId();
        activity.setProvinceId(StringUtils.splitLocation(provinceId));
        String cityId = activity.getCityId();
        activity.setCityId(StringUtils.splitLocation(cityId));
        String areaId = activity.getAreaId();
        activity.setAreaId(StringUtils.splitLocation(areaId));
        Long id = activity.getId();
        UserEntity user = UserUtils.getLoginUser(request);
        if(id == null){
            String returnId = activityService.insertActivity(activity);
            LogUtils.logCRMActivity("添加了产品(" + activity.getActivityName() + ")", returnId, user);
        }else{
            activityService.updateActivity(activity);
            LogUtils.logCRMActivity("修改了产品(" + activity.getActivityName() + ")", id.toString(), user);
        }

        return mav;
    }

}
