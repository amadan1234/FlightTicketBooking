package com.flightbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightbooking.dto.PassengerDTO;
import com.flightbooking.services.IPassengerService;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

	@Autowired
	IPassengerService passengerService;

	@PostMapping("/registerUser")
	public PassengerDTO addUser(@RequestParam("user_id") Integer user_id, @RequestBody PassengerDTO passengerDTO) {
		return passengerService.addPassenger(user_id, passengerDTO);
	}

	@GetMapping("/findByUIN/{uin}")
	public PassengerDTO findByUIN(@PathVariable(value = "uin") Long uin) {
		return passengerService.viewPassengerByUIN(uin);
	}

	@GetMapping("/allPassengers")
	public List<PassengerDTO> readAllPassengers() {
		return passengerService.viewAllPassenger();
	}

}
