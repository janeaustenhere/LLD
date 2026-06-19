package com.example.bookmyshow.repository;

import com.example.bookmyshow.enums.BookingStatus;
import com.example.bookmyshow.model.Booking;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class BookingRepository {

    Map<String, Booking> bookingMap = new HashMap<>();

    public Booking createBooking(Booking booking){

        String bookingId = UUID.randomUUID().toString();
        booking.setBookingId(bookingId);
        booking.setBookingStatus(BookingStatus.CREATED);
        bookingMap.put(bookingId,booking);
        return booking;
    }

    public Booking updateBooking(String bookingId, BookingStatus bookingStatus){
        Booking booking = bookingMap.get(bookingId);
        booking.setBookingStatus(bookingStatus);
        return booking;
    }

    public Booking getBookingDetailsById(String bookingId){
        return bookingMap.get(bookingId);
    }
}
