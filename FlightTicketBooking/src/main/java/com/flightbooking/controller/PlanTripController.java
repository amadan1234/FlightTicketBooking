package com.flightbooking.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightbooking.dto.PlanTripDTO;
import com.flightbooking.services.IPlanTripService;

@RestController
@RequestMapping("/plantrip")
public class PlanTripController {

	@Autowired
	IPlanTripService planTripService;

	@PostMapping("/addtrip")
	public String addTrip(@RequestParam("flight_id") Integer flight_id,
			@RequestParam("schedule_id") Integer schedule_id) {
		return planTripService.AddTrip(schedule_id, flight_id);
	}

	@GetMapping("/findBySAirportAndDAirport/{sname}/{dname}")
	public List<PlanTripDTO> findBySAirportAndDAirport(@PathVariable(value = "sname") String sname,
			@PathVariable(value = "dname") String dname) {
		return planTripService.viewBySourceDestination(sname, dname);
	}

	@GetMapping("/findByAll")
	public List<PlanTripDTO> findByAll() {
		return planTripService.ViewAllTrips();
	}

	@GetMapping("/findBySAirportAndDAirportAndDDate/{sname}/{dname}/{date}")
	public List<PlanTripDTO> findBySAirportAndDAirportDDate(@PathVariable(value = "sname") String sname,
			@PathVariable(value = "dname") String dname,
			@PathVariable(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return planTripService.viewBySourceDestinationAndDepartureDate(sname, dname, date);
	}

}
