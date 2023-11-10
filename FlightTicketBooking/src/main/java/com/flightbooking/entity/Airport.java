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

@NoArgsConstructor
@Data
@Entity
@Table(name = "Airport_Details")
public class Airport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer airportid;
	@NotNull
	@NotBlank
    @Column(nullable = false)
	private String airportName;
	@NotNull
	@NotBlank
    @Column(nullable = false)
	private String airportCity;
	@NotNull
	@NotBlank
    @Column(nullable = false)
	private String airportCountry;

	public Airport(Integer airportid, String airportName, String airportCity, String airportCountry) {
		super();
		this.airportid = airportid;
		this.airportName = airportName;
		this.airportCity = airportCity;
		this.airportCountry = airportCountry;
	}

}
