package com.tms.service;

import com.tms.domain.Movie;
import com.tms.domain.dto.MovieHibernateDto;
import com.tms.repository.MovieRepository;
import com.tms.utils.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie getMovieById(int id) {
        MovieHibernateDto movieHibernateDto = movieRepository.getMovieById(id);
        return DtoMapper.fromMovieHibernateDtoToMovie(movieHibernateDto);
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
