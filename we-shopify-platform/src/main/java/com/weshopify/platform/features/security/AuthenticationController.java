package com.weshopify.platform.features.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController 
{
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String authenticate(HttpServletRequest request)
	{
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		System.out.println("UserName :" + userName + ":" + "Password : "+ password);
		boolean isLogin = StringUtils.hasText(userName)  && StringUtils.hasText(password);
		
		System.out.println("Does Login is Successfull? " + isLogin);
		
		if (isLogin)
		{
			return "dashbord";
		}
		
		return "login";
	};
}
