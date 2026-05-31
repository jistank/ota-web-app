package com.ota.app.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // tells JPA this is a database table
@Table(name= "bookings") // table name in PostgreSQL
public class Booking {

	@Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PostgreSQL auto-generates the ID (1, 2, 3...)
	private Long id;
	
	@ManyToOne // bookings belong to one user
	@JoinColumn(name = "user_id", nullable = false) // creates column "user_id" in database
	@JsonIgnore // prevents infinite recursion when serializing to JSON
	private User user; // is needed the obj user
	
	@ManyToOne
	@JoinColumn(name = "hotel_id", nullable = false)
	private Hotel hotel;
	
	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	private Room room_id;
	
	@Column(nullable = false)
	private LocalDate bookingDate; // when the booking was made
	
	@Column(nullable = false)
	private LocalDate checkInDate;

	@Column(nullable = false)
	private LocalDate checkOutDate;
	
	@Column(nullable = false)
	private Double totalPrice;
	
	@Column(nullable = false, unique = true)
	private String bookingCode;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BookingStatus status;


	@Override
	public String toString() {
		return "Booking [id=" + id + ", user=" + user + ", room_id=" + room_id + ", checkInDate=" + checkInDate
				+ ", checkOutDate=" + checkOutDate + ", totalPrice=" + totalPrice + ", status=" + status + "]";
	}
}
