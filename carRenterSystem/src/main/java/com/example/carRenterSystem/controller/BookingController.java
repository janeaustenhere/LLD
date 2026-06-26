package com.example.carRenterSystem.controller;


import com.example.carRenterSystem.model.Booking;
import com.example.carRenterSystem.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/createBooking")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking){
        Booking createdBooking = null;
        try {
            createdBooking =  bookingService.createBooking(booking);
        } catch (Exception e) {
           return  ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(createdBooking);
        }

       return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }

    @PostMapping("/updateBooking/{bookingId}")
    public ResponseEntity<String> updatedBooking(@PathVariable String bookingId,
                                                 @RequestParam String bookingStatus){
        try {
            bookingService.updateBooking(bookingId, bookingStatus);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Not updated");
        }

        return ResponseEntity.status(HttpStatus.OK).body("booking updated");
    }

    @GetMapping("/getBookingDetails/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable String bookingId){

        Booking bookingDetails = null;

        try{
            bookingDetails = bookingService.getBookingDetailsById(bookingId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bookingDetails);
        }

        return ResponseEntity.status(HttpStatus.OK).body(bookingDetails);
    }


}
