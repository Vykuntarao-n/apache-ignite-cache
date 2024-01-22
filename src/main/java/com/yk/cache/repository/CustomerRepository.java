package com.yk.cache.repository;

import com.yk.cache.entity.Customer;
import org.apache.ignite.springdata22.repository.IgniteRepository;
import org.apache.ignite.springdata22.repository.config.RepositoryConfig;

@RepositoryConfig(cacheName = "customer")
public interface CustomerRepository extends IgniteRepository<Customer, Long> {


}
