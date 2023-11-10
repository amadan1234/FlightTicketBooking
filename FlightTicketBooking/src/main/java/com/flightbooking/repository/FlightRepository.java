package com.flightbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flightbooking.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

	public List<Flight> findByFlightName(String flightName);

	@Query(value = "select * from flighttbl f order by f.seat_capacity ASC", nativeQuery = true)
	public List<Flight> viewByCapacity();
}
