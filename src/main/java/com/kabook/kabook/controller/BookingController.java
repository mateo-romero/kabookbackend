package com.kabook.kabook.controller;

import com.kabook.kabook.model.DTO.BookingDTO;
import com.kabook.kabook.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@CrossOrigin
public class BookingController {

	@Autowired
	private IBookingService bookingService;

	/**
	 * Obtiene las reservas segun id de producto
	 * <p>
	 * Headers - KEY: Authorization, VALUE: Bearer JWT
	 *
	 * @param id id del producto
	 * @return json con array de reservas
	 */
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getBookingsByProductId(@PathVariable Long id) {
		List<BookingDTO> bookingList = bookingService.getBookingsByProductId(id);
		if (bookingList != null && !bookingList.isEmpty()) {
			return ResponseEntity.ok(bookingList);
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Crea una nueva reserva de un producto
	 * <p>
	 * Headers - KEY: Authorization, VALUE: Bearer JWT
	 *
	 * @param bookingDTO reserva que se quiere realizar
	 * @return reserva
	 */
	@PostMapping("/product-booking/{id}")
	public ResponseEntity<?> createBooking(@RequestBody BookingDTO bookingDTO) {
		return bookingService.isValidBooking(bookingDTO)
				? new ResponseEntity(bookingService.createBooking(bookingDTO), HttpStatus.CREATED)
				: new ResponseEntity<>(
				"Ha ocurrido un error realizando la reserva, por favor intente nuevamente",
				HttpStatus.CONFLICT);
	}

	/**
	 * Busca reservas hechas por usuario
	 * <p>
	 * Headers - KEY: Authorization, VALUE: Bearer JWT
	 *
	 * @param userEmail email del usuario que se quiere buscar
	 * @return lista de reservas
	 */
	@GetMapping("/user/{userEmail}")
	public ResponseEntity<?> getBookingsByUserEmail(@PathVariable String userEmail) {
		List<BookingDTO> bookingDTOList = bookingService.getBookingsByUserEmail(userEmail);
		return  bookingDTOList == null || bookingDTOList.isEmpty()
				? new ResponseEntity<>("No se encuentran reservas para este usuario", HttpStatus.NOT_FOUND)
				: ResponseEntity.ok(bookingDTOList);
	}
}
