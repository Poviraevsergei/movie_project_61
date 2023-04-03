package com.tms.service;

import com.tms.domain.Movie;
import com.tms.domain.User;
import com.tms.domain.dto.UserHibernateDto;
import com.tms.repository.UserRepository;
import com.tms.utils.DtoMapper;
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
        ArrayList<UserHibernateDto> listUserDto = userRepository.getAllUsers();
        ArrayList<User> resultUserlist = new ArrayList<>();
        for (UserHibernateDto u : listUserDto) {
            //DTOMapper
        }
        return resultUserlist;
    }

    public User getUserById(int id) {
        UserHibernateDto userDto = userRepository.getUserById(id);
        return DtoMapper.fromHibernateUserDtoToUser(userDto);
    }

    public boolean createUser(User user) {
        return userRepository.createUser(DtoMapper.fromUserToUserHibernateDto(user));
    }

    public boolean updateUser(User user) {
        return userRepository.updateUser(DtoMapper.fromUserToUserHibernateDto(user));
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