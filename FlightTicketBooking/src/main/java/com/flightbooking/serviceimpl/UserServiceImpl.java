package com.flightbooking.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightbooking.dto.UserDTO;
import com.flightbooking.entity.User;
import com.flightbooking.repository.UserRepository;
import com.flightbooking.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	public UserDTO registerUser(UserDTO userDTO) {
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setMobileNumber(userDTO.getMobileNumber());
		user.setPassword(userDTO.getPassword());
		user.setUserName(userDTO.getUserName());

		userRepository.save(user);
		return userDTO;

	}

	public String loginUser(String username, String password) {
		User user = userRepository.findByUserNameAndPassword(username, password);

		if (user != null) {
			return "Login Successfull";
		} else
			return "Sorry! Please check username and password";
	}

	public String logOutUser(String username, String password) {
		User user = userRepository.findByUserNameAndPassword(username, password);

		if (user != null) {
			return "LogOut Successfull";
		} else
			return "Sorry! Please check username and password";
	}

}
