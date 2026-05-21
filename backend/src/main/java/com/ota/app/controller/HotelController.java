package com.ota.app.controller;

import java.io.IOException;
import java.security.PublicKey;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ota.app.model.Hotel;
import com.ota.app.service.HotelService;


@RestController
@RequestMapping("/hotels")
@CrossOrigin
public class HotelController {
	
	@Autowired
	private HotelService hotelService;

	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotels() {
		return new ResponseEntity<>(hotelService.getAllHotel(), HttpStatus.OK);
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable("id") Long id) {
        Hotel hotel = hotelService.getHotelById(id);

        if(hotel != null)
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    
    @PostMapping
    public ResponseEntity<?> createHotel(@RequestPart Hotel hotel, @RequestPart MultipartFile image){
        Hotel saved = null;
		try {
			saved = hotelService.createHotel(hotel, image);
			return new ResponseEntity<>(saved, HttpStatus.CREATED);
		} catch (IOException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

/*   
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel saved = hotelService.createHotel(hotel);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
*/   

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        Hotel updated = hotelService.updateHotel(id, hotel);
        
        if(updated != null)
            return new ResponseEntity<>(updated, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        boolean deleted = hotelService.deleteHotel(id);
        
        if(deleted)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/search/city") // http://localhost:8080/hotels/search/city?city=Kuala Lumpur
    public ResponseEntity<List<Hotel>> searchByCity(@RequestParam String city) {
        List<Hotel> hotels = hotelService.findByCity(city);
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }
    
    @GetMapping("/search/name") // http://localhost:8080/hotels/search/name?name=luxury
    public ResponseEntity<List<Hotel>> searchHotelsByName(@RequestParam String name) {
        List<Hotel> hotels = hotelService.findByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(hotels);
    }
}
















