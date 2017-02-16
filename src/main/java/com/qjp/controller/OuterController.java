package com.qjp.controller;

import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.qjp.base.UserStatus;
import com.qjp.entity.CompanyEntity;
import com.qjp.entity.MenuEntity;
import com.qjp.entity.ResultEnum;
import com.qjp.entity.UserEntity;
import com.qjp.model.ResultModel;
import com.qjp.service.CompanyService;
import com.qjp.service.MenuService;
import com.qjp.service.UserService;


@Controller
@RequestMapping("/out")
public class OuterController {

	private final static Logger LOG = LoggerFactory.getLogger(OuterController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private CompanyService companyService;
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
				Long userId = loginUser.getId();
				List<MenuEntity> list = menuService.getMenuList(userId.toString());
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
		ModelAndView mav = new ModelAndView("/out/register");
		return mav;
	}

	@RequestMapping("/clause")
	public ModelAndView clause(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/out/clause");
		return mav;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute UserEntity user, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/out/register_success");
		mav.addObject("user", user);
		boolean isOk = false;
		if(isOk){
			userService.register(user);
		}else{
			mav.setViewName("/out/register_error");
		}
		
		return mav;
	}

}
