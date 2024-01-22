package com.yk.cache.controller;

import com.yk.cache.entity.Customer;
import com.yk.cache.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/customers")
public class CustomersController {
    @Autowired CustomerService customerService ;
    @GetMapping
    public Iterable<Customer> getCustomers(){
        return  customerService.getCustomers();
    }

}
