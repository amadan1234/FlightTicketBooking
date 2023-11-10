package com.flightbooking.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightbooking.dto.AirportDTO;
import com.flightbooking.dto.ScheduleDTO;
import com.flightbooking.entity.Airport;
import com.flightbooking.entity.Schedule;
import com.flightbooking.exception.AirportNotFoundException;
import com.flightbooking.exception.ScheduleNotFoundException;
import com.flightbooking.repository.AirportRepository;
import com.flightbooking.repository.ScheduleRepository;
import com.flightbooking.services.IScheduleService;

@Service
public class ScheduleServiceImpl implements IScheduleService {

	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	AirportRepository airportRepository;
	@Autowired
	private ModelMapper modelMapper;

	public String addSchedule(Integer s_airport_id, Integer d_airport_id, ScheduleDTO scheduleDTO) {
		try {
		
		Airport sourceAirport = airportRepository.findById(s_airport_id).orElseThrow(() -> new AirportNotFoundException());
		Airport destinationAirport = airportRepository.findById(d_airport_id).orElseThrow(() -> new AirportNotFoundException());
		
		if(sourceAirport.equals(destinationAirport))
		{
			System.out.println("Source and Destination cannot be same...");
			return "Source and Destination cannot be same...";
		}
		else {
		Schedule schedule = new Schedule();

		schedule.setArrivalDate(scheduleDTO.getArrivalDate());
		schedule.setDepartureDate(scheduleDTO.getDepartureDate());
		schedule.setDestinationAirport(destinationAirport);
		schedule.setSourceAirport(sourceAirport);
		schedule.setScheduleId(scheduleDTO.getScheduleId());

		scheduleRepository.save(schedule);

		return "Schedule Added Successfully";
		}
		}
		catch (AirportNotFoundException e) {
			System.out.println(e);
			return "Airport data not updated";

		}
	}

	public List<ScheduleDTO> viewSchedules() {
		List<ScheduleDTO> scheduleDTOs = new ArrayList<ScheduleDTO>();
		List<Schedule> schedules = scheduleRepository.findAll();

		for (Schedule schedule : schedules) {
			ScheduleDTO scheduleDTO = new ScheduleDTO();
			scheduleDTO.setArrivalDate(schedule.getArrivalDate());
			scheduleDTO.setDepartureDate(schedule.getDepartureDate());
			scheduleDTO.setScheduleId(schedule.getScheduleId());
			scheduleDTO.setSourceAirport(mapAirportTODTO(schedule.getSourceAirport()));
			scheduleDTO.setDestinationAirport(mapAirportTODTO(schedule.getDestinationAirport()));

			scheduleDTOs.add(scheduleDTO);
		}
		return scheduleDTOs;
	}

	public List<ScheduleDTO> viewBySourceAndDestination(String source, String destination) {
		List<ScheduleDTO> scheduleDTOs = new ArrayList<ScheduleDTO>();

		Airport sAirport = airportRepository.findByAirportName(source);
		Airport dAirport = airportRepository.findByAirportName(destination);

		List<Schedule> schedules = scheduleRepository.findBySourceAirportAndDestinationAirport(sAirport, dAirport);
		for (Schedule schedule : schedules) {
			ScheduleDTO scheduleDTO = new ScheduleDTO();
			scheduleDTO.setArrivalDate(schedule.getArrivalDate());
			scheduleDTO.setDepartureDate(schedule.getDepartureDate());
			scheduleDTO.setScheduleId(schedule.getScheduleId());
			scheduleDTO.setSourceAirport(mapAirportTODTO(schedule.getSourceAirport()));
			scheduleDTO.setDestinationAirport(mapAirportTODTO(schedule.getDestinationAirport()));

			scheduleDTOs.add(scheduleDTO);
		}
		return scheduleDTOs;

	}

	public List<ScheduleDTO> viewBySourceDestinationAndDepartureDate(String source, String destination,
			LocalDate departureDate) {
		List<ScheduleDTO> scheduleDTOs = new ArrayList<ScheduleDTO>();

		Airport sAirport = airportRepository.findByAirportName(source);
		Airport dAirport = airportRepository.findByAirportName(destination);

		List<Schedule> schedules = scheduleRepository.findBySourceAirportAndDestinationAirportAndDepartureDate(sAirport,
				dAirport, departureDate);
		for (Schedule schedule : schedules) {
			ScheduleDTO scheduleDTO = new ScheduleDTO();
			scheduleDTO.setArrivalDate(schedule.getArrivalDate());
			scheduleDTO.setDepartureDate(schedule.getDepartureDate());
			scheduleDTO.setScheduleId(schedule.getScheduleId());
			scheduleDTO.setSourceAirport(mapAirportTODTO(schedule.getSourceAirport()));
			scheduleDTO.setDestinationAirport(mapAirportTODTO(schedule.getDestinationAirport()));

			scheduleDTOs.add(scheduleDTO);
		}
		return scheduleDTOs;
	}

	public String updateSchedule(Integer id, ScheduleDTO scheduleDTO) {

		Schedule schedule;
		try {
			schedule = scheduleRepository.findById(id).orElseThrow(() -> new ScheduleNotFoundException());

			if (scheduleDTO.getArrivalDate() != null)
				schedule.setArrivalDate(scheduleDTO.getArrivalDate());

			if (scheduleDTO.getDepartureDate() != null)
				schedule.setDepartureDate(scheduleDTO.getDepartureDate());
			if (scheduleDTO.getDestinationAirport() != null)
				schedule.setDestinationAirport(MapDTOTOAirport(scheduleDTO.getDestinationAirport()));
			if (scheduleDTO.getSourceAirport() != null)
				schedule.setSourceAirport(MapDTOTOAirport(scheduleDTO.getSourceAirport()));

			scheduleRepository.save(schedule);
		} catch (ScheduleNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return "Schedule data not updated";

		}

		return "Schedule Updated Successfully";

	}

	private Airport MapDTOTOAirport(AirportDTO destinationAirportDTO) {
		Airport airport = modelMapper.map(destinationAirportDTO, Airport.class);
		return airport;
	}

	private AirportDTO mapAirportTODTO(Airport adAirport) {
		// TODO Auto-generated method stub
		AirportDTO airportDTO = modelMapper.map(adAirport, AirportDTO.class);

		return airportDTO;
	}
}
