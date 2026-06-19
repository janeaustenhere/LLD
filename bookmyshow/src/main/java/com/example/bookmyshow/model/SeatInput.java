package com.example.bookmyshow.model;

import com.example.bookmyshow.enums.SeatType;
import lombok.Data;

@Data
public class SeatInput {

    String seatId;
    SeatType seatType;
    double price;
}
