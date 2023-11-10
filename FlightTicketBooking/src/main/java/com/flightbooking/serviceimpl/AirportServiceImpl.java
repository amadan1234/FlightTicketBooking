package com.flightbooking.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightbooking.dto.AirportDTO;
import com.flightbooking.entity.Airport;
import com.flightbooking.exception.AirportNotFoundException;
import com.flightbooking.repository.AirportRepository;
import com.flightbooking.services.IAirportService;

@Service
public class AirportServiceImpl implements IAirportService {

	@Autowired
	AirportRepository airportRepository;
	@Autowired
	private ModelMapper modelMapper;

	public AirportDTO addAirportDetails(AirportDTO airportDTO) {
		Airport airport = new Airport();
		airport.setAirportCity(airportDTO.getAirportCity());
		airport.setAirportCountry(airportDTO.getAirportCountry());
		airport.setAirportName(airportDTO.getAirportName());

		airportRepository.save(airport);

		return airportDTO;

	}

	public List<AirportDTO> viewAirports() {
		List<Airport> airports = airportRepository.findAll();
		List<AirportDTO> airportDTOs = airports.stream().map(source -> modelMapper.map(source, AirportDTO.class))
				.collect(Collectors.toList());
		return airportDTOs;

	}

	public List<AirportDTO> viewByCountry(String country) {
		List<Airport> airports = airportRepository.findByAirportCountry(country);

		List<AirportDTO> airportDTOs = airports.stream().map(source -> modelMapper.map(source, AirportDTO.class))
				.collect(Collectors.toList());
		return airportDTOs;
	}

	public List<AirportDTO> viewByCity(String city) {

		List<Airport> airports = airportRepository.findByAirportCity(city);
		List<AirportDTO> airportDTOs = airports.stream().map(source -> modelMapper.map(source, AirportDTO.class))
				.collect(Collectors.toList());
		return airportDTOs;

	}

	public AirportDTO viewByAirportName(String airportName) {
		Airport airport = airportRepository.findByAirportName(airportName);

		AirportDTO airportDTO = new AirportDTO();
		if (airport != null) {

			airportDTO = modelMapper.map(airport, AirportDTO.class);
			return airportDTO;
		} else
			return null;

	}

	public String updateAirportDetails(int id, AirportDTO airportDTO) {

		Airport airport;
		try {
			airport = airportRepository.findById(id).orElseThrow(() -> new AirportNotFoundException());

			if (airportDTO.getAirportCity() != null)
				airport.setAirportCity(airportDTO.getAirportCity());

			if (airportDTO.getAirportCountry() != null)
				airport.setAirportCountry(airportDTO.getAirportCountry());

			if (airportDTO.getAirportName() != null)
				airport.setAirportName(airportDTO.getAirportName());

			airportRepository.save(airport);
		} catch (AirportNotFoundException e) {
			System.out.println(e);
			return "Airport data not updated";

		}

		return "Airport Updated Successfully";

	}
}
