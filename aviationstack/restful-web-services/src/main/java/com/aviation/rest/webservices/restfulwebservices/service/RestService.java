package com.aviation.rest.webservices.restfulwebservices.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aviation.rest.webservices.restfulwebservices.model.Airport;

@Service
public class RestService {

    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public Airport getPostsPlainJSON() {
        
    	String url = "http://api.aviationstack.com/v1/airports?access_key=64f0403188f63f4b9f52d3489cb6c955";
    	return this.restTemplate.getForObject(url, Airport.class);
    }
    
    public String getPostsPlainJSONS() {
        
    	String url = "http://api.aviationstack.com/v1/airports?access_key=64f0403188f63f4b9f52d3489cb6c955";
    	return this.restTemplate.getForObject(url, String.class);
    }
}