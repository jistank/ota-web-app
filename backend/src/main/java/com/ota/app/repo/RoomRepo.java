package com.ota.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ota.app.model.Room;
import com.ota.app.model.RoomType;

import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> { // Long is the type of the primary key (id) in Room entity
    
    // Find all rooms for a specific hotel
    List<Room> findByHotelId(Long hotelId);
    
    // Find rooms by type
    List<Room> findByRoomType(RoomType roomType);
    
    List<Room> findByMaxGuests(Integer maxGuests); 
    
    List<Room> findByPricePerNightLessThanEqual(Double price);
}