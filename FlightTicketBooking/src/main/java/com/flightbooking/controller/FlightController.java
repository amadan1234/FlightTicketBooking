package com.flightbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightbooking.dto.FlightDTO;
import com.flightbooking.services.IFlightService;

@RestController
@RequestMapping("/flight")
public class FlightController {

	@Autowired
	IFlightService flightService;

	@PostMapping("/addFlight")
	public FlightDTO addFlight(@RequestBody FlightDTO flightDTO) {
		return flightService.addFlight(flightDTO);
	}

	@GetMapping("/findByName/{name}")
	public List<FlightDTO> findByName(@PathVariable(value = "name") String name) {
		return flightService.viewByFlightName(name);
	}

	@GetMapping("/findById/{id}")
	public FlightDTO findByName(@PathVariable(value = "id") Integer id) {
		return flightService.viewByFlightId(id);
	}

	@GetMapping("/allFlights")
	public List<FlightDTO> readAllFlights() {
		return flightService.viewAllFlights();
	}

	@GetMapping("/allFlightsBySeatCapacity")
	public List<FlightDTO> readAllFlightsBySeatCapacity() {
		return flightService.viewByFlightSeatCapacity();
	}

	@PutMapping("/updateFlight/{no}")
	public String updateFlight(@PathVariable(value = "no") int no, @RequestBody FlightDTO flightDTO) {

		return flightService.updateFlight(no, flightDTO);
	}
}
