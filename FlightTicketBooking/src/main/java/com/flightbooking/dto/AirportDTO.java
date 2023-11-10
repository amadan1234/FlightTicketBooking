package com.flightbooking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AirportDTO {
	private Integer airportid;

	private String airportName;
	private String airportCity;
	private String airportCountry;

	public AirportDTO(Integer airportid, String airportName, String airportCity, String airportCountry) {
		super();
		this.airportid = airportid;
		this.airportName = airportName;
		this.airportCity = airportCity;
		this.airportCountry = airportCountry;
	}

}
