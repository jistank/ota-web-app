package com.ota.app.repo;
import com.ota.app.model.Booking;
import com.ota.app.model.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
    List<Booking> findByHotelId(Long hotelId);
    Optional<Booking> findByBookingCode(String bookingCode);
    List<Booking> findByStatus(BookingStatus status);
}