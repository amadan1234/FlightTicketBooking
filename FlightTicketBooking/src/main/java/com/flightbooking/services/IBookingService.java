package com.flightbooking.services;

import java.util.List;

import com.flightbooking.dto.BookingDTO;

public interface IBookingService {

	public String addBooking(Integer userId, Integer flightId);

	public String cancelBooking(Integer bookingid);

	public List<BookingDTO> viewBookings();

	public List<BookingDTO> viewBookingByUserId(Integer userId);

	public BookingDTO viewBookingByBookingId(Integer bookingid);

	public List<BookingDTO> viewBookingsByFlightId(Integer flightid);

}
