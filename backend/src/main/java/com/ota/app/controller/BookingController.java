package com.ota.app.controller;

import com.ota.app.model.Booking;
import com.ota.app.model.BookingStatus;
import com.ota.app.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@CrossOrigin

public class BookingController {
	
    @Autowired
    private BookingService bookingService;
    
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        if (booking != null)
            return new ResponseEntity<>(booking, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/code/{code}")
    public ResponseEntity<Booking> getBookingByCode(@PathVariable String code) {
        Booking booking = bookingService.getBookingByCode(code);
        if (booking != null)
            return new ResponseEntity<>(booking, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(bookingService.getBookingsByUser(userId), HttpStatus.OK);
    }
    
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Booking>> getBookingsByHotel(@PathVariable Long hotelId) {
        return new ResponseEntity<>(bookingService.getBookingsByHotel(hotelId), HttpStatus.OK);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Booking>> getBookingsByStatus(@PathVariable BookingStatus status) {
        return new ResponseEntity<>(bookingService.getBookingsByStatus(status), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> createBooking(
            @RequestParam Long userId,
            @RequestParam Long hotelId,
            @RequestParam Long roomId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut) {
        try {
            Booking created = bookingService.createBooking(userId, hotelId, roomId, checkIn, checkOut);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PatchMapping("/{id}/status")
    public ResponseEntity<Booking> updateBookingStatus(
            @PathVariable Long id,
            @RequestParam BookingStatus status) {
        Booking updated = bookingService.updateBookingStatus(id, status);
        if (updated != null)
            return new ResponseEntity<>(updated, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        boolean deleted = bookingService.deleteBooking(id);
        if (deleted)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}














