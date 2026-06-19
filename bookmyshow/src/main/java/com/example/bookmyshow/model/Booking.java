package com.example.bookmyshow.model;

import com.example.bookmyshow.enums.BookingStatus;
import lombok.Data;

import java.util.List;

@Data
public class Booking {

    String bookingId;
    String showId;
    String screenId;
    String theatreId;
    String movieId;
    List<SeatInput> seatList;
    double totalAmount;
    BookingStatus bookingStatus;
    String userId;
}
