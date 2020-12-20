package com.aviation.rest.webservices.restfulwebservices.model;

public class StringBlob {
	int id;
	String flight;

	@Override
	public String toString() {
		return "StringBlob [id=" + id + ", flight=" + flight + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlight() {
		return flight;
	}

	public void setFlight(String flight) {
		this.flight = flight;
	}
}
