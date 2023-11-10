package com.flightbooking.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightbooking.dto.PassengerDTO;
import com.flightbooking.entity.Passenger;
import com.flightbooking.entity.User;
import com.flightbooking.exception.UserNotFoundException;
import com.flightbooking.repository.PassengerRepository;
import com.flightbooking.repository.UserRepository;
import com.flightbooking.services.IPassengerService;

@Service
public class PassengerServiceImpl implements IPassengerService {

	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	public PassengerDTO addPassenger(Integer userId, PassengerDTO passengerDTO) {
		try {
			Passenger passenger = new Passenger();

			User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());

			passenger.setAge(passengerDTO.getAge());
			passenger.setPassengerName(passengerDTO.getPassengerName());
			passenger.setPassengerUIN(passengerDTO.getPassengerUIN());
			passenger.setUser(user);

			passengerRepository.save(passenger);

			return passengerDTO;
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("User Not Found" + e);
			return null;

		}
	}

	public List<PassengerDTO> viewAllPassenger() {

		List<Passenger> passengers = passengerRepository.findAll();

		List<PassengerDTO> passengerDTOs = passengers.stream()
				.map(source -> modelMapper.map(source, PassengerDTO.class)).collect(Collectors.toList());
		return passengerDTOs;
	}

	public PassengerDTO viewPassengerByUIN(Long uin) {
		PassengerDTO passengerDTO = new PassengerDTO();
		Passenger passenger = passengerRepository.findByPassengerUIN(uin);
		if (passenger != null) {

			passengerDTO = modelMapper.map(passenger, PassengerDTO.class);
			return passengerDTO;
		} else
			return null;

	}
}
