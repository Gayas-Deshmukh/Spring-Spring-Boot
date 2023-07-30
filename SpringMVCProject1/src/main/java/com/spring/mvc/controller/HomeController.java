package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController 
{
	@GetMapping("/home")
	public String getHome()
	{
		System.out.println("I am in Home");
		return "home";
	}
	
	@GetMapping("/details")
	public String getDetails()
	{
		return "details";
	}
}
