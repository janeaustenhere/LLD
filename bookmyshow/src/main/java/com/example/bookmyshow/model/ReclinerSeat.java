package com.example.bookmyshow.model;

import com.example.bookmyshow.enums.SeatType;
import lombok.Data;

@Data
public class ReclinerSeat extends Seat{

    public ReclinerSeat(SeatInput seatInput){
        super(seatInput.seatId, SeatType.RECLINER, seatInput.getPrice());
    }
    @Override
    public SeatType getType() {
        return SeatType.RECLINER;
    }
}
