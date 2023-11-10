package com.flightbooking.services;

import java.util.List;

import com.flightbooking.dto.FlightDTO;

public interface IFlightService {

	public FlightDTO addFlight(FlightDTO flight);

	public FlightDTO viewByFlightId(Integer flightId);

	public List<FlightDTO> viewAllFlights();

	public List<FlightDTO> viewByFlightName(String flightName);

	public List<FlightDTO> viewByFlightSeatCapacity();

	public String updateFlight(Integer id, FlightDTO flight);

}
