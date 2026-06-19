package com.example.bookmyshow.model;

import com.example.bookmyshow.enums.SeatType;
import lombok.Data;

@Data
public class RegularSeat extends Seat{

    public RegularSeat(SeatInput seatInput){
        super(seatInput.seatId, SeatType.REGULAR, seatInput.getPrice());
    }
    @Override
    public SeatType getType() {
        return SeatType.REGULAR;
    }
}
