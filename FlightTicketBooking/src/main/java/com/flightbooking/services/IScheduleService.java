
package com.flightbooking.services;

import java.time.LocalDate;
import java.util.List;

import com.flightbooking.dto.ScheduleDTO;

public interface IScheduleService {
	public String addSchedule(Integer s_airport_id, Integer d_airport_id, ScheduleDTO scheduleDTO);

	public String updateSchedule(Integer id, ScheduleDTO schedule);

	public List<ScheduleDTO> viewSchedules();

	public List<ScheduleDTO> viewBySourceAndDestination(String source, String destination);

	public List<ScheduleDTO> viewBySourceDestinationAndDepartureDate(String source, String destination,
			LocalDate departureDate);

}
