package com.flightbooking.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "User_Details")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@NotNull
	@NotBlank
    @Column(nullable = false)
	private String userName;
	@NotNull
	@NotBlank
    @Column(nullable = false)
	private String password;
	@NotNull
	@NotBlank
    @Column(nullable = false,unique = true)
	private String email;
	@NotNull
    @Column(nullable = false,unique = true)
	private Long mobileNumber;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Passenger> passengers = new ArrayList<>();
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Booking> booking = new ArrayList<>();

	public User(Integer userId, String userName, String password, String email, Long mobileNumber) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}

}
