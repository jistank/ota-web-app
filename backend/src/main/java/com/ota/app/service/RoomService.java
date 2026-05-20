package com.ota.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ota.app.model.Hotel;
import com.ota.app.model.Room;
import com.ota.app.model.RoomType;
import com.ota.app.repo.RoomRepo;
import java.util.List;

@Service
public class RoomService {
    
    @Autowired
    private HotelService hotelService;
	  
    @Autowired
    private RoomRepo roomRepository;
    
    // Get all rooms for a specific hotel
    public List<Room> getRoomsByHotelId(Long hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }
    
    // Get rooms by type
    public List<Room> getRoomsByType(RoomType roomType) {
        return roomRepository.findByRoomType(roomType);
    }
    
    public List<Room> getRoomsByMaxGuests(Integer maxGuests) {
		return roomRepository.findByMaxGuests(maxGuests);
	}
    
    public List<Room> getRoomsByPriceLessThanEqual(Double price) {
    	return roomRepository.findByPricePerNightLessThanEqual(price);
    }
    
    // Create new room
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }
    
    // Update room
    public Room updateRoom(Long id, Room room) {
        if(!roomRepository.existsById(id))
            return null;
        
        room.setId(id);
        return roomRepository.save(room);
    }
    
    // Delete room
    public boolean deleteRoom(Long id) {
        if(!roomRepository.existsById(id))
            return false;
        
        roomRepository.deleteById(id);
        return true;
    }

    public Room createRoomForHotel(Long hotelId, Room room) {
        Hotel hotel = hotelService.getHotelById(hotelId);
        
        if(hotel == null) {
            throw new RuntimeException("Hotel not found with id: " + hotelId);
        }
        
        List<Room> existingRooms = roomRepository.findByHotelId(hotelId);
        boolean roomExists = existingRooms.stream()
            .anyMatch(r -> r.getRoomNumber().equals(room.getRoomNumber()));
        
        if(roomExists) {
            throw new RuntimeException("Room " + room.getRoomNumber() + " already exists for this hotel");
        }
        
        room.setHotel(hotel);
        return roomRepository.save(room);
    }
}











