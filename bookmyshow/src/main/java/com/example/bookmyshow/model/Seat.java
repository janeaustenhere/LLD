package com.example.bookmyshow.model;

import com.example.bookmyshow.enums.SeatStatus;
import com.example.bookmyshow.enums.SeatType;
import lombok.Data;

@Data
public abstract class Seat {

    String seatId;
    SeatType seatType;
    double price;
    SeatStatus seatStatus = SeatStatus.AVAILABLE;

    public Seat(String seatId, SeatType seatType, double price) {
        this.seatId = seatId;
        this.seatType = seatType;
        this.price = price;
    }

    public abstract SeatType getType();
}
