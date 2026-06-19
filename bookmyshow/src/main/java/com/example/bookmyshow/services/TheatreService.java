package com.example.bookmyshow.services;

import com.example.bookmyshow.factory.SeatFactory;
import com.example.bookmyshow.model.Screen;
import com.example.bookmyshow.model.Seat;
import com.example.bookmyshow.model.SeatInput;
import com.example.bookmyshow.model.Theatre;
import com.example.bookmyshow.repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TheatreService {

    private final TheatreRepository theatreRepository;
    private final SeatFactory seatFactory;

    public TheatreService(TheatreRepository theatreRepository, SeatFactory seatFactory) {
        this.theatreRepository = theatreRepository;
        this.seatFactory = seatFactory;
    }

    public Theatre addTheatre(Theatre theater){
        return theatreRepository.addTheatre(theater);
    }

    public Theatre getTheatreById(String theatreId){
        return theatreRepository.getTheatreById(theatreId);
    }

    public List<Theatre> getTheatreByCity(String city){
        return theatreRepository.getTheatreByCity(city);
    }

    public Screen addScreen(String theatreId, Screen screen){
        return theatreRepository.addScreen(theatreId,screen);
    }

    public Seat addSeat(String theatreId, String screenId, SeatInput seatInput){

        Seat seat = seatFactory.creatSeat(seatInput);
        return theatreRepository.addSeat(theatreId,screenId,seat);

    }
}
