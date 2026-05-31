package com.ota.app.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotels")
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String address;
	
	@Column(length = 1000)
	private String description;
	
	private Integer stars;
	
	private String phoneNumber;
	
	@Column(nullable = false, unique = true)
	private String email;

	@OneToMany(mappedBy = "hotel")
	@JsonIgnore	
	private List<Booking> bookings;
	
	@OneToMany(mappedBy = "hotel")
	@JsonIgnore
	private List<Room> rooms;
	
	private String imageName;
	
	private String imageType;

	@Lob // indicates that this field should be stored as a large object in the database, which is suitable for storing large binary data like images.
	private byte[] imageData;

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", city=" + city + ", address=" + address + ", description="
				+ description + ", stars=" + stars + ", phoneNumber=" + phoneNumber + ", email=" + email + ", rooms="
				+ rooms + "]";
	}
}
