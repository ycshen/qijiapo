package com.qjp.controller;

import com.qjp.service.AttnService;
import com.qjp.service.LogService;
import com.qjp.service.UserService;
import com.qjp.util.JsonUtils;
import com.qjp.util.StringUtils;
import com.qjp.util.query.AttnQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fengyue on 2017/2/28.
 */

@Controller
@RequestMapping(value = "/inner/attn")
public class AttnController {
    @Autowired
    private AttnService attnService;
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;

    /**
     * 页面跳转控制
     * @param attnQuery
     * @param request
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ModelAndView list(@ModelAttribute AttnQuery attnQuery, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("/attn/attn_list");
        modelAndView.addObject("attnQuery",modelAndView);
        return modelAndView;
    }

    /**
     *
     * @param attnQuery
     * @param request
     * @return
     */
    @RequestMapping(value = "/listAjax", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String listAjax(@ModelAttribute AttnQuery attnQuery,HttpServletRequest request){
        attnQuery.init(request);
        String provinceId = attnQuery.getProvinceId();
        attnQuery.setProvinceId(StringUtils.splitLocation(provinceId));
        String cityId = attnQuery.getCityId();
        attnQuery.setCityId(StringUtils.splitLocation(cityId));
        String areaId = attnQuery.getAreaId();
        attnQuery.setAreaId(StringUtils.splitLocation(areaId));
        attnQuery = attnService.getAttnPage(attnQuery);
        attnQuery.setSize(65);
        String jsonStr = JsonUtils.json2Str(attnQuery);

        return jsonStr;
    }
}
