package com.flightbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flightbooking.entity.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

	@Query(value = "select * from passengertbl where user_user_id=?1", nativeQuery = true)
	public List<Passenger> findByuserId(Integer userId);

	public Passenger findByPassengerUIN(Long uin);
}
