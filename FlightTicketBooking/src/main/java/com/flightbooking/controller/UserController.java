package com.flightbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightbooking.dto.UserDTO;
import com.flightbooking.services.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserService userService;

	@PostMapping("/registerUser")
	public UserDTO addUser(@RequestBody UserDTO userDTO) {
		return userService.registerUser(userDTO);
	}

	@PostMapping("/login/{username}/{password}")
	public String userLogin(@PathVariable(value = "username") String username,
			@PathVariable(value = "password") String password) {
		return userService.loginUser(username, password);
	}

	@PostMapping("/logout/{username}/{password}")
	public String userLogout(@PathVariable(value = "username") String username,
			@PathVariable(value = "password") String password) {
		return userService.logOutUser(username, password);
	}

}
