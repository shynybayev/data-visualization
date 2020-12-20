package com.aviation.rest.webservices.restfulwebservices;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aviation.rest.webservices.restfulwebservices.service.FlightsDaoService;



@Component
public class Scheduler {
	
	private static final Logger LOG = LoggerFactory.getLogger(Scheduler.class);
	
	@Autowired
	private FlightsDaoService service;
	@CacheEvict(allEntries=true, value = "flights")
	@Scheduled(cron="${cronjob.schedule}")
	public void updateCache(){
	    service.getAllFlights();
	    LOG.info("Cron job has been performed");
	}

}
