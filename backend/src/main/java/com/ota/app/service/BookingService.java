package com.ota.app.service;
import com.ota.app.model.*;
import com.ota.app.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private UserService userService;
    
    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepo.findById(id).orElse(null);
    }

    public Booking getBookingByCode(String code) {
        return bookingRepo.findByBookingCode(code).orElse(null);
    }

    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepo.findByUserId(userId);
    }

    public List<Booking> getBookingsByHotel(Long hotelId) {
        return bookingRepo.findByHotelId(hotelId);
    }

    public List<Booking> getBookingsByStatus(BookingStatus status) {
        return bookingRepo.findByStatus(status);
    }

    public Booking createBooking(Long userId, Long hotelId, Long roomId, LocalDate checkIn, LocalDate checkOut) {

        User user = userService.getUserById(userId);
        if (user == null) throw new RuntimeException("User not found");

        Hotel hotel = hotelService.getHotelById(hotelId);
        if (hotel == null) throw new RuntimeException("Hotel not found");

        Room room = (Room) roomService.getRoomsByHotelId(roomId); // < --------------- to fix, it is necessaire a method to book a specific room, not all rooms
        if (room == null) throw new RuntimeException("Room not found");

        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        double totalPrice = nights * room.getPricePerNight();

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setHotel(hotel);
        booking.setRoom_id(room);
        booking.setCheckInDate(checkIn);
        booking.setCheckOutDate(checkOut);
        booking.setBookingDate(LocalDate.now());
        booking.setTotalPrice(totalPrice);
        booking.setBookingCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        booking.setStatus(BookingStatus.PENDING);

        return bookingRepo.save(booking);
    }

    public Booking updateBookingStatus(Long id, BookingStatus status) {
        Booking booking = bookingRepo.findById(id).orElse(null);
        if (booking == null) return null;
        booking.setStatus(status);
        return bookingRepo.save(booking);
    }

    public boolean deleteBooking(Long id) {
        if (!bookingRepo.existsById(id)) return false;
        bookingRepo.deleteById(id);
        return true;
    }
}