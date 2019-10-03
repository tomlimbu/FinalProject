package com.webage.spring.project.customer.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import com.webage.spring.project.customer.api.customer;
//mport com.webage.spring.project.customer.api.CustomerResponse;
import com.webage.spring.project.customer.domain.Customer;
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
	
	 // Boolean findByName( String name );
	  Boolean existsByName(String name);
	  Boolean existsById(long id);
	  Boolean existsByPassword(String password);
	  public Customer findByName(String name); 
	 
	   		
	 

}
