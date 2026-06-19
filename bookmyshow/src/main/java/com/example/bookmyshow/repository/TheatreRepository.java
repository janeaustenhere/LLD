package com.example.bookmyshow.repository;

import com.example.bookmyshow.model.Screen;
import com.example.bookmyshow.model.Seat;
import com.example.bookmyshow.model.Theatre;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class TheatreRepository {

    Map<String, Theatre> theaterMap = new HashMap<>();

    public Theatre addTheatre(Theatre theater){
        String theatreId = UUID.randomUUID().toString();
        theater.setTheaterId(theatreId);
        theaterMap.put(theatreId,theater);
        return theater;
    }

    public Theatre getTheatreById(String theatreId){
        return theaterMap.get(theatreId);
    }

    public List<Theatre> getTheatreByCity(String city){
        return theaterMap.values().stream()
                .filter(theater -> theater.getAddress().getCity().equals(city))
                .collect(Collectors.toList());
    }

    public Screen addScreen(String theatreId, Screen screen){
        Theatre theatre = theaterMap.get(theatreId);
        theatre.getScreenMap().put(screen.getScreenId(), screen);
        return screen;
    }

    public Seat addSeat(String theatreId, String screenId, Seat seat){

        Theatre theatre = theaterMap.get(theatreId);
        Screen screen = theatre.getScreenMap().get(screenId);
        screen.getSeatMap().put(seat.getSeatId(), seat);
        return seat;
    }


}
