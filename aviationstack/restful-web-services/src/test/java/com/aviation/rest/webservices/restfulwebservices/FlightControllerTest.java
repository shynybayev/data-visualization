package com.aviation.rest.webservices.restfulwebservices;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.aviation.rest.webservices.restfulwebservices.controller.FlightsResource;
import com.aviation.rest.webservices.restfulwebservices.exceptions.FlightNotFoundException;
import com.aviation.rest.webservices.restfulwebservices.model.Aircraft;
import com.aviation.rest.webservices.restfulwebservices.model.Airline;
import com.aviation.rest.webservices.restfulwebservices.model.DepartureArrival;
import com.aviation.rest.webservices.restfulwebservices.model.Flight;
import com.aviation.rest.webservices.restfulwebservices.model.FlightList;
import com.aviation.rest.webservices.restfulwebservices.model.NestedFlight;
import com.aviation.rest.webservices.restfulwebservices.service.FlightsDaoService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FlightControllerTest {
	@Autowired
	private FlightsDaoService service;
	@Mock
	private FlightsDaoService mockService;
	@Mock
	private FlightsResource controller; 
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	private Flight flight1;
	private Flight flight2;
	@Mock
	private FlightList flightList;
	
	private final Date flight_date1 =  Date.valueOf("2020-12-12");
	private final Date flight_date2 =  Date.valueOf("2020-11-20");
	private final String flight_status = "scheduled";
	private final DepartureArrival departure = null;
	private final DepartureArrival arrival = null;
	private final Airline airline = null;
	private final Aircraft aircraft = null;
	private final NestedFlight nesFlight1 = new NestedFlight();
	private final NestedFlight nesFlight2 = new NestedFlight();
	
	
	@Before
	public void setup() {
		flight1 = new Flight();
		flight2 = new Flight();

		nesFlight1.setCodeshared(null);
		nesFlight1.setNumber("447");
		nesFlight1.setIata("IE323");
		nesFlight1.setIcao("SOL323");
		nesFlight2.setCodeshared(null);
		nesFlight2.setNumber("475");
		nesFlight2.setIata("VA1835");
		nesFlight2.setIcao("VOZ1835");
		
		flight1.setFlight_date(flight_date1);
		flight1.setFlight_status(flight_status);
		flight1.setDeparture(departure);
		flight1.setArrival(arrival);
		flight1.setAirline(airline);
		flight1.setAircraft(aircraft);
		flight1.setNestedFlight(nesFlight1);
		
		flight2.setFlight_date(flight_date2);
		flight2.setFlight_status(flight_status);
		flight2.setDeparture(departure);
		flight2.setArrival(arrival);
		flight2.setAirline(airline);
		flight2.setAircraft(aircraft);
		flight2.setNestedFlight(nesFlight2);
		
		flightList.addFlight(flight1);
		flightList.addFlight(flight2);
		
	}
	
	@Test
	public void testFindAllPositive() {

		FlightsResource controller = mock(FlightsResource.class);
		//given
		when(controller.retrieveAllFlights()).thenReturn(flightList);	
		
		//when
		
	    FlightList foundFlights = service.getAllFlights();
	    
	    //then
	    assertNotNull(foundFlights);
       
	}

	@Test
	public void testFindFligtByNumberPositive() {
		when(controller.retrieveFlightByNumber(Integer.parseInt(flight1.getNestedFlight().getNumber()))).thenReturn(flight1);
		Flight found = controller.retrieveFlightByNumber(Integer.parseInt(flight1.getNestedFlight().getNumber()));
	
		assertNotNull(found);
		assertEquals(flight1.getFlight_date(), found.getFlight_date());
	}

	
}
