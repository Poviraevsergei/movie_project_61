package com.tms.service;

import com.tms.domain.Movie;

import java.sql.*;
import java.util.ArrayList;

public class GiveAllMoviesFromUserService {

    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Movie> getMovies(int id) {
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
}
