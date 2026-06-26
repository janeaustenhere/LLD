package com.example.bookmyshow.controller;


import com.example.bookmyshow.model.Movie;
import com.example.bookmyshow.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/addMovie")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        Movie addedMovie = null;
        try {
            addedMovie = movieService.addMovie(movie);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(addedMovie);
    }

    @GetMapping("/getMovieById/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String movieId){
        Movie movie = null;
        try {
            movie = movieService.getMovieById(movieId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }

    @GetMapping("/getMovieListByName/{movieName}")
    public ResponseEntity<List<Movie>> getMovieListByName(@PathVariable String movieName){
        List<Movie> movieList = new ArrayList<>();
        try {
            movieList = movieService.getMovieByName(movieName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(movieList);
    }

    @GetMapping("/getAllMovies")
    public ResponseEntity<List<Movie>> getMovieList(){
        List<Movie> movieList = new ArrayList<>();
        try {
            movieList = movieService.getAllMovieList();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(movieList);
    }
}
