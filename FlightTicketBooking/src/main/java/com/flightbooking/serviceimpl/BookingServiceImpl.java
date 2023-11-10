package com.flightbooking.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightbooking.dto.AirportDTO;
import com.flightbooking.dto.BookingDTO;
import com.flightbooking.dto.FlightDTO;
import com.flightbooking.dto.PassengerDTO;
import com.flightbooking.dto.ScheduleDTO;
import com.flightbooking.entity.Airport;
import com.flightbooking.entity.Booking;
import com.flightbooking.entity.Flight;
import com.flightbooking.entity.Passenger;
import com.flightbooking.entity.PlanTrip;
import com.flightbooking.entity.Schedule;
import com.flightbooking.entity.User;
import com.flightbooking.exception.BookingNotFoundException;
import com.flightbooking.repository.BookingRepository;
import com.flightbooking.repository.FlightRepository;
import com.flightbooking.repository.PassengerRepository;
import com.flightbooking.repository.PlanTripRepository;
import com.flightbooking.repository.ScheduleRepository;
import com.flightbooking.repository.UserRepository;
import com.flightbooking.services.IBookingService;

@Service
public class BookingServiceImpl implements IBookingService {

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	PlanTripRepository planTripRepository;

	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	private ModelMapper modelMapper;

	public String addBooking(Integer userId, Integer plantrip_id) {
		User user = userRepository.findById(userId).get();

		PlanTrip planTrip = planTripRepository.findById(plantrip_id).get();
		Flight flight = flightRepository.findById(planTrip.getFlight().getFlightId()).get();
		Schedule schedule = scheduleRepository.findById(planTrip.getSchedule().getScheduleId()).get();
		List<Passenger> passengers = new ArrayList<Passenger>();

		passengers = passengerRepository.findByuserId(userId);
		if(passengers.size() == 0) {
			return "Passengers List is Empty!! Please add 1 or More Passengers ";}
		else
		{
		if (passengers.size() <= flight.getSeatCapacity()) {
			Booking booking = new Booking();

			booking.setBookingDate(LocalDate.now());
			booking.setFlight(flight);
			booking.setSchedule(schedule);
			booking.setUser(user);
			booking.setPassengerList(passengers);
			booking.setNoOfPassengers(passengers.size());
			booking.setTotalCost(passengers.size() * flight.getFare());

			bookingRepository.save(booking);

			flight.setSeatCapacity(flight.getSeatCapacity() - passengers.size());
			flightRepository.save(flight);

			return "Booked Trip Successfully";
		} else {
			return "Sorry ! No seats Available or We have only " + flight.getSeatCapacity() + " seat/seats Available";
		}
		}
	

	}

	public String cancelBooking(Integer bookingid) {
		Booking booking;
		try {
			booking = bookingRepository.findById(bookingid).orElseThrow(() -> new BookingNotFoundException());
			Integer size = booking.getNoOfPassengers();

			bookingRepository.deleteBookedPassengers(bookingid);
			Flight flight = flightRepository.findById(booking.getFlight().getFlightId()).get();
			flight.setSeatCapacity(flight.getSeatCapacity() + size);
			flightRepository.save(flight);

			bookingRepository.cancelBooking(bookingid);

		} catch (BookingNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return "Booking Not found";

		}
		return "Booking Cancel Successfully";

	}

	public List<BookingDTO> viewBookings() {
		List<BookingDTO> bookingDTOs = new ArrayList<BookingDTO>();
		List<Booking> bookings = bookingRepository.findAll();

		for (Booking booking : bookings) {
			BookingDTO bookingDTO = new BookingDTO();
			bookingDTO.setBookingDate(booking.getBookingDate());
			bookingDTO.setBookingId(booking.getBookingId());
			bookingDTO.setFlight(mapFlightToDTO(booking.getFlight()));
			bookingDTO.setNoOfPassengers(booking.getNoOfPassengers());
			bookingDTO.setTotalCost(booking.getTotalCost());
			bookingDTO.setSchedule(mapScheduleToDTO(booking.getSchedule()));
			bookingDTO.setPassengerList(mapPassengersToDTO(booking.getPassengerList()));
			bookingDTOs.add(bookingDTO);
		}
		return bookingDTOs;

	}

