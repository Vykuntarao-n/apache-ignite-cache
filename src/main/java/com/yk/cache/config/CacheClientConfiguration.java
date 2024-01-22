package com.yk.cache.config;

import com.yk.cache.entity.Customer;
import com.yk.cache.entity.Organization;
import com.yk.cache.entity.Person;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CacheClientConfiguration {

    private List<CacheConfiguration> cacheConfigurations = new ArrayList<>();
    @Bean
    public CacheConfiguration[] cacheConfiguration() {
        createCustomerCacheConfiguration();
        createOrganizationCacheConfiguration();
        createPersonCacheConfiguration();
        return cacheConfigurations.toArray(new CacheConfiguration[cacheConfigurations.size()]);
    }

    private void createCustomerCacheConfiguration(){
        CacheConfiguration<Long, Customer> customer = new CacheConfiguration<>("customer");
        customer.setCacheMode(CacheMode.REPLICATED);
        customer.setIndexedTypes(Long.class, Customer.class);
        //customer.setStatisticsEnabled(true);
        cacheConfigurations.add(customer) ;
    }

    private void createOrganizationCacheConfiguration(){
        CacheConfiguration<Long, Organization> orgCacheCfg = new CacheConfiguration<>("ORG_CACHE");
        orgCacheCfg.setCacheMode(CacheMode.REPLICATED); // Default.
        orgCacheCfg.setIndexedTypes(Long.class, Organization.class);
        cacheConfigurations.add(orgCacheCfg);
    }

    private void createPersonCacheConfiguration(){
        CacheConfiguration<Long, Person> person = new CacheConfiguration<>("PersonCache");
        person.setCacheMode(CacheMode.REPLICATED);
        person.setIndexedTypes(Long.class, Person.class);
        cacheConfigurations.add(person);
    }


}
