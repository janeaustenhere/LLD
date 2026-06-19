package com.example.bookmyshow.controller;


import com.example.bookmyshow.model.Screen;
import com.example.bookmyshow.model.SeatInput;
import com.example.bookmyshow.model.Theatre;
import com.example.bookmyshow.services.TheatreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @PostMapping("/addTheatre")
    public ResponseEntity<Theatre> addTheater(@RequestBody Theatre theatre){
        Theatre addedTheatre = null;
        try{
            addedTheatre = theatreService.addTheatre(theatre);
        }catch (Exception ex){
            ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }

        return  ResponseEntity.status(HttpStatus.CREATED).body(addedTheatre);
    }

    @PostMapping("/addScreen/{theatreId}")
    public ResponseEntity<Screen> addScreen(@RequestBody Screen screen,
                                            @PathVariable String theatreId){
        Screen addedScreen = null;
        try{
            addedScreen = theatreService.addScreen(theatreId,screen);
        }catch (Exception ex){
            ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }

        return  ResponseEntity.status(HttpStatus.CREATED).body(addedScreen);
    }

    @PostMapping("/addSeat/{theatreId}")
    public ResponseEntity<String> addScreen(@RequestBody SeatInput seatInput,
                                            @PathVariable String theatreId,
                                            @RequestParam String screenId){

        try{
            theatreService.addSeat(theatreId,screenId, seatInput);
        }catch (Exception ex){
            ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }

        return  ResponseEntity.status(HttpStatus.CREATED).body("Seat added");
    }

    @GetMapping("/getTheatreById/{theatreId}")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable String theatreId){
        Theatre theatre = null;
        try{
            theatre = theatreService.getTheatreById(theatreId);
        }catch (Exception ex){
            ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }

        return  ResponseEntity.status(HttpStatus.FOUND).body(theatre);
    }

    @GetMapping("/getTheatreListByCity/{city}")
    public ResponseEntity<List<Theatre>> getTheatreListByCity(@PathVariable String city){
        List<Theatre> theatreList = new ArrayList<>();
        try{
            theatreList = theatreService.getTheatreByCity(city);
        }catch (Exception ex){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return  ResponseEntity.status(HttpStatus.FOUND).body(theatreList);
    }


}
