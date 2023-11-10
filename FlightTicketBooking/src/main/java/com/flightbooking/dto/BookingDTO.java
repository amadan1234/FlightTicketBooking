package com.flightbooking.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingDTO {

	private Integer bookingId;
	private LocalDate bookingDate;
	private List<PassengerDTO> passengerList;
	private Double totalCost;
	private FlightDTO flight;
	private ScheduleDTO schedule;
	private Integer noOfPassengers;

	public BookingDTO(Integer bookingId, Double totalCost, Integer noOfPassengers) {
		super();
		this.bookingId = bookingId;
		this.totalCost = totalCost;
		this.noOfPassengers = noOfPassengers;
	}

}
