package com.example.bookmyshow.services;

import com.example.bookmyshow.model.Movie;
import com.example.bookmyshow.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie addMovie(Movie movie){
        return movieRepository.addMovie(movie);
    }

    public Movie getMovieById(String movieId){
        return movieRepository.getMovieById(movieId);
    }

    public List<Movie> getMovieByName(String movieName){

        return movieRepository.getMovieByName(movieName);

    }

    public List<Movie> getAllMovieList(){
        return movieRepository.getAllMovieList();
    }


}
