package com.example.bookmyshow.controller;

import com.example.bookmyshow.model.Show;
import com.example.bookmyshow.services.ShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @PostMapping("/addShow")
    public ResponseEntity<Show> addShow(@RequestBody Show show){
        Show addedShow = null;
        try{
            addedShow = showService.addShow(show);
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(addedShow);
    }

    @GetMapping("/getShowDetails/{showId}")
    public ResponseEntity<Show> getShowDetails(@PathVariable String showId){

        Show show = null;
        try{
            show = showService.getShowById(showId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(show);
    }

    @GetMapping("/getShowListByMovieNameAndCity")
    public ResponseEntity<List<Show>> getShowListByMovieNameAndCity(@RequestParam String movieName,
                                                                    @RequestParam String city){

        List<Show> showList = new ArrayList<>();
        try{
            showList = showService.getShowListByMovieNameAndCity(movieName,city);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(showList);
        }

        return ResponseEntity.status(HttpStatus.OK).body(showList);

    }

    @GetMapping("/getAllShows")
    public ResponseEntity<List<Show>> getAllShows(){

        List<Show> showList = new ArrayList<>();
        try{
            showList = showService.getAllShowList();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(showList);
        }

        return ResponseEntity.status(HttpStatus.OK).body(showList);

    }
}
