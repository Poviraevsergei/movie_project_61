package com.tms.repository;

import com.tms.domain.Movie;
import com.tms.domain.User;
import com.tms.utils.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Repository
public class UserRepository {

    public JdbcTemplate template;

    @Autowired
    public UserRepository(JdbcTemplate template) {
        this.template = template;
    }

    public ArrayList<User> getAllUsers() {
        return (ArrayList<User>) template.query("SELECT * FROM user_table", new UserMapper());
    }

    public User getUserById(int id) {
        return template.queryForObject("SELECT * FROM user_table WHERE id=?", new UserMapper(), id);
    }

    public boolean createUser(User user) {
        int result = template.update("INSERT INTO user_table (id, first_name, last_name, login, password, created, changed,email,birthday_date,is_deleted, telephone) " +
                "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT, ?)", new Object[]{user.getFirstName(), user.getLastName(), user.getLogin(),
                user.getPassword(), new Date((new java.util.Date()).getTime()), new Date((new java.util.Date()).getTime()),
                user.getEmail(), new Date((new java.util.Date()).getTime()), user.getTelephoneNumber()});
        return result == 1;
    }

    public boolean updateUser(User user) {
        int result = template.update("UPDATE user_table SET first_name=?, last_name=?, login=?, password=?, changed=?,email=?,birthday_date=?, telephone=? WHERE id=?", new Object[]{user.getFirstName(), user.getLastName(), user.getLogin(),
                user.getPassword(), new Date((new java.util.Date()).getTime()),
                user.getEmail(), new Date((new java.util.Date()).getTime()), user.getTelephoneNumber(), user.getId()});
        return result == 1;
    }

    public boolean deleteUser(int id) {
        int result = template.update("UPDATE user_table SET is_deleted=true WHERE id=?", id);
        return result == 1;
    }

    public ArrayList<Movie> getMoviesForSingleUser(int id) {
        ArrayList<Movie> movieList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {
            PreparedStatement statement = connection.prepareStatement("SELECT m.id,m.movie_name,m.year,m.genre,m.rating,m.description FROM l_user_movie JOIN movie_table as m ON l_user_movie.movie_id = m.id WHERE l_user_movie.user_id=?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setMovieName(resultSet.getString("movie_name"));
                movie.setYear(resultSet.getInt("year"));
                movie.setGenre(resultSet.getString("genre"));
                movie.setRating(resultSet.getDouble("rating"));
                movie.setDescription(resultSet.getString("description"));
                movieList.add(movie);
            }
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return movieList;
    }


    public boolean addMovieToUser(int userId, int movieId) {
        int result = template.update("INSERT INTO l_user_movie (id, user_id, movie_id) " +
                "VALUES (DEFAULT, ?, ?)", new Object[]{userId, movieId});
        return result == 1;
    }
}
