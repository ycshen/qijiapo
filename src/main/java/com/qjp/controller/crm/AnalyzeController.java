package com.qjp.controller.crm;

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
@RequestMapping("/inner/analyze")
public class AnalyzeController {
    @RequestMapping(value = "/checkingAttendance", method = RequestMethod.GET)
    public ModelAndView checkingAttendance(){
        ModelAndView mav = new ModelAndView("/analyze/checkingAttendance_list");
        return mav;
    }
}
