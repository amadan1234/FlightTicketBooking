package com.flightbooking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FlightDTO {
	private Integer flightId;
	private String flightName;
	private Integer seatCapacity;
	private Double fare;

	public FlightDTO(Integer flightId, String flightName, Integer seatCapacity, Double fare) {
		super();
		this.flightId = flightId;
		this.flightName = flightName;
		this.seatCapacity = seatCapacity;
		this.fare = fare;
	}

}
