package com.tms.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddMovieToUserService {

    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean add(int userId, int movieId){
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO l_user_movie (id, user_id, movie_id) " +
                    "VALUES (DEFAULT, ?, ?)");
            statement.setInt(1, userId);
            statement.setInt(2, movieId);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }

}
