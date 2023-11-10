package com.flightbooking.services;

import java.time.LocalDate;
import java.util.List;

import com.flightbooking.dto.PlanTripDTO;

public interface IPlanTripService {
	public String AddTrip(Integer schedule_id, Integer flight_id);

	public List<PlanTripDTO> viewBySourceDestination(String source, String destination);

	public List<PlanTripDTO> viewBySourceDestinationAndDepartureDate(String source, String destination,
			LocalDate departureDate);
	public List<PlanTripDTO> ViewAllTrips();

}
