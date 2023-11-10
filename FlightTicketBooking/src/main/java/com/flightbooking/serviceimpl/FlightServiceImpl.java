package com.flightbooking.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightbooking.dto.FlightDTO;
import com.flightbooking.entity.Flight;
import com.flightbooking.exception.FlightNotFoundException;
import com.flightbooking.repository.FlightRepository;
import com.flightbooking.repository.ScheduleRepository;
import com.flightbooking.services.IFlightService;

@Service
public class FlightServiceImpl implements IFlightService {

	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	FlightRepository flightRepository;
	@Autowired
	private ModelMapper modelMapper;

	public FlightDTO addFlight(FlightDTO flightDTO) {

		Flight flight = new Flight();
		flight.setFlightName(flightDTO.getFlightName());
		flight.setSeatCapacity(flightDTO.getSeatCapacity());
		flight.setFare(flightDTO.getFare());
		
		flightRepository.save(flight);

		return flightDTO;
	}

	public FlightDTO viewByFlightId(Integer flightId) {
		try {
			Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new FlightNotFoundException());
			FlightDTO flightDTO = new FlightDTO();

			flightDTO = modelMapper.map(flight, FlightDTO.class);
			return flightDTO;
		} catch (FlightNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return null;

		}

	}

	public List<FlightDTO> viewAllFlights() {

		List<Flight> flights = flightRepository.findAll();
		List<FlightDTO> flightDTOs = new ArrayList<FlightDTO>();
		if (flights != null) {
			flightDTOs = flights.stream().map(source -> modelMapper.map(source, FlightDTO.class))
					.collect(Collectors.toList());

			return flightDTOs;
		} else
			return null;
	}

	public List<FlightDTO> viewByFlightName(String flightName) {
		List<Flight> flights = flightRepository.findByFlightName(flightName);
		List<FlightDTO> flightDTOs = flights.stream().map(source -> modelMapper.map(source, FlightDTO.class))
				.collect(Collectors.toList());

		return flightDTOs;

	}

	public List<FlightDTO> viewByFlightSeatCapacity() {
		List<Flight> flights = flightRepository.viewByCapacity();
		List<FlightDTO> flightDTOs = flights.stream().map(source -> modelMapper.map(source, FlightDTO.class))
				.collect(Collectors.toList());

		return flightDTOs;
	}

	public String updateFlight(Integer id, FlightDTO flightDTO) {

		Flight flight;
		try {
			flight = flightRepository.findById(id).orElseThrow(() -> new FlightNotFoundException());

			if (flightDTO.getFare() != null)
				flight.setFare(flightDTO.getFare());

			if (flightDTO.getFlightName() != null)
				flight.setFlightName(flightDTO.getFlightName());

			if (flightDTO.getSeatCapacity() != null)
				flight.setSeatCapacity(flightDTO.getSeatCapacity());

			flightRepository.save(flight);
		} catch (FlightNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return "Flight data not updated";

		}

		return "Flight Updated Successfully";

	}
}
