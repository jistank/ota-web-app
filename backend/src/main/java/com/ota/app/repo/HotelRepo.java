package com.ota.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ota.app.model.Hotel;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> { // Long is the type of the primary key (id) in Hotel entity
    List<Hotel> findByCity(String city); // Find hotels by city

	List<Hotel> findByNameContainingIgnoreCase(String name);
	
}
