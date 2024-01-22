package com.yk.cache.controller;

import com.yk.cache.entity.Customer;
import com.yk.cache.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/{id}")
    public Customer findCustomerById(@PathVariable Long id) {
        log.debug("Search by ID  : " + id);
        return customerService.getCustomerByID(id);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        log.debug("Creating customer" , customer.toString());
        return customerService.createCustomer(customer);
    }


    @PutMapping(value = "/{id}")
    public Customer updateCustomerById(@PathVariable Long id,@RequestBody Customer customer)
    {
        log.debug("Updating by ID  : " + id);
        return customerService.updateCustomer(id,customer);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Long id)
    {
       log.debug("Delete Customer by ID  : " + id);
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
