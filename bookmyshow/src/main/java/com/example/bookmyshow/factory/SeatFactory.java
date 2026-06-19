package com.example.bookmyshow.factory;

import com.example.bookmyshow.enums.SeatType;
import com.example.bookmyshow.model.ReclinerSeat;
import com.example.bookmyshow.model.RegularSeat;
import com.example.bookmyshow.model.Seat;
import com.example.bookmyshow.model.SeatInput;
import org.springframework.stereotype.Component;

@Component
public class SeatFactory {

    public Seat creatSeat(SeatInput seatInput){

        return switch (seatInput.getSeatType()) {
            case SeatType.REGULAR -> new RegularSeat(seatInput);
            case SeatType.RECLINER -> new ReclinerSeat(seatInput);
        };

    }
}
