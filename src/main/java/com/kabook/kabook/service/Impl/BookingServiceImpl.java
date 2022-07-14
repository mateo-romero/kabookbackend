package com.kabook.kabook.service.Impl;

import com.kabook.kabook.model.Booking;
import com.kabook.kabook.model.DTO.BookingDTO;
import com.kabook.kabook.model.Product;
import com.kabook.kabook.model.User;
import com.kabook.kabook.repository.IBookingRepository;
import com.kabook.kabook.repository.IProductRepository;
import com.kabook.kabook.service.IBookingService;
import com.kabook.kabook.service.IProductService;
import com.kabook.kabook.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements IBookingService {

	@Autowired
	private IBookingRepository bookingRepository;

	@Autowired
	private IProductRepository productRepository;

	@Autowired
	private IUserService userService;

	@Override
	public Booking getBookingById(Long id) {
		return bookingRepository.findById(id).orElse(null);
	}

	@Override
	public List<BookingDTO> getBookingsByProductId(Long id) {
		List<Booking> bookingList = bookingRepository.findByProductId(id);
		return !bookingList.isEmpty()
				? mapBookingsToBookingsDTO(bookingList)
				: new ArrayList<>();
	}

	@Override
	public BookingDTO createBooking(BookingDTO bookingDTO) {
		BookingDTO savedBookingDTO;
		Booking bookingToSave;
		Booking savedBooking;
		User user = userService.findByEmail(bookingDTO.getUserEmail());
		Optional<Product> product = productRepository.findById(bookingDTO.getProductId());
		if (product.isEmpty()) {
			return null;
		}
		bookingToSave = new Booking(
				bookingDTO.getHourBookingCheckIn(),
				bookingDTO.getHourBookingCheckOut(),
				bookingDTO.getDateBookingCheckIn(),
				bookingDTO.getDateBookingCheckOut(),
				product.get(),
				user
		);
		savedBooking = bookingRepository.save(bookingToSave);
		savedBookingDTO = new BookingDTO(
				savedBooking.getId(),
				savedBooking.getHourBookingCheckIn(),
				savedBooking.getHourBookingCheckOut(),
				savedBooking.getDateBookingCheckIn(),
				savedBooking.getDateBookingCheckOut(),
				savedBooking.getProduct().getId(),
				savedBooking.getUser().getEmail()
		);
		return savedBookingDTO;
	}

	@Override
	public boolean isValidBooking(BookingDTO bookingDTOToValidate) {
		LocalDate checkInDateOfDTO = bookingDTOToValidate.getDateBookingCheckIn();
		LocalDate checkOutDateOfDTO = bookingDTOToValidate.getDateBookingCheckOut();
		// Si no existe el usuario o el producto la reserva es invalida
		if (!isUserAndProductValid(bookingDTOToValidate)) {
			return false;
		}
		// Si no hay reservas hechas en el producto la reserva es valida
		List<BookingDTO> bookingDTOList = getBookingsByProductId(bookingDTOToValidate.getProductId());
		if (bookingDTOList == null || bookingDTOList.isEmpty()) {
			return true;
		}
		return isBookingDateValid(checkInDateOfDTO, checkOutDateOfDTO, bookingDTOList);
	}

	@Override
	public List<BookingDTO> getBookingsByUserEmail(String userEmail) {
		Long userId = userService.findByEmail(userEmail).getId();
		List<Booking> bookingList = bookingRepository.findByUserId(userId);
		return !bookingList.isEmpty()
				? mapBookingsToBookingsDTO(bookingList)
				: null;
	}

	@Override
	public List<BookingDTO> getOverlappingBookingsList(
			LocalDate checkInDateOfDTO,
			LocalDate checkOutDateOfDTO,
			List<BookingDTO> bookingDTOList) {
		List<BookingDTO> overlappingBookingList = new ArrayList<>();
		for (BookingDTO existingBooking : bookingDTOList) {
			LocalDate existingBookingCheckIn = existingBooking.getDateBookingCheckIn();
			LocalDate existingBookingCheckOut = existingBooking.getDateBookingCheckOut();
			// Las condiciones deben ser distintas entre si para que la reserva sea valida
			if ((existingBookingCheckIn.isBefore(checkInDateOfDTO) && existingBookingCheckOut.isBefore(checkInDateOfDTO))
					==
					(existingBookingCheckIn.isAfter(checkOutDateOfDTO) && existingBookingCheckOut.isAfter(checkOutDateOfDTO))) {
				overlappingBookingList.add(existingBooking);
				break;
			}
		}
		return overlappingBookingList;
	}

	@Override
	public boolean isBookingDateValid(
			LocalDate checkInDateOfDTO,
			LocalDate checkOutDateOfDTO,
			List<BookingDTO> bookingDTOList) {
		// Revisa que el checkout sea posterior al check in
		if (checkOutDateOfDTO.isAfter(checkInDateOfDTO)) {
			// Obtiene una lista de reservas que crean conflicto
			List<BookingDTO> overlappingBookingList = getOverlappingBookingsList(
					checkInDateOfDTO,
					checkOutDateOfDTO,
					bookingDTOList);
			// Si lista es nula o esta vacia la reserva es valida
			if (overlappingBookingList == null || overlappingBookingList.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	private boolean isUserAndProductValid(BookingDTO bookingDTOToValidate) {
		Optional<Product> product = productRepository.findById(bookingDTOToValidate.getProductId());
		User user = userService.findByEmail(bookingDTOToValidate.getUserEmail());
		return user != null && product.isPresent();
	}

	private List<BookingDTO> mapBookingsToBookingsDTO(List<Booking> bookingList) {
		return bookingList
				.stream()
				.map(booking -> new BookingDTO(
						booking.getId(),
						booking.getHourBookingCheckIn(),
						booking.getHourBookingCheckOut(),
						booking.getDateBookingCheckIn(),
						booking.getDateBookingCheckOut(),
						booking.getProduct().getId(),
						booking.getUser().getEmail()
				))
				.collect(Collectors.toList());
	}
}
