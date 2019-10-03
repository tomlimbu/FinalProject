package com.webage.spring.project.customer.api;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.webage.spring.project.customer.domain.Customer;
import com.webage.spring.project.customer.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerDatabaseAPI {
	@Autowired
	CustomerRepository repo;
	
	
	  @GetMapping
	  public Iterable<Customer> getAll() { 
		  return repo.findAll(); }
	 
	
	@GetMapping("/{customerId}")    //
	public Optional<Customer> getCustomerById(@PathVariable("customerId") long id) {
		return repo.findById(id);
	}
	@PostMapping //create customer
	public ResponseEntity<?> addCustomer(@RequestBody Customer newCustomer, UriComponentsBuilder uri) {
		if(newCustomer.getId()!=0
			|| newCustomer.getName()==null
			|| newCustomer.getEmail()==null
			|| newCustomer.getPassword()==null) {
			return ResponseEntity.badRequest().build();
		}
		newCustomer=repo.save(newCustomer);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCustomer.getId()).toUri();
		ResponseEntity<?> response=ResponseEntity.created(location).build();
		return response;
		}	
	
//	@GetMapping ("/{customer}")
//	public Customer customer(@PathVariable("customer") String name){
//       
//        Boolean exists; 
//        exists=repo.existsByName(name);
//        if(exists==true) {
//         Customer foundCustomer = repo.findByName(name);
//         return foundCustomer; 
//        }else {
//        	return null;
//        }
//        
//	}
	@GetMapping ("/token/{customer}")
	public Customer customerVerify(@PathVariable("customer") String name){
       
        Boolean exists; 
        exists=repo.existsByName(name);
        if(exists==true) {
         Customer foundCustomer = repo.findByName(name);
         return foundCustomer; 
        }else {
        	return null;
        }
        
	}


	@PutMapping("/{customerId}") //update
	public ResponseEntity<?> putCustomer(@RequestBody Customer newCustomer, @PathVariable("customerId") long customerId) {
		if(newCustomer.getId()!=customerId||newCustomer.getName()==null
				|| newCustomer.getEmail()==null
				|| newCustomer.getPassword()==null
				) {
			return ResponseEntity.badRequest().build();
		}
		newCustomer=repo.save(newCustomer);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{customerId}")    //Delete
	public void deleteCustomer(@PathVariable("customerId") long id) {
		repo.deleteById(id);	
	}
	
	
	
	 
	
	
	
}