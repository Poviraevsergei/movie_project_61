package com.tms.service;

import com.tms.domain.Movie;
import com.tms.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class MovieService {

    MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie getMovieById(int id) {
        return movieRepository.getMovieById(id);
    }

    public boolean createMovie(String movieName, int year, String genre, Double rating, String description) {
        return movieRepository.createMovie(movieName, year, genre, rating, description);
    }

    public boolean updateMovie(int id, String movieName, int year, String genre, Double rating, String description) {
        return updateMovie(id, movieName, year, genre, rating, description);
    }

    public boolean deleteMovie(int id) {
        return movieRepository.deleteMovie(id);
    }
}
