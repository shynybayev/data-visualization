package com.aviation.rest.webservices.restfulwebservices.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Airport {
	private int id;
	private String gmt;
	@NotNull
	@Size(min = 3, max = 3, message = "Wrong lenght.")
	private String iata_code;
	@NotNull
	@Size(min = 3, max = 3, message = "Wrong lenght.")
	private String city_iata_code;
	@NotNull
	@Size(min = 4, max = 4, message = "Wrong lenght.")
	private String icao_code;
	private String country_iso2;
	private String geoname_id;
	private String latitude;
	private String longitude;
	private String airport_name;
	private String country_name;
	private String phone_number;
	private String timezone;

	public Airport() {
	}

	public Airport(

			String gmt, String iata_code, String city_iata_code, String icao_code, String country_iso2,
			String geoname_id, String latitude, String longitude, String airport_name, String country_name,
			String phone_number, String timezone) {
		this.gmt = gmt;
		this.iata_code = iata_code;
		this.city_iata_code = city_iata_code;
		this.icao_code = icao_code;
		this.country_iso2 = country_iso2;
		this.geoname_id = geoname_id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.airport_name = airport_name;
		this.country_name = country_name;
		this.phone_number = phone_number;
		this.timezone = timezone;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGmt() {
		return gmt;
	}

	public void setGmt(String gmt) {
		this.gmt = gmt;
	}

	public String getIata_code() {
		return iata_code;
	}

	public void setIata_code(String iata_code) {
		this.iata_code = iata_code;
	}

	public String getCity_iata_code() {
		return city_iata_code;
	}

	public void setCity_iata_code(String city_iata_code) {
		this.city_iata_code = city_iata_code;
	}

	public String getIcao_code() {
		return icao_code;
	}

	public void setIcao_code(String icao_code) {
		this.icao_code = icao_code;
	}

	public String getCountry_iso2() {
		return country_iso2;
	}

	public void setCountry_iso2(String country_iso2) {
		this.country_iso2 = country_iso2;
	}

	public String getGeoname_id() {
		return geoname_id;
	}

	public void setGeoname_id(String geoname_id) {
		this.geoname_id = geoname_id;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getAirport_name() {
		return airport_name;
	}

	public void setAirport_name(String airport_name) {
		this.airport_name = airport_name;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	@Override
	public String toString() {
		return "Airport [id=" + id + ", gmt=" + gmt + ", iata_code=" + iata_code + ", city_iata_code=" + city_iata_code
				+ ", icao_code=" + icao_code + ", country_iso2=" + country_iso2 + ", geoname_id=" + geoname_id
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", airport_name=" + airport_name
				+ ", country_name=" + country_name + ", phone_number=" + phone_number + ", timezone=" + timezone + "]";
	}

}