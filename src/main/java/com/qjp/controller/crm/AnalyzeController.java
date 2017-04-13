package com.qjp.controller.crm;

import com.qjp.base.RoleEnum;
import com.qjp.entity.LogEntity;
import com.qjp.entity.WorkAttendanceEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.DepartmentService;
import com.qjp.service.LogService;
import com.qjp.service.WorkAttendanceService;
import com.qjp.util.JsonUtils;
import com.qjp.util.LogUtils;
import com.qjp.util.StringUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.LogQuery;
import com.qjp.util.query.WorkAttendanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author yuchuanshen
 * @Date Created by 2017/4/11
 * @Desc qijiapo-com.qjp.controller.crm
 */
@Controller
@RequestMapping("/inner/analyze")
public class AnalyzeController {
    @Autowired
    private WorkAttendanceService workAttendanceService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private LogService logService;

    @RequestMapping(value = "/checkingAttendance", method = RequestMethod.GET)
    public ModelAndView checkingAttendance() {
        ModelAndView mav = new ModelAndView("/analyze/checkingAttendance_list");
        return mav;
    }

    @RequestMapping(value = "/listAjax", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String listAjax(@ModelAttribute WorkAttendanceQuery workAttendanceQuery, HttpServletRequest request) {
        workAttendanceQuery.init(request);
        String roleType = workAttendanceQuery.getRoleType();
        if (RoleEnum.DEP.getRoleId().toString().equals(roleType)) {
            String idList = departmentService.getSubDepList(workAttendanceQuery.getDepartmentId(), workAttendanceQuery.getCompanyId());
            workAttendanceQuery.setDepartmentId(idList);
        }

        workAttendanceQuery = workAttendanceService.getWorkAttendancePage(workAttendanceQuery);
        String jsonStr = JsonUtils.json2Str(workAttendanceQuery);

        return jsonStr;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute WorkAttendanceEntity workAttendance, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/analyze/workAttendance_edit");
        workAttendance.init(request); //初始化公司、部门、用户信息
        Long id = workAttendance.getId();
        UserEntity user = UserUtils.getLoginUser(request);
        String returnId = workAttendanceService.insertWorkAttendance(workAttendance);
        LogUtils.logCRMWorkAttendance("添加了考勤记录(" + workAttendance.getUserName() + ")", returnId, user);

        return mav;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView detail(String id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/analyze/workAttendance_detail");
        UserEntity user = UserUtils.getLoginUser(request);
        mav.addObject("user", user);
        WorkAttendanceEntity workAttendance = workAttendanceService.getWorkAttendanceById(id);
        mav.addObject("workAttendance", workAttendance);
        LogQuery logQuery = new LogQuery();
        logQuery.setCasecadeId(id);
        logQuery.setLogType("2");
        logQuery.setCompanyId(user.getCompanyId().toString());
        logQuery = logService.getLogPage(logQuery);
        List<LogEntity> logList = logQuery.getItems();
        mav.addObject("logList", logList);

        return mav;
    }
}
