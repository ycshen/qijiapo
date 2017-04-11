package com.qjp.controller.crm;

import com.qjp.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author yuchuanshen
 * @Date Created by 2017/4/11
 * @Desc qijiapo-com.qjp.controller.crm
 */
@Controller
@RequestMapping("/admin/checkAttendance")
public class CheckAttendanceController {
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
}
