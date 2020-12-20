package com.aviation.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aviation.rest.webservices.restfulwebservices.AirportService;
import com.aviation.rest.webservices.restfulwebservices.model.Airport;

@RestController
public class AirportController {
	public static final Logger LOG = LoggerFactory.getLogger(AirportService.class);

	AirportService airportService = new AirportService();

	@Autowired
	private JdbcTemplate jtm;

	public List<Airport> findAllAirports() {

		String sql = "SELECT * FROM TBL_AIRPORT";

		return jtm.query(sql, new BeanPropertyRowMapper<>(Airport.class));
	}

	public Airport findAirportById(int id) {

		String sql = "SELECT * FROM TBL_AIRPORT WHERE id = ?";

		return jtm.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<>(Airport.class));
	}

	public void insertAirport(Airport airport) {

		String sql = "INSERT INTO TBL_AIRPORT (id, gmt, iata_code, city_iata_code, icao_code, country_iso2, geoname_id, latitude, longitude, airport_name, country_name, phone_number, timezone) VALUES (?, ?, ?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] { airport.getId(), airport.getGmt(), airport.getIata_code(),
				airport.getCity_iata_code(), airport.getIata_code(), airport.getCountry_iso2(), airport.getGeoname_id(),
				airport.getLatitude(), airport.getLongitude(), airport.getAirport_name(), airport.getCountry_name(),
				airport.getPhone_number(), airport.getTimezone() };

		jtm.update(sql, params);
	}

	@GetMapping(path = "/")
	public String Airports() {

		return "Hello! To get airoports, please go to http://localhost:8080/airports";
	}

	@Cacheable("airports")
	@GetMapping(path = "/airports")
	public ResponseEntity<Object> getAirports() {
		List<Airport> allAirports = airportService.getAirports();
		if (allAirports.isEmpty()) {

			return new ResponseEntity<>("Nothing found.", HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(allAirports, HttpStatus.OK);
	}

	@RequestMapping(value = "/airports/{id}", method = RequestMethod.GET)
	public Airport retrieveAirportById(@PathVariable int id) {
		return findAirportById(id);
	}

	@PostMapping(value = "/airports", consumes = "application/json")
	public ResponseEntity<Object> createAirport(@RequestBody Airport airport) {
		Airport savedAirport = airportService.saveAirport(airport);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedAirport.getId()).toUri();
		insertAirport(airport);
		LOG.info("Airport will be inserted: " + airport.toString());
		return ResponseEntity.created(location).build();

	}
}
