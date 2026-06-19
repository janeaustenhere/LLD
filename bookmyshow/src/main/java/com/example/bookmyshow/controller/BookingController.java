package com.example.bookmyshow.controller;

import com.example.bookmyshow.model.Booking;
import com.example.bookmyshow.services.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/createBooking")
    public ResponseEntity<Booking> createBooking(Booking booking){
        Booking createBooking = null;
        try{
            createBooking =   bookingService.createBooking(booking);
        } catch (Exception e) {
           ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(createBooking);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(createBooking);
    }

    @PostMapping("/confirmBooking")
    public ResponseEntity<Booking> confirmBooking(Booking booking){
        Booking confirmedBooking = null;
        try{
            confirmedBooking = bookingService.confirmBooking(booking);
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(confirmedBooking);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(confirmedBooking);
    }

    @GetMapping("/getBookingDetails/{bookingId}")
    public ResponseEntity<Booking> getBookingDetails(String bookingId){
        Booking booking = null;
        try{
            booking = bookingService.getBookingDetails(bookingId);
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(booking);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(booking);
    }
}
