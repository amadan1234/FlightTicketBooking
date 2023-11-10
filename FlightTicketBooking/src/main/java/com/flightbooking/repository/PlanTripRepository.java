package com.flightbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flightbooking.entity.PlanTrip;

public interface PlanTripRepository extends JpaRepository<PlanTrip, Integer> {

	@Query(value = "select * from plantriptbl where schedule_id=?1", nativeQuery = true)
	public PlanTrip findByScheduleId(Integer schedule_id);
}
