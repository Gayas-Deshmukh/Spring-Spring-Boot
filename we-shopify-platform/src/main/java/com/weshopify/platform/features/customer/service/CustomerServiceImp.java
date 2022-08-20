package com.weshopify.platform.features.customer.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.weshopify.platform.features.customer.bean.CustomerBean;
import com.weshopify.platform.features.customer.domain.Customer;
import com.weshopify.platform.features.customer.repo.CustomerRepo;

//Service layer always accept bean & return Bean

@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public CustomerBean saveCustomer(CustomerBean customerBean) {
		Customer customerDomain	=	new Customer();
		
		/**
		 * Convert the bean to domain as per the repository 
		 * design, it will only accesspts the domains which are 
		 * etities.
		 */
		BeanUtils.copyProperties(customerBean, customerDomain);
		
		customerRepo.save(customerDomain);
		
		BeanUtils.copyProperties(customerDomain, customerBean);

		return customerBean;
	}

	@Override
	public CustomerBean updateCustomer(CustomerBean customerBean) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerBean> getAllCustomers() 
	{
		 Iterator<Customer> 	customers		=	customerRepo.findAll().iterator();
		 List<CustomerBean>  	listCustomers	=	new ArrayList<>();
		 
		 while(customers.hasNext())
		 {
			 Customer 		customerDomain	=	customers.next();
			 CustomerBean	customerBean	=	new CustomerBean();
			 
			 BeanUtils.copyProperties(customerDomain, customerBean);
			 listCustomers.add(customerBean);
		 }
		
		 return listCustomers;
	}

	@Override
	public List<CustomerBean> getAllCustomers(int noOfRecPerPage) 
	{
		int currentPage = 0;
		
		PageRequest	pageRequest	=	PageRequest.of(currentPage, noOfRecPerPage);
		
		Iterator<Customer> 		customers		=	customerRepo.findAll(pageRequest).iterator();
		List<CustomerBean>  	listCustomers	=	new ArrayList<>();
		 
		 while(customers.hasNext())
		 {
			 Customer 		customerDomain	=	customers.next();
			 CustomerBean	customerBean	=	new CustomerBean();
			 
			 BeanUtils.copyProperties(customerDomain, customerBean);
			 listCustomers.add(customerBean);
		 }
		
		 return listCustomers;
	}

	@Override
	public void deleteCustomerById(int id) 
	{
		customerRepo.deleteById(id);
	}

	@Override
	public List<CustomerBean> deleteCustomer(CustomerBean customerBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerBean getCustomerById(int id) 
	{
		Customer		customerDomain	=	customerRepo.findById(id).get();
		CustomerBean	customerBean	=	new CustomerBean();
		
		BeanUtils.copyProperties(customerDomain, customerBean);

		return customerBean;
	}

}
