package com.example.bookmyshow.repository;

import com.example.bookmyshow.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {

    Map<String, Movie> movieMap = new HashMap<>();

    public Movie addMovie(Movie movie){
        String movieId = UUID.randomUUID().toString();
        movie.setMovieId(movieId);
        movieMap.put(movieId,movie);
        return movie;
    }

    public Movie getMovieById(String movieId){
        return movieMap.get(movieId);
    }

    public List<Movie> getMovieByName(String movieName){

        List<Movie> movieList= movieMap.values().stream()
                .filter(movie1 -> movie1.getMovieName().equals(movieName))
                .collect(Collectors.toList());

        return movieList;

    }

    public List<Movie> getAllMovieList(){
        return movieMap.values().stream().toList();
    }
}
