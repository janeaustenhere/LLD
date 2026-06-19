package com.example.bookmyshow.model;

import lombok.Data;

import java.time.Duration;

@Data
public class Movie {

    String movieId;
    String movieName;
    Duration movieDuration;
    String movieShorDescription;
    String genre;
    double rating;

}
