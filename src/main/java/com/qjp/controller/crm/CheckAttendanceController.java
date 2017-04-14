package com.qjp.controller.crm;

import com.qjp.base.ResponseStatus;
import com.qjp.base.RoleEnum;
import com.qjp.entity.LogEntity;
import com.qjp.entity.UserEntity;
import com.qjp.entity.WorkAttendancePlaceEntity;
import com.qjp.service.DepartmentService;
import com.qjp.service.LogService;
import com.qjp.service.WorkAttendancePlaceService;
import com.qjp.util.JsonUtils;
import com.qjp.util.LogUtils;
import com.qjp.util.StringUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.LogQuery;
import com.qjp.util.query.WorkAttendancePlaceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @Author yuchuanshen
 * @Date Created by 2017/4/11
 * @Desc qijiapo-com.qjp.controller.crm
 */
@Controller
@RequestMapping("/admin/checkAttendance")
public class CheckAttendanceController {

    @Autowired
    private WorkAttendancePlaceService workAttendancePlaceService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private LogService logService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView checkingAttendance(){
        ModelAndView mav = new ModelAndView("/admin/office/checkingattendance_rule_list");
        return mav;
    }

    /**
     * 跳转到新增或者编辑页面
     * @param id 考勤点id
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView checkingAttendance(String id){
        ModelAndView mav = new ModelAndView("/admin/office/checkingattendance_rule_edit");
        if(StringUtils.isNotBlank(id)){
            //编辑，加载数据
        }

        return mav;
    }
    @RequestMapping(value = "/addWorkAttendancePlace")
    public ModelAndView addWorkAttendancePlace(String saleOppoId, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/workAttendancePlace/add_workAttendancePlace");
        mav.addObject("saleOppoId", saleOppoId);
        return mav;
    }

    @RequestMapping(value = "/selectWorkAttendancePlace", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ModelAndView selectWorkAttendancePlace(@ModelAttribute WorkAttendancePlaceQuery workAttendancePlaceQuery, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/workAttendancePlace/select_workAttendancePlace_list");
        mav.addObject("workAttendancePlaceQuery", workAttendancePlaceQuery);
        return mav;
    }


    @RequestMapping(value = "/listAjax", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String listAjax(@ModelAttribute WorkAttendancePlaceQuery workAttendancePlaceQuery, HttpServletRequest request){
        workAttendancePlaceQuery.init(request);
        String roleType = workAttendancePlaceQuery.getRoleType();
        if(RoleEnum.DEP.getRoleId().toString().equals(roleType)){
            String idList = departmentService.getSubDepList(workAttendancePlaceQuery.getDepartmentId(), workAttendancePlaceQuery.getCompanyId());
            workAttendancePlaceQuery.setDepartmentId(idList);
        }

        workAttendancePlaceQuery = workAttendancePlaceService.getWorkAttendancePlacePage(workAttendancePlaceQuery);
        String jsonStr = JsonUtils.json2Str(workAttendancePlaceQuery);

        return jsonStr;
    }
    @RequestMapping(value = "/forwardEdit", method = RequestMethod.GET)
    public ModelAndView forwardEdit(String id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/admin/office/workAttendancePlace_edit");
        UserEntity user = UserUtils.getLoginUser(request);
        WorkAttendancePlaceEntity workAttendancePlace = null;
        if(StringUtils.isNotBlank(id)){
            workAttendancePlace = workAttendancePlaceService.getWorkAttendancePlaceById(id);
        }

        mav.addObject("workAttendancePlace", workAttendancePlace);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView detail(String id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/admin/office/workAttendancePlace_detail");
        UserEntity user = UserUtils.getLoginUser(request);
        mav.addObject("user", user);
        WorkAttendancePlaceEntity workAttendancePlace = workAttendancePlaceService.getWorkAttendancePlaceById(id);
        mav.addObject("workAttendancePlace", workAttendancePlace);
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
            workAttendancePlaceService.deleteWorkAttendancePlaceById(id);
            UserEntity user = UserUtils.getLoginUser(request);
            LogUtils.logCRMWorkAttendancePlace("删除了考勤点(" + name + ")", id, user);
            result = ResponseStatus.UPDATE_SUCCESS;
        }

        return result;
    }

    @RequestMapping(value = "/batchDeleteById", method = RequestMethod.GET)
    @ResponseBody
    public Integer batchDeleteById(String ids, HttpServletRequest request){
        Integer result = ResponseStatus.INIT;
        if(StringUtils.isNotBlank(ids)){
            String[] idArr = ids.split("\\,");
            List<String> idList =  Arrays.asList(idArr);
            workAttendancePlaceService.batchDeleteWorkAttendancePlace(idList);
            UserEntity user = UserUtils.getLoginUser(request);
            for (String id : idList) {
                LogUtils.logCRMWorkAttendancePlace("删除了考勤点", id, user);
            }
            result = ResponseStatus.UPDATE_SUCCESS;
        }

        return result;
    }

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@ModelAttribute WorkAttendancePlaceEntity workAttendancePlace, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/admin/office/checkingattendance_rule_edit");
        workAttendancePlace.init(request); //初始化公司、部门、用户信息
        Long id = workAttendancePlace.getId();
        UserEntity user = UserUtils.getLoginUser(request);
        if(id == null){
            String returnId = workAttendancePlaceService.insertWorkAttendancePlace(workAttendancePlace);
            LogUtils.logCRMWorkAttendancePlace("添加了考勤点(" + workAttendancePlace.getPlaceName() + ")", returnId, user);
        }else{
            workAttendancePlaceService.updateWorkAttendancePlace(workAttendancePlace);
            LogUtils.logCRMWorkAttendancePlace("修改了考勤点(" + workAttendancePlace.getPlaceName() + ")", id.toString(), user);
        }

        return mav;
    }

    @RequestMapping(value = "/enableOrDisableById", method = RequestMethod.GET)
    @ResponseBody
    public Integer enableOrDisableById(String id, String name,HttpServletRequest request){
        Integer result = ResponseStatus.INIT;
        if(StringUtils.isNotBlank(id)){
            workAttendancePlaceService.enableOrDisableWorkAttendancePlaceById(id);
            UserEntity user = UserUtils.getLoginUser(request);
            LogUtils.logCRMWorkAttendancePlace("修改了考勤点(" + name + ")启用状态", id, user);
            result = ResponseStatus.UPDATE_SUCCESS;
        }

        return result;
    }
}
