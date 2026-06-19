package com.example.bookmyshow.services;

import com.example.bookmyshow.model.Movie;
import com.example.bookmyshow.model.Show;
import com.example.bookmyshow.model.Theatre;
import com.example.bookmyshow.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShowService {

    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public Show addShow(Show show){
      return showRepository.addShow(show);
    }

    public Show getShowById(String showId){
       return showRepository.getShowById(showId);
    }

    public List<Show> getShowListByMovieNameAndCity(String movieName, String city){

        return showRepository.getShowListByMovieNameAndCity(movieName,city);


    }

    public List<Show> getAllShowList(){

        return showRepository.getAllShowList();

    }

}
