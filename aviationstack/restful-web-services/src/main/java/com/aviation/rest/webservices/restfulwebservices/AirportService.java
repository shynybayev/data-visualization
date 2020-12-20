package com.aviation.rest.webservices.restfulwebservices;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.aviation.rest.webservices.restfulwebservices.model.Airport;
import com.aviation.rest.webservices.restfulwebservices.model.Airports;

@Component
public class AirportService {
	public static final Logger LOG = LoggerFactory.getLogger(AirportService.class);
	
	int id = 0;
	private static final String API_AIRPORTS = "http://api.aviationstack.com/v1/airports?access_key=";
	private String apiKey = "fb493e5f72018ba2107e835d06001b18";

	public List<Airport> getAirports() {
		List<Airport> airports = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();

		try {

			final String uriAirports = API_AIRPORTS + apiKey;
			Airports airport = restTemplate.getForObject(uriAirports, Airports.class);
			airports.addAll(airport.getAirports());

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);

		}

		return airports;

	}

	public Airport findAirport(int id) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			Airports response = restTemplate.getForObject(API_AIRPORTS + apiKey, Airports.class);

			for (Airport airport : response.getAirports()) {
				
				if (airport.getId() == id) {

					return airport;
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);

		}
		LOG.warn("Airport could not be found");
		return null;

	}

	public Airport saveAirport(Airport airport) {
		
		RestTemplate restTemplate = new RestTemplate();
		airport.setId((++id));
		
		Airports response = restTemplate.getForObject(API_AIRPORTS + apiKey, Airports.class);

		try {
			response.addAirport(airport);
		} catch (Exception e) {
			LOG.error("Airport has not been added");
			LOG.error(e.getMessage(), e);
		}
		return airport;
	}
}
