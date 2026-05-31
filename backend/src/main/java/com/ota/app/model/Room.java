package com.ota.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // indicates that this class is a JPA entity and will be mapped to a database table
@Table(name = "rooms") // specifies the name of the database table to be used for mapping. If not specified, it defaults to the class name.
public class Room {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
    @ManyToOne // indicates a many-to-one relationship between Room and Hotel. Many rooms can belong to one hotel.
    @JoinColumn(name = "hotel_id", nullable = false) // specifies the foreign key column (hotel_id) in the rooms table that references the primary key of the hotels table. nullable = false means this relationship is required.
    @JsonIgnore // prevents the hotel field from being serialized into JSON, which helps avoid circular references when converting Room objects to JSON.
	private Hotel hotel;
    
    @Enumerated(EnumType.STRING) // indicates that the roomType field is an enum and should be stored as a string in the database. The actual value stored will be the name of the enum constant (e.g., "SINGLE", "DOUBLE", "SUITE").
    @Column(nullable = false) // specifies that the roomType column cannot be null in the database, meaning every room must have a type defined.
	private RoomType roomType; 
    
    @Column(nullable = false)
	private Double pricePerNight;
    
    @Column(nullable = false)
	private Integer maxGuests;
    
    @Column(name = "room_number", nullable = false)
	private String roomNumber;
	
	@Override
	public String toString() {
		return "Room [id=" + id + ", hotel=" + hotel + ", roomType=" + roomType + ", pricePerNight=" + pricePerNight
				+ ", maxGuests=" + maxGuests + ", roomNumber=" + roomNumber + "]";
	}
}
