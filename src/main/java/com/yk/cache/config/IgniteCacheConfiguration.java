package com.yk.cache.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.springdata22.repository.config.EnableIgniteRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableIgniteRepositories(value = "com.yk.cache.*")
public class IgniteCacheConfiguration {

    @Autowired IgniteConfiguration igniteConfiguration;

    @Bean
    public Ignite igniteInstance() {
        return Ignition.start(igniteConfiguration);
    }


}
