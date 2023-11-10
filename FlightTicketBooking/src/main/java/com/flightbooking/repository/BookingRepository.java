package com.flightbooking.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flightbooking.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking , Integer>{

	@Modifying
	@Transactional
	@Query(value = "delete from booked_passengers where booking_id = ?1 ", nativeQuery = true)
	public void deleteBookedPassengers(Integer booking_id);
	@Modifying
	@Transactional
	@Query(value = "delete from bookingtbl where booking_id = ?1 ", nativeQuery = true)
	public void cancelBooking(Integer booking_id);
	
	@Query(value = "select * from bookingtbl where user_user_id=?1", nativeQuery = true)
	public List<Booking> findByUserId(Integer userId);
	@Query(value = "select * from bookingtbl where flight_id=?1", nativeQuery = true)
	public List<Booking> findByFlightId(Integer flightId);
}
