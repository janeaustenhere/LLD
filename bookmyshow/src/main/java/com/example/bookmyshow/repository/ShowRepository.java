package com.example.bookmyshow.repository;

import com.example.bookmyshow.model.Movie;
import com.example.bookmyshow.model.Show;
import com.example.bookmyshow.model.Theatre;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ShowRepository {

    Map<String, Show> showMap = new HashMap<>();
    private final MovieRepository movieRepository;
    private final TheatreRepository theatreRepository;

    public ShowRepository(MovieRepository movieRepository, TheatreRepository theatreRepository) {
        this.movieRepository = movieRepository;
        this.theatreRepository = theatreRepository;
    }

    public Show addShow(Show show){
        String showId = UUID.randomUUID().toString();
        show.setShowId(showId);
        showMap.put(showId,show);
        return show;
    }

    public Show getShowById(String showId){

        return showMap.get(showId);
    }

    public List<Show> getShowListByMovieNameAndCity(String movieName, String city){

        Movie movie = movieRepository.getMovieByName(movieName).getFirst();
        List<Theatre> theatreList = theatreRepository.getTheatreByCity(city);

        return showMap.values().stream()
                .filter(show -> show.getMovieId().equals(movie.getMovieId()))
                .filter(show -> theatreList.contains(show.getTheatreId()))
                .toList();


    }

    public List<Show> getAllShowList(){

        return showMap.values().stream().toList();

    }
}
