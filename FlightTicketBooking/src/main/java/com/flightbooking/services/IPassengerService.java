package com.flightbooking.services;


import java.util.List;

import com.flightbooking.dto.PassengerDTO;

public interface IPassengerService {
	public PassengerDTO addPassenger(Integer userId, PassengerDTO passenger);

	public List<PassengerDTO> viewAllPassenger();

	public PassengerDTO viewPassengerByUIN(Long uin);

}
