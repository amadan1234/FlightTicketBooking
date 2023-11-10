package com.flightbooking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightbooking.entity.Airport;
import com.flightbooking.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
	public List<Schedule> findBySourceAirportAndDestinationAirport(Airport s_Airport, Airport d_Airport);

	public List<Schedule> findBySourceAirportAndDestinationAirportAndDepartureDate(Airport s_Airport, Airport d_Airport,
			LocalDate dDate);
}
