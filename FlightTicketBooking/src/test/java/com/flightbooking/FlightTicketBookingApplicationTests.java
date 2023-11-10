package com.flightbooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.flightbooking.dto.AirportDTO;
import com.flightbooking.dto.FlightDTO;
import com.flightbooking.dto.UserDTO;
import com.flightbooking.entity.Airport;
import com.flightbooking.entity.Flight;
import com.flightbooking.entity.User;
import com.flightbooking.repository.AirportRepository;
import com.flightbooking.repository.FlightRepository;
import com.flightbooking.repository.PassengerRepository;
import com.flightbooking.repository.UserRepository;
import com.flightbooking.serviceimpl.AirportServiceImpl;
import com.flightbooking.serviceimpl.FlightServiceImpl;
import com.flightbooking.serviceimpl.PassengerServiceImpl;
import com.flightbooking.serviceimpl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class FlightTicketBookingApplicationTests {

	@InjectMocks
	UserServiceImpl userServiceImpl;
	@InjectMocks
	FlightServiceImpl flightServiceImpl;
	@InjectMocks
	AirportServiceImpl airportServiceImpl;

	@Mock
	UserRepository userRepository;
	@Mock
	AirportRepository airportRepository;
	@Mock
	FlightRepository flightRepository;

	@Test
	public void addUserTest() {
		User user = new User(3, "pqw", "123", "wwr@email", 1222221123L);
		UserDTO userDTO = new UserDTO(3, "pqw", "123", "wwr@email", 1222221123L);
		when(userRepository.save(user)).thenReturn(user);
		UserDTO reuserDTO = userServiceImpl.registerUser(userDTO);
		assertEquals(reuserDTO.getUserName(), user.getUserName());
		assertEquals(reuserDTO.getEmail(), user.getEmail());
		assertEquals(reuserDTO.getUserId(), user.getUserId());

	}

	@Test
	public void getFlightsTest()
	{
		when(flightRepository.findAll()).thenReturn(Stream.of(new Flight(1,"abc",123,444.00),new Flight(2,"abc",234,555.00)).collect(Collectors.toList()));
		assertEquals(2, flightServiceImpl.viewAllFlights().size());
	}

	@Test
	public void addFlightTest() {
		Flight flight = new Flight(1, "abc", 123, 444.00);
		FlightDTO flightDTO = new FlightDTO(1, "abc", 123, 444.00);
		when(flightRepository.save(flight)).thenReturn(flight);
		FlightDTO reflightDTO = flightServiceImpl.addFlight(flightDTO);
		assertEquals(reflightDTO.getFlightName(), flight.getFlightName());
		assertEquals(reflightDTO.getFare(), flight.getFare());
		assertEquals(reflightDTO.getSeatCapacity(), flight.getSeatCapacity());

	}

	@Test
	public void addAirportTest() {
		Airport airport = new Airport(1, "abc", "ijk", "pqr");
		AirportDTO airportDTO = new AirportDTO(1, "abc", "ijk", "pqr");
		when(airportRepository.save(airport)).thenReturn(airport);
		AirportDTO reairportDTO = airportServiceImpl.addAirportDetails(airportDTO);
		assertEquals(reairportDTO.getAirportCity(), airport.getAirportCity());
		assertEquals(reairportDTO.getAirportCountry(), airport.getAirportCountry());
		assertEquals(reairportDTO.getAirportName(), airport.getAirportName());

	}

	@Test
	public void getAirportsTest()
	{
		when(airportRepository.findAll()).thenReturn(Stream.of(new Airport(1, "abc", "ijk", "pqr"),new Airport(2, "hhh", "ijk", "pqr")).collect(Collectors.toList()));
		assertEquals(2, airportServiceImpl.viewAirports().size());
	}

	@Test
	public void findByFlightIdTest() {
		Flight flight = new Flight(3, "pqw", 123, 444.00);
		when(flightRepository.findById(3)).thenReturn(Optional.of(flight));
		FlightDTO flightDTO = flightServiceImpl.viewByFlightId(3);
		assertEquals(flightDTO.getFlightName(), flight.getFlightName());
		assertEquals(flightDTO.getFare(), flight.getFare());
		assertEquals(flightDTO.getSeatCapacity(), flight.getSeatCapacity());

	}

}
