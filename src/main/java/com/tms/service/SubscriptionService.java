package com.tms.service;

import com.tms.domain.Subscription;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class SubscriptionService {

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

    public Subscription getSubscriptionById(int id) {
        Subscription subscription = new Subscription();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM subscription_table WHERE id=?");
            statement.setInt(1, id); //TODO: change userId
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            subscription.setId(resultSet.getInt("id"));
            subscription.setExpireDate(resultSet.getDate("expire_date"));
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return subscription;
    }

    public boolean createSubscription(int userId) {
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

    public boolean updateSubscription(int id, Date expireDate) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {

            PreparedStatement statement = connection.prepareStatement("UPDATE subscription_table SET expire_date=? WHERE id=?");
            statement.setDate(1, expireDate);
            statement.setInt(2, id);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }

    public boolean deleteSubscription(int id) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM subscription_table WHERE id=?");
            statement.setInt(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }
}