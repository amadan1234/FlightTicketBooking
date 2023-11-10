package com.flightbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightbooking.entity.Airport;
@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer>{

	public List<Airport> findByAirportCity(String cityName);
	public List<Airport> findByAirportCountry(String countryName);
	public Airport findByAirportName(String Name);
}
