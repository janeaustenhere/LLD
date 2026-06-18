package com.example.carRenterSystem.repository;


import com.example.carRenterSystem.enums.BookingStatus;
import com.example.carRenterSystem.model.Booking;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class BookingRepository {

    Map<String, Booking> bookingMap = new HashMap<>();

    public Booking createBooking(Booking booking){

        String bookingId = UUID.randomUUID().toString();
        booking.setBookingId(bookingId);
        bookingMap.put(bookingId,booking);
        return booking;
    }

    public void updateBookingStatus(String bookingId, String bookingStatus){

        if(!bookingMap.containsKey(bookingId)){

            throw new RuntimeException("booking id not present in database");
        }

        Booking booking = bookingMap.get(bookingId);
        booking.setBookingStatus(BookingStatus.valueOf(bookingStatus));

    }

    public Booking getBookingDetailsById(String bookingId){

        if(!bookingMap.containsKey(bookingId)){
            throw  new RuntimeException("Booking id not present in database");
        }

        return bookingMap.get(bookingId);
    }

    public List<Booking> getListOfAllBokkings(){

        return bookingMap.values().stream().toList();
    }




}
