package com.flightbooking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Plantrip_Details")
public class PlanTrip {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tripId;

	@OneToOne
	@JoinColumn(name = "flight_id")
	private Flight flight;
	@OneToOne
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;
}
