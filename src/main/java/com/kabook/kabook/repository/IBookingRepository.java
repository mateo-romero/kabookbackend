package com.kabook.kabook.repository;

import com.kabook.kabook.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBookingRepository extends JpaRepository<Booking, Long> {
	@Query
	List<Booking> findByProductId(Long id);
	@Query
	List<Booking> findByUserId(Long id);
}
