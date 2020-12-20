package com.aviation.rest.webservices.restfulwebservices.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.aviation.rest.webservices.restfulwebservices.exceptions.FlightNotFoundException;
import com.aviation.rest.webservices.restfulwebservices.model.Flight;
import com.aviation.rest.webservices.restfulwebservices.model.FlightList;

@Component
public class FlightsDaoService {
	int number=111000;
	public static final Logger LOG = LoggerFactory.getLogger(FlightsDaoService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${api.key}")
	private String apiKey;
	private FlightList response;


	public FlightList getAllFlights(){
		response = restTemplate.getForObject("http://api.aviationstack.com/v1/flights"+"?access_key="+apiKey, FlightList.class);
		if(response == null)
			throw new FlightNotFoundException("Flights were not found");
		return response;
		}

	public Flight findFlight(int number) {
		response = restTemplate.getForObject("http://api.aviationstack.com/v1/flights"+"?access_key="+apiKey, FlightList.class );

		for(Flight flight : response.getFlightList()) {
			if (Integer.parseInt(flight.getNestedFlight().getNumber())==number) {
				LOG.info("Flight with number: "+number+" has been found.");
				return flight;
			}
		}
		throw new FlightNotFoundException("Flight with number: "+number+" could not be found");
	}

	public Flight saveFlight(Flight flight) {
		flight.getNestedFlight().setNumber(Integer.toString(++number));
		response = restTemplate.getForObject(
				"http://api.aviationstack.com/v1/flights"+"?access_key="+apiKey, FlightList.class );

		try {
			response.addFlight(flight);
		}catch (Exception e) {
			LOG.error("Fligth has not been added");
		}
		return flight;
	}

}
