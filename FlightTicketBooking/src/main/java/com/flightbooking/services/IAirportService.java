package com.flightbooking.services;

import java.util.List;

import com.flightbooking.dto.AirportDTO;

public interface IAirportService {

	public AirportDTO addAirportDetails(AirportDTO airport);

	public List<AirportDTO> viewAirports();

	public List<AirportDTO> viewByCountry(String country);

	public List<AirportDTO> viewByCity(String city);

	public AirportDTO viewByAirportName(String airportName);

	public String updateAirportDetails(int id, AirportDTO airportDTO);

}
