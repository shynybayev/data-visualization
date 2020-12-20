package com.aviation.rest.webservices.restfulwebservices;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CachingConfig {
 
    @Bean
    public CacheManager cacheManagerFlights() {
    	
    	SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
          new ConcurrentMapCache("flights"), 
          new ConcurrentMapCache("airports")));
        return cacheManager;
    }
    

}
