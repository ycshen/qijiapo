package com.qjp.controller;

import com.qjp.entity.MenuEntity;
import com.qjp.entity.UserEntity;
import com.qjp.service.CompanyService;
import com.qjp.service.MenuService;
import com.qjp.service.UserService;
import com.qjp.util.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
		String tip = this.validateUser(user);
		if(StringUtils.isBlank(tip)){
			userService.register(user);
		}else{
			mav.setViewName("/out/register_error");
			mav.addObject("tip", tip);
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/isExistTelephone", method = RequestMethod.GET)
	@ResponseBody
	public Integer isExistTelephone(String telephone, HttpServletRequest request){
		Integer isExist = 0;
		if(StringUtils.isNotBlank(telephone)){
			isExist = userService.isExistTelephone(telephone);
		}
		
		return isExist;
	}
	
	private String validateUser(UserEntity user){
		String tip = StringUtils.EMPTY;
		String companyName = user.getCompanyName();
		if(StringUtils.isBlank(companyName)){
			tip = "亲，公司名称不能为空哦~~~";
			return tip;
		}else if(companyName.length() > 20){
			tip = "亲，公司名称的长度不能超过20位哦~~~";
			return tip;
		}
		
		String userName = user.getUserName();
		if(StringUtils.isBlank(userName)){
			tip = "亲，姓名不能为空哦~~~";
			return tip;
		}else if(userName.length() > 15){
			tip = "亲，姓名的长度不能超过15位哦~~~";
			return tip;
		}
		
		String telephone = user.getTelphone();
		if(StringUtils.isBlank(telephone)){
			tip = "亲，手机号码不能为空哦~~~";
			return tip;
		}else if(telephone.length() != 11){
			tip = "亲，姓名的长度是11位哦~~~";
			return tip;
		}else if(!ValidateUtils.isTelephone(telephone)){
			tip = "亲，请输入正确的手机号码格式~~~";
			return tip;
		}else{
			Integer isExist = userService.isExistTelephone(telephone);
			if(isExist > 0){
				tip = "亲，该手机号码已经注册~~~";
				return tip;
			}
		}
		
		String email = user.getEmail();
		if(StringUtils.isBlank(email)){
			tip = "亲，电子邮箱不能为空哦~~~";
			return tip;
		}else{
			if(!ValidateUtils.isEmail(email)){
				tip = "亲，请输入正确的电子邮箱格式~~~";
				return tip;
			}
		}
		
		String password = user.getPassword();
		if(StringUtils.isBlank(password)){
			tip = "亲，密码不能为空哦~~~";
			return tip;
		}else{
			/*if(!ValidateUtils.isValidPass(password)){
				tip = "亲，密码的长度是由6-15位的数字、字母、特殊字符组成~~~";
				return tip;
			}*/
		}
		
		return tip;
	}

	public static void main(String[] args) {
		Pattern p = Pattern.compile("(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,15}");
		Matcher m = p.matcher("4496qAq@1");
		boolean isEmail = m.matches();
		System.out.println(isEmail);
	}
}
