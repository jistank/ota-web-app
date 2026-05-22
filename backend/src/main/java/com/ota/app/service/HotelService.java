package com.ota.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ota.app.model.Hotel;
import com.ota.app.repo.HotelRepo;

@Service
public class HotelService {
	
	@Autowired
	private HotelRepo hotelRepo;
	
	public List<Hotel> getAllHotel() {
		return hotelRepo.findAll();
	}

    public Hotel getHotelById(Long id) { 
        return hotelRepo.findById(id).orElse(null);
    }

	public Hotel createOrUpdateHotel(Hotel hotel, MultipartFile image) throws IOException {
		hotel.setImageName(image.getOriginalFilename());
		hotel.setImageType(image.getContentType());
		hotel.setImageData(image.getBytes());
		return hotelRepo.save(hotel);
	}

	public Hotel updateHotel(Long id, Hotel hotel) {
	    if(!hotelRepo.existsById(id))
	        return null;
	    
	    hotel.setId(id);
	    return hotelRepo.save(hotel);
	}

	public boolean deleteHotel(Long id) {
		if(!hotelRepo.existsById(id))
			return false;
		
		hotelRepo.deleteById(id);
		return true;
	}

	public List<Hotel> findByCity(String city) {
		return hotelRepo.findByCity(city);
	}

	public List<Hotel> findByNameContainingIgnoreCase(String name) {
	    return hotelRepo.findByNameContainingIgnoreCase(name);
	}
	
}
