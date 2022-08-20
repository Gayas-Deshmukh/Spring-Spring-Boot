package com.weshopify.platform.features.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j // used for maintaining logs
public class HomeController 
{
	@RequestMapping(value={"","/","/home"})
	public String viewHomePage()
	{
		//Log.
		return "login";
	}
}
