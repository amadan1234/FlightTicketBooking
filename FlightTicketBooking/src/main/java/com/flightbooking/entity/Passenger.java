package com.flightbooking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Passenger_Details")
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer passengerId;

	@NotNull
	@NotBlank
    @Column(nullable = false)
	private String passengerName;
	@NotNull
    @Column(nullable = false)
	private Integer age;
	@NotNull
    @Column(nullable = false,unique = true)
	private Long passengerUIN;
	@ManyToOne
	private User user;

	public Passenger(Integer passengerId, String passengerName, Integer age, Long passengerUIN) {
		super();
		this.passengerId = passengerId;
		this.passengerName = passengerName;
		this.age = age;
		this.passengerUIN = passengerUIN;
	}

}
