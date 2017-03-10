package com.qjp.controller;

import com.qjp.base.ResponseStatus;
import com.qjp.entity.AttnEntity;
import com.qjp.entity.LogEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.AttnService;
import com.qjp.service.LogService;
import com.qjp.service.UserService;
import com.qjp.util.JsonUtils;
import com.qjp.util.LogUtils;
import com.qjp.util.StringUtils;
import com.qjp.util.UserUtils;
import com.qjp.util.query.AttnQuery;
import com.qjp.util.query.LogQuery;
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
 * Created by fengyue on 2017/2/28.
 */

@Controller
@RequestMapping(value = "/inner/attn")
public class AttnController {
    @Autowired
    private AttnService attnService;
    @Autowired
    private DepartmentController departmentController;
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
    @RequestMapping(value = "/forwardEdit", method = RequestMethod.GET)
    public ModelAndView forwardEdit(String id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/attn/attn_edit");
        UserEntity user = UserUtils.getLoginUser(request);
        AttnEntity attnEntity = null;
        if(StringUtils.isNotBlank(id)){
            attnEntity = attnService.getAttnById(id);
        }

        mav.addObject("attn", attnEntity);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView detail(String id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/attn/attn_detail");
        UserEntity user = UserUtils.getLoginUser(request);
        mav.addObject("user", user);
        AttnEntity attn = attnService.getAttnById(id);
        mav.addObject("attn", attn);
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
            attnService.deleteAttnById(id);
            UserEntity user = UserUtils.getLoginUser(request);
            LogUtils.logCRMAttn("删除了竞争对手(" + name + ")", id, user);
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
            attnService.batchDeleteAttn(idList);
            UserEntity user = UserUtils.getLoginUser(request);
            for (String id : idList) {
                LogUtils.logCRMAttn("删除了竞争对手", id, user);
            }
            result = ResponseStatus.UPDATE_SUCCESS;
        }

        return result;
    }

    /**
     *
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ModelAndView saveOrUpdate(@ModelAttribute AttnEntity entity,HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/entity/entity_edit");
        entity.init(request); //初始化公司、部门、用户信息
        String provinceId = entity.getProvinceId();
        entity.setProvinceId(StringUtils.splitLocation(provinceId));
        String cityId = entity.getCityId();
        entity.setCityId(StringUtils.splitLocation(cityId));
        String areaId = entity.getAreaId();
        entity.setAreaId(StringUtils.splitLocation(areaId));
        Long id = entity.getId();
        UserEntity user = UserUtils.getLoginUser(request);
        if(id == null){
            String returnId = attnService.insertAttn(entity);
            LogUtils.logCRMAttn("添加了竞争对手(" + entity.getAttnName() + ")", returnId, user);
        }else{
            attnService.updateAttn(entity);
            LogUtils.logCRMAttn("修改了竞争对手(" + entity.getAttnName() + ")", id.toString(), user);
        }

        return mav;
    }
}
