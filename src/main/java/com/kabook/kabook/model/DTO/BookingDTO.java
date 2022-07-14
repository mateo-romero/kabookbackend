package com.kabook.kabook.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
	private Long id;
	private String hourBookingCheckIn;
	private String hourBookingCheckOut;
	private LocalDate dateBookingCheckIn;
	private LocalDate dateBookingCheckOut;
	private Long productId;
	private String userEmail;
}
