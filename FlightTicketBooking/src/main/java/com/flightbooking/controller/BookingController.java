package com.flightbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightbooking.dto.BookingDTO;
import com.flightbooking.services.IBookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	IBookingService bookingService;

	@PostMapping("/placeBook")
	public String placeBooking(@RequestParam("userId") Integer userId, @RequestParam("tripId") Integer tripId) {
		return bookingService.addBooking(userId, tripId);
	}

	@DeleteMapping("/cancel/{id}")
	public String cancelBooking(@PathVariable(value = "id") Integer id) {
		return bookingService.cancelBooking(id);
	}

	@GetMapping("/allBookings")
	public List<BookingDTO> allBookings() {
		return bookingService.viewBookings();
	}

	@GetMapping("/findById/{id}")
	public BookingDTO findById(@PathVariable(value = "id") Integer id) {
		return bookingService.viewBookingByBookingId(id);
	}

	@GetMapping("/findByuserId/{id}")
	public List<BookingDTO> findByuserId(@PathVariable(value = "id") Integer id) {
		return bookingService.viewBookingByUserId(id);
	}

	@GetMapping("/findByflightId/{id}")
	public List<BookingDTO> findByflightId(@PathVariable(value = "id") Integer id) {
		return bookingService.viewBookingsByFlightId(id);
	}

}
