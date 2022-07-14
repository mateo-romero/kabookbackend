package com.kabook.kabook.service;

import com.kabook.kabook.model.Booking;
import com.kabook.kabook.model.DTO.BookingDTO;

import java.time.LocalDate;
import java.util.List;

public interface IBookingService {
	Booking getBookingById(Long id);
	List<BookingDTO> getBookingsByProductId(Long id);
	BookingDTO createBooking(BookingDTO bookingDTO);
	boolean isValidBooking(BookingDTO bookingDTO);
	List<BookingDTO> getBookingsByUserEmail(String userEmail);
	List<BookingDTO> getOverlappingBookingsList(LocalDate checkInDateOfDTO, LocalDate checkOutDateOfDTO, List<BookingDTO> bookingDTOList);
	boolean isBookingDateValid(LocalDate checkInDateOfDTO, LocalDate checkOutDateOfDTO, List<BookingDTO> bookingDTOList);
}
