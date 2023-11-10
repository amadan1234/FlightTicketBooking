package com.flightbooking.services;

import com.flightbooking.dto.UserDTO;

public interface IUserService {
	public UserDTO registerUser(UserDTO user);
	public String loginUser(String uname,String pass);
	public String logOutUser(String uname,String pass);
}
