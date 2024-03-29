package com.tms.controller;

import com.tms.domain.Movie;
import com.tms.domain.User;
import com.tms.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Tag(name = "testTag")
    @GetMapping
    public ResponseEntity<ArrayList<User>> getAllUser() {
        ArrayList<User> list = userService.getAllUsers();
        return new ResponseEntity<>(list,(!list.isEmpty())?HttpStatus.OK:HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Он вам отдаст юзера по его id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все супер!"),
            @ApiResponse(responseCode = "400", description = "Не получилось достать по id..."),
            @ApiResponse(responseCode = "500", description = "Ошибка на сервере.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@Parameter(description = "Эта та самая id которую нужно передать") @PathVariable int id) {
        User user = userService.getUserById(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/getMovies/{id}")
    public ResponseEntity<ArrayList<Movie>> giveAllMoviesForThisUser(@PathVariable int id) {
        return new ResponseEntity<>(userService.getMoviesForSingleUser(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o :bindingResult.getAllErrors()){
                log.warn("We have bindingResult error : " + o);
            }
        }
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/addFilm")
    public ResponseEntity<HttpStatus> addFilm(@RequestParam int userId, @RequestParam int movieId) {
        userService.addMovieToUser(userId, movieId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}