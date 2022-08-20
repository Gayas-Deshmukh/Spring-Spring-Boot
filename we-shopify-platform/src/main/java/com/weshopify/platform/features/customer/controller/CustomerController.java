package com.weshopify.platform.features.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.weshopify.platform.features.customer.bean.CustomerBean;
import com.weshopify.platform.features.customer.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CustomerController 
{
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/view-customer-board")
	public String viewCustomerdashBoard(Model model)
	{
		List<CustomerBean>	allCustomers	=	customerService.getAllCustomers();
		
		model.addAttribute("allCustomers", allCustomers);
		
		return "customer-dashboard";
	}
	
	@RequestMapping("/add-customer-view")
	public String addCustomerViewPage(Model model)
	{
		model.addAttribute("customerFormBean", new CustomerBean());
		return "add-customer";
	}
	
	@RequestMapping(value = "/save-customer",method = RequestMethod.POST)
	public String addCustomer(@ModelAttribute("customerFormBean")CustomerBean cutomerBean , Model model)
	{
		log.info(cutomerBean.toString());
		
		if(cutomerBean.getIsSelfReg() != null && Boolean.valueOf(cutomerBean.getIsSelfReg()))
		{
			customerService.saveCustomer(cutomerBean);
			
			if(cutomerBean != null && cutomerBean.getId() > 0)
			{
				String isSelfReg = "true";
				model.addAttribute("regMessage", isSelfReg);
				
				return "customer-self-reg";
			}
		}
		
		customerService.saveCustomer(cutomerBean);
	
		return "redirect:/view-customer-board";
	}
	
	@RequestMapping(value="/delete-customer/{id}",method = RequestMethod.GET)
	public String deleteCustomerByid(@PathVariable("id") String customerId)
	{
		customerService.deleteCustomerById(Integer.parseInt(customerId));
		
		return "redirect:/view-customer-board";
	}
	
	@RequestMapping(value="/edit-customer/{id}",method = RequestMethod.GET)
	public String editCustomerByid(@PathVariable("id") String customerId, Model model)
	{
		CustomerBean customer	= customerService.getCustomerById(Integer.parseInt(customerId));
		model.addAttribute("customerInfo", customer);
		
		return "edit-customer";
	}
	
	@RequestMapping(value="/view-customerBoard/{noOfRecPerPage}")
	public String viewCustomerDashboardWithPagination(@PathVariable("noOfRecPerPage") String noOfRecPerPage, Model model)
	{
		List<CustomerBean> customerBean	=	customerService.getAllCustomers(Integer.parseInt(noOfRecPerPage));
		
		model.addAttribute("currentPage", 0);
		model.addAttribute("noOfRecPerPage", noOfRecPerPage);
		
		int totalRecord	=	customerBean.size();	
		int noOfPages	=	totalRecord/Integer.parseInt(noOfRecPerPage);
		
		model.addAttribute("noOfPages", noOfPages);
		model.addAttribute("allCustomers", customerBean);
		
		return "customer-dashboard";
	}
}
