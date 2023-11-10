package com.flightbooking.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "Schedule_Details")
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer scheduleId;
	@OneToOne
	@JoinColumn(name = "source_airport_id")
	private Airport sourceAirport;
	@OneToOne
	@JoinColumn(name = "destination_airport_id")
	private Airport destinationAirport;
	@NotNull
    @Column(nullable = false)
	private LocalDate arrivalDate;
	@NotNull
    @Column(nullable = false)
	private LocalDate departureDate;

}
