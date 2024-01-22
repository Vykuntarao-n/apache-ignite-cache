package com.yk.cache.controller;

import com.yk.cache.entity.Customer;
import com.yk.cache.entity.Person;
import com.yk.cache.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonsController {
    @Autowired
    PersonService personService;

    @GetMapping
    public Iterable<Person> getPersonss(){
        return  personService.getCustomers();
    }
}
