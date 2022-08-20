package com.weshopify.platform.features.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerSelfRegController 
{
	@RequestMapping(value = "/customer-self-reg")
	public String viewCustomerSelfRegForm() 
	{
		return "customer-self-reg";
	}
}
