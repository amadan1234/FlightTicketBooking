package com.flightbooking.dto;

import lombok.Data;

@Data
public class PlanTripDTO {

	private Integer tripId;

	private FlightDTO flight;
	private ScheduleDTO schedule;

}
