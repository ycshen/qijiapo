package com.qjp.interceptors;


import com.qjp.entity.UserEntity;
import com.qjp.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by shenyuchuan on 2017/3/28.
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(AdminInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        super.preHandle(request,response,o);
        HttpSession seesion = request.getSession();
        //response.sendRedirect("login");
        UserEntity loginUser = (UserEntity) seesion.getAttribute("loginUser");
        if(loginUser == null){
        	response.sendRedirect("/login");
			return false;
        	
        }

        String roleType = UserUtils.getRoleType(request);
        if(!"1".equals(roleType)){
            response.sendRedirect("/login");
            return false;
        }
        
        return true;

    }


}
