package com.aviation.rest.webservices.restfulwebservices.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Airports {
	
	@JsonProperty("data")
	List<Airport> data;

	public Airports() {
	}

	public List<Airport> getAirports() {
		return data;
	}

	public List<Airport> getData() {
		return data;
	}

	public void setData(List<Airport> data) {
		this.data = data;
	}
	
	public void addAirport(Airport airport) {
		data.add(airport);
	}

}
