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
import jakarta.persistence.*;

@Entity // tells JPA this is a database table
@Table(name= "bookings") // table name in PostgreSQL
public class Booking {

	@Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PostgreSQL auto-generates the ID (1, 2, 3...)
	private Long id;
	
	@ManyToOne // bookings belong to one user
	@JoinColumn(name = "user_id", nullable = false) // creates column "user_id" in database
	@JsonIgnore // <============== prevents infinite recursion when serializing to JSON
	private User user; // is needed the obj user
	
	@ManyToOne
	@JoinColumn(name = "hotel_id", nullable = false)
	private Hotel hotel;
	
	@ManyToOne
	@JoinColumn(name = "room_number", nullable = false)
	private Room room_id;
	
	@Column(nullable = false)
	private LocalDate bookingDate; // <- when the booking was made
	
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

	


	public Booking() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Room getRoom_id() {
		return room_id;
	}

	public void setRoom_id(Room room_id) {
		this.room_id = room_id;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public void setBookingDate(LocalDate now) {
		this.bookingDate = now;
	}

	public void setBookingCode(String upperCase) {
		this.bookingCode = upperCase;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", user=" + user + ", room_id=" + room_id + ", checkInDate=" + checkInDate
				+ ", checkOutDate=" + checkOutDate + ", totalPrice=" + totalPrice + ", status=" + status + "]";
	}


	
	
}
