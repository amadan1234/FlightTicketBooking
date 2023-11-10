package com.flightbooking.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightbooking.dto.ScheduleDTO;
import com.flightbooking.services.IScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	IScheduleService scheduleService;

	@PostMapping("/addSchedule/{source_airport_id}/{destination_airport_id}")
	public String addSchedule(@PathVariable(value = "source_airport_id") Integer source_airport_id,
			@PathVariable(value = "destination_airport_id") Integer destination_airport_id,
			@RequestBody ScheduleDTO scheduleDTO) {
		return scheduleService.addSchedule(source_airport_id, destination_airport_id, scheduleDTO);
	}

	@GetMapping("/allSchedules")
	public List<ScheduleDTO> readAllSchedules() {
		return scheduleService.viewSchedules();
	}

	@GetMapping("/findBySAirportAndDAirport/{sname}/{dname}")
	public List<ScheduleDTO> findBySAirportAndDAirport(@PathVariable(value = "sname") String sname,
			@PathVariable(value = "dname") String dname) {
		return scheduleService.viewBySourceAndDestination(sname, dname);
	}

	@GetMapping("/findBySAirportAndDAirportAndDDate/{sname}/{dname}/{date}")
	public List<ScheduleDTO> findBySAirportAndDAirportDDate(@PathVariable(value = "sname") String sname,
			@PathVariable(value = "dname") String dname,
			@PathVariable(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return scheduleService.viewBySourceDestinationAndDepartureDate(sname, dname, date);
	}

	@PutMapping("/updateSchedule/{no}")
	public String updateSchedule(@PathVariable(value = "no") int no, @RequestBody ScheduleDTO scheduleDTO) {

		return scheduleService.updateSchedule(no, scheduleDTO);
	}

}
