package com.tms.controller;

import com.tms.domain.Movie;
import com.tms.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @PostMapping
    public String createMovie(
            @RequestParam String movieName,
            @RequestParam int year,
            @RequestParam String genre,
            @RequestParam Double rating,
            @RequestParam String description
    ) {
        log.info("doing /movie Post method!");
        boolean result = movieService.createMovie(movieName, year, genre, rating, description);
        return result ? "successfully" : "unsuccessfully";
    }

    @PutMapping
    public String updateMovie(
            @RequestParam int id,
            @RequestParam String movieName,
            @RequestParam int year,
            @RequestParam String genre,
            @RequestParam Double rating,
            @RequestParam String description
    ) {
        boolean result = movieService.updateMovie(id, movieName, year, genre, rating, description);
        return result ? "successfully" : "unsuccessfully";
    }

    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable int id) {
        log.info("doing /movie Delete method!");
        boolean result = movieService.deleteMovie(id);
        return result ? "successfully" : "unsuccessfully";
    }
}