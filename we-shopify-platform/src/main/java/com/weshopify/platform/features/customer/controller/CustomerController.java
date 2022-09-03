package com.weshopify.platform.features.customer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping(value = {"/view-customer-board/{sortBy}"})
	public String viewCustomerDataBySorting(@PathVariable("sortBy") String SortBy,Model model)
	{
		List<CustomerBean>	allCustomers	=	customerService.getAllCustomersBySort(SortBy);
		
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
	
	
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
	@RequestMapping(value="/delete-customer/{id}",method = RequestMethod.GET)
	public String deleteCustomerByid(@PathVariable("id") String customerId)
	{
		log.info("Executing Controller Method");

		customerService.deleteCustomerById(Integer.parseInt(customerId));
		
		return "redirect:/view-customer-board";
	}
	
	/*
	 * it is used to check transaction concepts
	 * 
	@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED,timeout = 3,
			readOnly = true, rollbackFor = RuntimeException.class)
	@RequestMapping(value="/delete-customer/{id}",method = RequestMethod.GET)
	public String deleteCustomerByid(@PathVariable("id") String customerId)
	{
		log.info("Executing Controller Method");

		customerService.deleteCustomerById(Integer.parseInt(customerId));
		
		return "redirect:/view-customer-board";
	}
	*/
	
	@RequestMapping(value="/edit-customer/{id}",method = RequestMethod.GET)
	public String editCustomerByid(@PathVariable("id") String customerId, Model model)
	{
		CustomerBean customer	= customerService.getCustomerById(Integer.parseInt(customerId));
		model.addAttribute("customerInfo", customer);
		
		return "edit-customer";
	}
	
	@RequestMapping(value="/view-customerBoard/{currentPage}/{noOfRecPerPage}")
	public String viewCustomerDashboardWithPagination(@PathVariable("currentPage")String currentPage, @PathVariable("noOfRecPerPage") String noOfRecPerPage, Model model)
	{
		if(currentPage != null)
		{
			int pageNo	= Integer.parseInt(currentPage);
			
			List<CustomerBean> 	customerBean		=	customerService.getAllCustomers(Integer.parseInt(currentPage) -1,Integer.parseInt(noOfRecPerPage));
			List<Integer> 		noOfRequiredPage	=	new ArrayList<>();
			
			int totalRecord	=	customerService.getAllCustomers().size();	
			int noOfPages	=	totalRecord/Integer.parseInt(noOfRecPerPage);
			
			for (int i=1 ; i<= noOfPages; i++)
			{
				noOfRequiredPage.add(i);
			}
			
			if(totalRecord % Integer.parseInt(noOfRecPerPage) != 0 && Integer.parseInt(noOfRecPerPage) < totalRecord)
			{
				noOfRequiredPage.add(noOfRequiredPage.size() + 1);
			}
			
			if(totalRecord < Integer.parseInt(noOfRecPerPage) )
			{
				noOfRequiredPage.add(noOfRequiredPage.size() + 1);
			}
			
			if (pageNo == 1)
			{
				model.addAttribute("previousPage", pageNo);
			}
			else if(pageNo > 1)
			{
				model.addAttribute("previousPage", pageNo - 1);
			}
			
			if(pageNo < noOfRequiredPage.size())
			{
				model.addAttribute("nextPage", pageNo + 1);
			}
			else 
			{
				model.addAttribute("nextPage", pageNo);
			}
			
			model.addAttribute("noOfRecPerPage", noOfRecPerPage);
			model.addAttribute("noOfPages", noOfRequiredPage);
			model.addAttribute("allCustomers", customerBean);
		}
		
		return "customer-dashboard-paggination";
	}
	
	@RequestMapping(value="/search-customers")
	public String searchCustomers(@RequestParam("searchKey") String searchKey, @RequestParam("searchText") String searchText, Model model)
	{
		List<CustomerBean> 	customerBean	=	customerService.searchCustomers(searchKey, searchText);
		
		model.addAttribute("allCustomers", customerBean);

		return "customer-dashboard-paggination";
	}
}
