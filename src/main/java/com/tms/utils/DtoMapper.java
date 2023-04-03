package com.tms.utils;

import com.tms.domain.Movie;
import com.tms.domain.User;
import com.tms.domain.dto.MovieHibernateDto;
import com.tms.domain.dto.UserHibernateDto;

import java.util.HashSet;

public class DtoMapper {

    public static UserHibernateDto fromUserToUserHibernateDto(User user) {
        UserHibernateDto userHibernateDto = new UserHibernateDto();
        userHibernateDto.setId(user.getId());
        userHibernateDto.setFirstName(user.getFirstName());
        userHibernateDto.setLastName(user.getLastName());
        userHibernateDto.setLogin(user.getLogin());
        userHibernateDto.setPassword(user.getPassword());
        userHibernateDto.setCreated(user.getCreated());
        userHibernateDto.setChanged(user.getChanged());
        userHibernateDto.setEmail(user.getEmail());
        userHibernateDto.setBirthdate(user.getBirthdate());
        userHibernateDto.setDeleted(user.isDeleted());
        userHibernateDto.setTelephoneNumber(user.getTelephoneNumber());
        //userHibernateDto.setMovieList(user.getMovieList());
        return userHibernateDto;
    }

    public static User fromHibernateUserDtoToUser(UserHibernateDto userHibernateDto) {
        User user = new User();
        user.setId(userHibernateDto.getId());
        user.setFirstName(userHibernateDto.getFirstName());
        user.setLastName(userHibernateDto.getLastName());
        user.setLogin(userHibernateDto.getLogin());
        user.setPassword(userHibernateDto.getPassword());
        user.setCreated(userHibernateDto.getCreated());
        user.setChanged(userHibernateDto.getChanged());
        user.setEmail(userHibernateDto.getEmail());
        user.setBirthdate(userHibernateDto.getBirthdate());
        user.setDeleted(userHibernateDto.isDeleted());
        user.setTelephoneNumber(userHibernateDto.getTelephoneNumber());

        HashSet<Movie> listMovie = new HashSet<>();
        for (MovieHibernateDto mhd : userHibernateDto.getMovieList()) {
            Movie movie = new Movie();
            movie.setId(mhd.getId());
            movie.setMovieName(mhd.getMovieName());
            movie.setDescription(mhd.getDescription());
            movie.setGenre(mhd.getGenre());
            movie.setYear(mhd.getYear());
            movie.setRating(mhd.getRating());
            listMovie.add(movie);
        }
          user.setMovieList(listMovie);
        return user;
    }

    public static Movie fromMovieHibernateDtoToMovie(MovieHibernateDto movieHibernateDto) {
        Movie movie = new Movie();
        movie.setId(movieHibernateDto.getId());
        movie.setMovieName(movieHibernateDto.getMovieName());
        movie.setDescription(movieHibernateDto.getDescription());
        movie.setGenre(movieHibernateDto.getGenre());
        movie.setYear(movieHibernateDto.getYear());
        movie.setRating(movieHibernateDto.getRating());
        movie.setDescription(movieHibernateDto.getDescription());

        HashSet<User> listUser = new HashSet<>();
        for (UserHibernateDto uhd : movieHibernateDto.getUserList()) {
            User user = new User();
            user.setId(uhd.getId());
            user.setFirstName(uhd.getFirstName());
            user.setLastName(uhd.getLastName());
            user.setLogin(uhd.getLogin());
            user.setPassword(uhd.getPassword());
            user.setCreated(uhd.getCreated());
            user.setChanged(uhd.getChanged());
            user.setEmail(uhd.getEmail());
            user.setBirthdate(uhd.getBirthdate());
            user.setDeleted(uhd.isDeleted());
            user.setTelephoneNumber(uhd.getTelephoneNumber());
            listUser.add(user);
        }
        movie.setUserList(listUser);
        return movie;
    }
}
