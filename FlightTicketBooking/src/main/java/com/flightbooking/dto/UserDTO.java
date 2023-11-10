package com.flightbooking.dto;

import lombok.Data;

@Data
public class UserDTO {
	private Integer userId;

	private String userName;
	private String password;
	private String email;
	private Long mobileNumber;

	public UserDTO(Integer userId, String userName, String password, String email, Long mobileNumber) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}

}
