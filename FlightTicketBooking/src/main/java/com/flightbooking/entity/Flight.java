package com.flightbooking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Flight_Details")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer flightId;
	@NotNull
	@NotBlank
    @Column(nullable = false)
	private String flightName;
	@NotNull
    @Column(nullable = false)
	private Integer seatCapacity;
	@NotNull
    @Column(nullable = false)
	private Double fare; // cost per seat

	public Flight(Integer flightId, String flightName, Integer seatCapacity, Double fare) {
		super();
		this.flightId = flightId;
		this.flightName = flightName;
		this.seatCapacity = seatCapacity;
		this.fare = fare;
	}

}
