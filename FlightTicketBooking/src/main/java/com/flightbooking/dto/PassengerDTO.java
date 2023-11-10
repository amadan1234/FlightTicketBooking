package com.flightbooking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PassengerDTO {

	private Integer passengerId;
	private String passengerName;
	private Integer age;
	private Long passengerUIN;

	public PassengerDTO(Integer passengerId, String passengerName, Integer age, Long passengerUIN) {
		super();
		this.passengerId = passengerId;
		this.passengerName = passengerName;
		this.age = age;
		this.passengerUIN = passengerUIN;
	}

}
