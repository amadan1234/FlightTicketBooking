package com.flightbooking.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Booking_Details")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	@ManyToOne
	private User user;
	@NotNull
    @Column(nullable = false)
	private LocalDate bookingDate;
	@ManyToMany
	@JoinTable(name = "booked_passengers", joinColumns = @JoinColumn(name = "booking_id"), inverseJoinColumns = @JoinColumn(name = "passenger_id"))
	private List<Passenger> passengerList = new ArrayList<>();
	
	@NotNull
    @Column(nullable = false)
	private Double totalCost;
	@OneToOne
	@JoinColumn(name = "flight_id")
	private Flight flight;
	@OneToOne
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;
	@NotNull
    @Column(nullable = false)
	private Integer noOfPassengers;

	public Booking(Integer bookingId, Double totalCost, Integer noOfPassengers) {
		super();
		this.bookingId = bookingId;
		this.totalCost = totalCost;
		this.noOfPassengers = noOfPassengers;
	}

}
