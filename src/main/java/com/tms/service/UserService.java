package com.tms.service;

import com.tms.domain.Movie;
import com.tms.domain.User;
import com.tms.domain.request.UserRegistrationRequest;
import com.tms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final String USER_ROLE = "USER";
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ArrayList<User> getAllUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }

    public User getUserById(int id) {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName(); // Who authenticated!
        return userRepository.findById(id).orElse(new User());
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        user.setCreated(new Date(System.currentTimeMillis()));
        return userRepository.saveAndFlush(user);
    }

    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }

    public Optional<User> findUserByLastName(String ln) {
        return userRepository.findUserByLastName(ln);
    }

    public ArrayList<Movie> getMoviesForSingleUser(int id) {
        //    return userRepository.getMoviesForSingleUser(id);
        return new ArrayList<>(); //TODO: change
    }

    public void addMovieToUser(int userId, int movieId) {
        //   userRepository.addMovieToUser(userId, movieId);

    }

    public Boolean userRegistration(UserRegistrationRequest userRegistrationRequest) {
        User user = new User();
        user.setFirstName(userRegistrationRequest.getFirstName());
        user.setLastName(userRegistrationRequest.getLastName());
        user.setEmail(userRegistrationRequest.getEmail());
        user.setLogin(userRegistrationRequest.getLogin());
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        user.setTelephoneNumber(userRegistrationRequest.getTelephoneNumber());
        user.setBirthdate(userRegistrationRequest.getBirthdate());
        user.setCreated(new Date(System.currentTimeMillis()));
        user.setChanged(new Date(System.currentTimeMillis()));
        user.setIsDeleted(false);
        user.setRole(USER_ROLE);

        return userRepository.save(user)!=null;
    }
}