package com.yk.cache.service;

import com.yk.cache.entity.Customer;
import com.yk.cache.exception.ResourceNotFoundException;
import com.yk.cache.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer getCustomerByID(long id) {
        log.debug("id" , id);
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }


    public Customer updateCustomer(long id, Customer customer) {
        return customerRepository.findById((id))
                .map(existingCustomer -> {
                    existingCustomer.setName(customer.getName());
                    existingCustomer.setIncome(customer.getIncome());
                    return customerRepository.save(id, existingCustomer);
                }).orElseGet(() -> customerRepository.save(new Random().nextLong(), customer));
    }


    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);
    }

    public Customer createCustomer(Customer customer) {
        long id = new Random().nextLong();
        return customerRepository.save(id, customer);
    }

    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
