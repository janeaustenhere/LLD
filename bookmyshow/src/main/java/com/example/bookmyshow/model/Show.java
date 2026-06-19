package com.example.bookmyshow.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Show {

    String showId;
    String movieId;
    String theatreId;
    String screenId;
    LocalDateTime startTime;
}
