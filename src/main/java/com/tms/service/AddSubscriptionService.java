package com.tms.service;

import java.sql.*;

public class AddSubscriptionService {

    private final long ONE_YEAR = 31556926000L;

    {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean add(int userId) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO subscription_table (id, expire_date,user_id) " +
                    "VALUES (DEFAULT , ?,?)");
            statement.setDate(1, new Date((new java.util.Date()).getTime() + ONE_YEAR));
            statement.setInt(2, userId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }
}
