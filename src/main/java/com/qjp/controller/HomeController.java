package com.qjp.controller;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qjp.entity.MenuEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.MenuService;
import com.qjp.service.UserService;


@Controller
@RequestMapping("/")
public class HomeController {

	private final static Logger LOG = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
/*	@RequestMapping("/")
	public String main() {
		return "main";
	}*/

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		UserEntity loginUser = null;
		ModelAndView mv = new ModelAndView("out/login");
		if (isEmpty(account, password) || !"POST".equalsIgnoreCase(request.getMethod())) {
			return mv;
		}else{
			loginUser = userService.login(account, password);
			if(loginUser != null){
				HttpSession seesion = request.getSession();
				seesion.setAttribute("loginUser",loginUser);
				Integer menuDefineType = loginUser.getMenuDefinedType();
				if(menuDefineType == null){
					menuDefineType = 4;
				}
				
				String definedType = menuDefineType.toString();
				String companyId = loginUser.getCompanyId().toString();
				List<MenuEntity> list = null;
				String definedCasecaseId = StringUtils.EMPTY;
				//menuDefineType  1-员工  2-职位  3-部门  4-角色
				switch (menuDefineType) {
					case 1:
						definedCasecaseId = loginUser.getId().toString();
						break;
					case 2:
						definedCasecaseId = loginUser.getPositionId().toString();					
						break;
					case 3:
						definedCasecaseId = loginUser.getDepartmentId().toString();
						break;
					case 4:
						//待开发
						definedCasecaseId = "1";
						break;
					default:
						definedCasecaseId = "1";
						//默认按照角色
						break;
				}

				list = menuService.getLoginMenus(definedType, companyId, definedCasecaseId);
				seesion.setAttribute("menuList",list);
			}else{
				mv.addObject("msg", "用户名或者密码有误");
				return mv;
			}
		}
		
		mv.setViewName("redirect:home");
		try {
			// 设置记住密码
			if ("true".equals(request.getParameter("rememberMe"))) {
				Cookie accountC = new Cookie("account", URLEncoder.encode(account, "UTF-8"));
				Cookie passwordC = new Cookie("password", password);

				accountC.setPath("/");
				passwordC.setPath("/");

				accountC.setMaxAge(7 * 24 * 60 * 60);
				passwordC.setMaxAge(7 * 24 * 60 * 60);

				response.addCookie(accountC);
				response.addCookie(passwordC);
			}
		} catch (Exception e) {
			
		} 
		
		return mv;
	}

	private boolean isEmpty(String account, String password) {
		return StringUtils.isEmpty(account) || StringUtils.isEmpty(password);
	}

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie c : cookies) {
				if ("account".equals(c.getName())) {
					c.setMaxAge(0);
					response.addCookie(c);
				}
				if ("password".equals(c.getName())) {
					c.setMaxAge(0);
					response.addCookie(c);
				}
			}
		}

        HttpSession seesion = request.getSession();
		UserEntity loginUser = (UserEntity) seesion.getAttribute("loginUser");
        if(loginUser != null){
        	seesion.removeAttribute("loginUser");
        }

		return new ModelAndView("redirect:login");
	}

	@RequestMapping("/list_demo")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/gateway/system/list");
		return mv;
	}
	
	@RequestMapping("/home")
	public ModelAndView home(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/home");
		HttpSession seesion = request.getSession();
		UserEntity loginUser = (UserEntity) seesion.getAttribute("loginUser");
		mav.addObject("loginUser", loginUser); //不想直接取
		
		return mav;
	}
	
	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/register");
		return mav;
	}


}
