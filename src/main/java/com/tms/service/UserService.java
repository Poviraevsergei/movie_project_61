package com.tms.service;

import com.tms.domain.Movie;
import com.tms.domain.User;
import com.tms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ArrayList<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public boolean createUser(User user) {
        return userRepository.createUser(user);
    }

    public boolean updateUser(int id, String firstName, String lastName, String login, String password, String email, String telephoneNumber) {
        return userRepository.updateUser(id, firstName, lastName, login, password, email, telephoneNumber);
    }

    public boolean deleteUser(int id) {
        return userRepository.deleteUser(id);
    }

    public ArrayList<Movie> getMoviesForSingleUser(int id) {
        return userRepository.getMoviesForSingleUser(id);
    }

    public boolean addMovieToUser(int userId, int movieId) {
        return userRepository.addMovieToUser(userId, movieId);
    }
}