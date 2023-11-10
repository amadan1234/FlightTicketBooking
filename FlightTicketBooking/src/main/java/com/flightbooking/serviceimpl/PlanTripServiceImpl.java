package com.flightbooking.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightbooking.dto.AirportDTO;
import com.flightbooking.dto.FlightDTO;
import com.flightbooking.dto.PlanTripDTO;
import com.flightbooking.dto.ScheduleDTO;
import com.flightbooking.entity.Airport;
import com.flightbooking.entity.Flight;
import com.flightbooking.entity.PlanTrip;
import com.flightbooking.entity.Schedule;
import com.flightbooking.exception.FlightNotFoundException;
import com.flightbooking.exception.ScheduleNotFoundException;
import com.flightbooking.repository.AirportRepository;
import com.flightbooking.repository.FlightRepository;
import com.flightbooking.repository.PlanTripRepository;
import com.flightbooking.repository.ScheduleRepository;
import com.flightbooking.services.IPlanTripService;

@Service
public class PlanTripServiceImpl implements IPlanTripService{

	@Autowired
	PlanTripRepository planTripRepository;
	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	FlightRepository flightRepository;
	@Autowired
	AirportRepository airportRepository;
	@Autowired
	private ModelMapper modelMapper;

	public String AddTrip(Integer schedule_id, Integer flight_id) {
		try {
			Flight flight = flightRepository.findById(flight_id).orElseThrow(() -> new FlightNotFoundException());
			Schedule schedule = scheduleRepository.findById(schedule_id)
					.orElseThrow(() -> new ScheduleNotFoundException());
			PlanTrip trip = new PlanTrip();

			trip.setFlight(flight);
			trip.setSchedule(schedule);

			planTripRepository.save(trip);

			return "trip Added Successfully";
		} catch (ScheduleNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return "Schedule data not found";

		} catch (FlightNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return "Flight data not found";

		}
	}

	public List<PlanTripDTO> viewBySourceDestination(String source, String destination) {

		Airport sAirport = airportRepository.findByAirportName(source);
		Airport dAirport = airportRepository.findByAirportName(destination);
		List<Schedule> schedules = scheduleRepository.findBySourceAirportAndDestinationAirport(sAirport, dAirport);
		List<PlanTripDTO> planTripDTOs = new ArrayList<PlanTripDTO>();
		List<PlanTrip> planTrips = new ArrayList<PlanTrip>();
		PlanTrip planTrip = new PlanTrip();
		for (Schedule schedule : schedules) {
			planTrip = planTripRepository.findByScheduleId(schedule.getScheduleId());
			System.out.println(schedule);
			System.out.println(planTrip);
			if (planTrip != null) {
				planTrips.add(planTrip);
			}

		}

		for (PlanTrip planTrip1 : planTrips) {
			PlanTripDTO planTripDTO = new PlanTripDTO();
			planTripDTO.setFlight(mapFlightToDTO(planTrip1.getFlight()));
			planTripDTO.setTripId(planTrip1.getTripId());
			planTripDTO.setSchedule(mapScheduleToDTO(planTrip1.getSchedule()));
			planTripDTOs.add(planTripDTO);
		}
		return planTripDTOs;
	}

	public List<PlanTripDTO> viewBySourceDestinationAndDepartureDate(String source, String destination,
			LocalDate departureDate) {
		Airport sAirport = airportRepository.findByAirportName(source);
		Airport dAirport = airportRepository.findByAirportName(destination);
		List<Schedule> schedules = scheduleRepository.findBySourceAirportAndDestinationAirportAndDepartureDate(sAirport,
				dAirport, departureDate);
		List<PlanTripDTO> planTripDTOs = new ArrayList<PlanTripDTO>();
		List<PlanTrip> planTrips = new ArrayList<PlanTrip>();
		PlanTrip planTrip = new PlanTrip();
		for (Schedule schedule : schedules) {
			planTrip = planTripRepository.findByScheduleId(schedule.getScheduleId());
			System.out.println(schedule);
			System.out.println(planTrip);
			if (planTrip != null) {
				planTrips.add(planTrip);
			}

		}

		for (PlanTrip planTrip1 : planTrips) {
			PlanTripDTO planTripDTO = new PlanTripDTO();
			planTripDTO.setFlight(mapFlightToDTO(planTrip1.getFlight()));
			planTripDTO.setTripId(planTrip1.getTripId());
			planTripDTO.setSchedule(mapScheduleToDTO(planTrip1.getSchedule()));
			planTripDTOs.add(planTripDTO);
		}
		return planTripDTOs;
	}

	public List<PlanTripDTO> ViewAllTrips() {

		List<PlanTrip> planTrips = planTripRepository.findAll();

		List<PlanTripDTO> planTripDTOs = new ArrayList<PlanTripDTO>();

		for (PlanTrip planTrip : planTrips) {
			PlanTripDTO planTripDTO = new PlanTripDTO();
			planTripDTO.setFlight(mapFlightToDTO(planTrip.getFlight()));
			planTripDTO.setSchedule(mapScheduleToDTO(planTrip.getSchedule()));
			planTripDTO.setTripId(planTrip.getTripId());
			planTripDTOs.add(planTripDTO);
		}
		return planTripDTOs;
	}

	private ScheduleDTO mapScheduleToDTO(Schedule schedule) {
		ScheduleDTO scheduleDTO = new ScheduleDTO();

		scheduleDTO.setDestinationAirport(mapAirportTODTO(schedule.getDestinationAirport()));
		scheduleDTO.setSourceAirport(mapAirportTODTO(schedule.getSourceAirport()));
		scheduleDTO.setArrivalDate(schedule.getArrivalDate());
		scheduleDTO.setDepartureDate(schedule.getDepartureDate());
		scheduleDTO.setScheduleId(schedule.getScheduleId());

		return scheduleDTO;
	}

	private FlightDTO mapFlightToDTO(Flight flight) {
		FlightDTO flightDTO = modelMapper.map(flight, FlightDTO.class);
		return flightDTO;
	}

	private AirportDTO mapAirportTODTO(Airport adAirport) {
		AirportDTO airportDTO = modelMapper.map(adAirport, AirportDTO.class);
		return airportDTO;

	}
}
