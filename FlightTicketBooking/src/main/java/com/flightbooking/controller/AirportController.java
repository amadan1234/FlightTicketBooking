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

import com.flightbooking.dto.AirportDTO;
import com.flightbooking.services.IAirportService;

@RestController
@RequestMapping("/airport")
public class AirportController {

	@Autowired
	IAirportService airportService;

	@PostMapping("/addAirport")
	public AirportDTO addAirport(@RequestBody AirportDTO airportDTO) {
		return airportService.addAirportDetails(airportDTO);
	}

	@GetMapping("/allAirports")
	public List<AirportDTO> readAllAirports() {
		return airportService.viewAirports();
	}

	@GetMapping("/findByCountry/{country}")
	public List<AirportDTO> findByCountry(@PathVariable(value = "country") String country) {
		return airportService.viewByCountry(country);
	}

	@GetMapping("/findByCity/{city}")
	public List<AirportDTO> findByCity(@PathVariable(value = "city") String city) {
		return airportService.viewByCity(city);
	}

	@GetMapping("/findByName/{name}")
	public AirportDTO findByName(@PathVariable(value = "name") String name) {
		return airportService.viewByAirportName(name);
	}

	@PutMapping("/updateAirport/{no}")
	public String updateAirport(@PathVariable(value = "no") int no, @RequestBody AirportDTO airportDTO) {

		return airportService.updateAirportDetails(no, airportDTO);
	}

}
