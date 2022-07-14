package com.kabook.kabook.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ProductSearchRequest {
	private Long cityId;
	private LocalDate dateBookingCheckIn;
	private LocalDate dateBookingCheckOut;
}
