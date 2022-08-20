package com.weshopify.platform.features.customer.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.weshopify.platform.features.customer.domain.Customer;

//public interface CustomerRepo extends CrudRepository<Customer, Integer>
public interface CustomerRepo extends PagingAndSortingRepository<Customer, Integer>
{

}


// PagingAndSortingRepository is a subInterface of CrudRepository