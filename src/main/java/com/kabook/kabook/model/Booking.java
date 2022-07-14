package com.kabook.kabook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "booking")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class Booking {

	@Id
	@SequenceGenerator(name = "booking_sequence" , sequenceName = "booking_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="hour_booking_check_in")
	private String hourBookingCheckIn;
	@Column(name="hour_booking_check_out")
	private String hourBookingCheckOut;
	@Column(name="date_booking_check_in")
	private LocalDate dateBookingCheckIn;
	@Column(name="date_booking_check_out")
	private LocalDate dateBookingCheckOut;

	// Muchas reservas de un mismo producto
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	// Muchas reservas de un mismo usuario
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Booking(String hourBookingCheckIn, String hourBookingCheckOut, LocalDate dateBookingCheckIn, LocalDate dateBookingCheckOut, Product product, User user) {
		this.hourBookingCheckIn = hourBookingCheckIn;
		this.hourBookingCheckOut = hourBookingCheckOut;
		this.dateBookingCheckIn = dateBookingCheckIn;
		this.dateBookingCheckOut = dateBookingCheckOut;
		this.product = product;
		this.user = user;
	}
}