	public BookingDTO viewBookingByBookingId(Integer bookingid) {
		Booking booking = bookingRepository.findById(bookingid).get();
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setBookingDate(booking.getBookingDate());
		bookingDTO.setBookingId(booking.getBookingId());
		bookingDTO.setFlight(mapFlightToDTO(booking.getFlight()));
		bookingDTO.setNoOfPassengers(booking.getNoOfPassengers());
		bookingDTO.setTotalCost(booking.getTotalCost());
		bookingDTO.setSchedule(mapScheduleToDTO(booking.getSchedule()));
		bookingDTO.setPassengerList(mapPassengersToDTO(booking.getPassengerList()));

		return bookingDTO;

	}

	public List<BookingDTO> viewBookingByUserId(Integer userId) {
		List<BookingDTO> bookingDTOs = new ArrayList<BookingDTO>();
		List<Booking> bookings = bookingRepository.findByUserId(userId);

		for (Booking booking : bookings) {
			BookingDTO bookingDTO = new BookingDTO();
			bookingDTO.setBookingDate(booking.getBookingDate());
			bookingDTO.setBookingId(booking.getBookingId());
			bookingDTO.setFlight(mapFlightToDTO(booking.getFlight()));
			bookingDTO.setNoOfPassengers(booking.getNoOfPassengers());
			bookingDTO.setTotalCost(booking.getTotalCost());
			bookingDTO.setSchedule(mapScheduleToDTO(booking.getSchedule()));
			bookingDTO.setPassengerList(mapPassengersToDTO(booking.getPassengerList()));
			bookingDTOs.add(bookingDTO);
		}
		return bookingDTOs;
	}

	public List<BookingDTO> viewBookingsByFlightId(Integer flightid) {

		List<BookingDTO> bookingDTOs = new ArrayList<BookingDTO>();
		List<Booking> bookings = bookingRepository.findByFlightId(flightid);

		for (Booking booking : bookings) {
			BookingDTO bookingDTO = new BookingDTO();
			bookingDTO.setBookingDate(booking.getBookingDate());
			bookingDTO.setBookingId(booking.getBookingId());
			bookingDTO.setFlight(mapFlightToDTO(booking.getFlight()));
			bookingDTO.setNoOfPassengers(booking.getNoOfPassengers());
			bookingDTO.setTotalCost(booking.getTotalCost());
			bookingDTO.setSchedule(mapScheduleToDTO(booking.getSchedule()));
			bookingDTO.setPassengerList(mapPassengersToDTO(booking.getPassengerList()));
			bookingDTOs.add(bookingDTO);
		}
		return bookingDTOs;

	}

	private List<PassengerDTO> mapPassengersToDTO(List<Passenger> passengerList) {
		List<PassengerDTO> passengerDTOs = passengerList.stream()
				.map(source -> modelMapper.map(source, PassengerDTO.class)).collect(Collectors.toList());

		return passengerDTOs;
	}

	private ScheduleDTO mapScheduleToDTO(Schedule schedule) {
		ScheduleDTO scheduleDTO = new ScheduleDTO();

		scheduleDTO.setDestinationAirport(mapAirportTODTO(schedule.getDestinationAirport()));
		scheduleDTO.setSourceAirport(mapAirportTODTO(schedule.getSourceAirport()));
		scheduleDTO.setArrivalDate(schedule.getArrivalDate());
		scheduleDTO.setDepartureDate(schedule.getDepartureDate());
		scheduleDTO.setScheduleId(schedule.getScheduleId());

		return scheduleDTO;
	}

	private FlightDTO mapFlightToDTO(Flight flight) {
		FlightDTO flightDTO = new FlightDTO();
		flightDTO = modelMapper.map(flight, FlightDTO.class);
		return flightDTO;
	}

	private AirportDTO mapAirportTODTO(Airport adAirport) {
		// TODO Auto-generated method stub
		AirportDTO airportDTO = new AirportDTO();

		airportDTO = modelMapper.map(adAirport, AirportDTO.class);
		return airportDTO;

	}
}
