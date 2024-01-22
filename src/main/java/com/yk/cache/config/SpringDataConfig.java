package com.yk.cache.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.cache.spring.SpringCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableCaching
@Slf4j
public class SpringDataConfig {
    @Bean
    public CacheManager cacheManager() {
        SpringCacheManager cacheManager = new SpringCacheManager();
        cacheManager.setIgniteInstanceName("apacheIgniteInstance");
        return cacheManager;
    }
}
