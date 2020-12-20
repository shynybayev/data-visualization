package com.aviation.rest.webservices.restfulwebservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.aviation.rest.webservices.restfulwebservices.model.Airport;

@SpringBootTest(classes = RestfulWebServicesApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class AirportControllerIntegrationTest {

	HttpHeaders headers = new HttpHeaders();

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testInsertAirport() {
		Airport airport = new Airport("+2", "XYZ", "XYZ", "DABA", "DZ", "2570559", "36.821392", "36.821392", "RIX",
				"United States", "267538824", "America/New_York");
		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("http://localhost:8080/airports",
				airport, String.class);
		assertEquals(201, responseEntity.getStatusCodeValue());
	}

	@Test
	public void testRetrieveAirport() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(("http://localhost:8080/airports/123"), HttpMethod.GET,
				entity, String.class);
		String expected = "{\r\n" + "    \"id\": 123,\r\n" + "    \"gmt\": \"5\",\r\n"
				+ "    \"iata_code\": \"XYZ\",\r\n" + "    \"city_iata_code\": \"XYZ\",\r\n"
				+ "    \"icao_code\": \"DABA\",\r\n" + "    \"country_iso2\": \"DZ\",\r\n"
				+ "    \"geoname_id\": \"2570559\",\r\n" + "    \"latitude\": \"36.821392\",\r\n"
				+ "    \"longitude\": \"36.821392\",\r\n" + "    \"airport_name\": \"RIX\",\r\n"
				+ "    \"country_name\": \"United States\",\r\n" + "    \"phone_number\": \"267538824\",\r\n"
				+ "    \"timezone\": \"America\\/New_York\"\r\n" + "}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

}