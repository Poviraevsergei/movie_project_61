package com.tms.controller;

import com.tms.domain.Movie;
import com.tms.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movie")
public class MovieController {

    MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @GetMapping("/{id}")
    public String getMovie(@PathVariable int id, Model model) {
        log.info("doing /movie Get method!");
        Movie movie = movieService.getMovieById(id);
        if (movie.getId() == 0) {
            log.warn("Movie is not found! Trying find id=" + id);
        }
        model.addAttribute("movie", movie);
        return "singleMovie";
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