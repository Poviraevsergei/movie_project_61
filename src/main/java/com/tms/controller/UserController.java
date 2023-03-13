package com.tms.controller;

import com.tms.domain.Movie;
import com.tms.domain.User;
import com.tms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

//@Controller
@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ArrayList<User> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getMovies/{id}")
    public ArrayList<Movie> giveAllMoviesForThisUser(@PathVariable int id) {
        return userService.getMoviesForSingleUser(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public void createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o :bindingResult.getAllErrors()){
                log.warn("We have bindingResult error : " + o);
            }
        }
        userService.createUser(user);
    }

    @PostMapping("/addFilm")
    public void addFilm(@RequestParam int userId, @RequestParam int movieId) {
        userService.addMovieToUser(userId, movieId);
    }


    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
