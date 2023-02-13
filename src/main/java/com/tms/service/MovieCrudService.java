package com.tms.service;

import com.tms.domain.Movie;

import java.sql.*;

public class MovieCrudService {

    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Movie getMovieById(int id) {
        //брось лог
        Movie movie = new Movie();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM movie_table WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            movie.setId(resultSet.getInt("id"));
            movie.setMovieName(resultSet.getString("movie_name"));
            movie.setYear(resultSet.getInt("year"));
            movie.setGenre(resultSet.getString("genre"));
            movie.setRating(resultSet.getDouble("rating"));
            movie.setDescription(resultSet.getString("description"));
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return movie;
    }

    public boolean createMovie(String movieName, int year, String genre, Double rating, String description) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO movie_table (id, movie_name, year, genre, rating, description) " +
                    "VALUES (DEFAULT , ?, ?, ?, ?, ?)");
            statement.setString(1, movieName);
            statement.setInt(2, year);
            statement.setString(3, genre);
            statement.setDouble(4, rating);
            statement.setString(5, description);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }

    public boolean updateMovie(int id, String movieName, int year, String genre, Double rating, String description) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {

            PreparedStatement statement = connection.prepareStatement("UPDATE movie_table SET movie_name=?, year=?, genre=?, rating=?, description=? WHERE id=?");
            statement.setString(1, movieName);
            statement.setInt(2, year);
            statement.setString(3, genre);
            statement.setDouble(4, rating);
            statement.setString(5, description);
            statement.setInt(6, id);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }

    public boolean deleteMovie(int id) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM movie_table WHERE id=?");
            statement.setInt(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }
}