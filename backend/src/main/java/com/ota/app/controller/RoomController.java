package com.ota.app.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.ota.app.model.Room;
import com.ota.app.model.RoomType;
import com.ota.app.service.RoomService;

@RestController // indicates that this class is a REST controller, which means it will handle HTTP requests and return responses in a RESTful manner (JSON)
@RequestMapping("/rooms") // base path for all room-related endpoints
@CrossOrigin // allows cross-origin requests from any domain, which is useful for frontend applications hosted on different domains to access this API without CORS issues
public class RoomController {

	
    @Autowired
    private RoomService roomService;
    
    // GET all rooms for a specific hotel
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Room>> getRoomsByHotel(@PathVariable Long hotelId) {
        List<Room> rooms = roomService.getRoomsByHotelId(hotelId);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
    
    // GET rooms by type
    @GetMapping("/type/{roomType}")
    public ResponseEntity<List<Room>> getRoomsByType(@PathVariable RoomType roomType) {
        List<Room> rooms = roomService.getRoomsByType(roomType);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<Room>> getRoomsByMaxGuests(@PathVariable Integer maxGuests) {
		List<Room> rooms = roomService.getRoomsByMaxGuests(maxGuests);
		return new ResponseEntity<>(rooms, HttpStatus.OK);
	}
    
    @GetMapping("/price/{price}")
    public ResponseEntity<List<Room>> getRoomsByPrice(@PathVariable Double price) {
    	List<Room> rooms = roomService.getRoomsByPriceLessThanEqual(price);
		return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
    
    // POST - create new room
    @PostMapping("/hotel/{hotelId}")
    public ResponseEntity<Room> createRoomForHotel(@PathVariable Long hotelId, @RequestBody Room room) {
        Room created = roomService.createRoomForHotel(hotelId, room);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    // PUT - update room
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room room) {
        Room updated = roomService.updateRoom(id, room);
        
        if(updated != null)
            return new ResponseEntity<>(updated, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // DELETE room
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        boolean deleted = roomService.deleteRoom(id);
        
        if(deleted)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    
}
