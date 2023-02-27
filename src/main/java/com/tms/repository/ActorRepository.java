package com.tms.repository;

import com.tms.domain.Actor;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class ActorRepository {

    public Actor getActorById(int id) {
        Actor actor = new Actor();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM actors WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            actor.setId(resultSet.getInt("id"));
            actor.setFirstName(resultSet.getString("first_name"));
            actor.setLastName(resultSet.getString("last_name"));
            actor.setAge(resultSet.getInt("age"));
            actor.setBiography(resultSet.getString("biography"));
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return actor;
    }

    public boolean createActor(String firstName, String lastName, int age, String biography) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO actors (id, first_name, last_name, age, biography) " +
                    "VALUES (DEFAULT, ?, ?, ?, ?)");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.setString(4, biography);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }

    public boolean updateActor(int id, String firstName, String lastName, int age, String biography) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {

            PreparedStatement statement = connection.prepareStatement("UPDATE actors SET first_name=?, last_name=?, age=?, biography=? WHERE id=?");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.setString(4, biography);
            statement.setInt(5, id);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }

    public boolean deleteActor(int id) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movie_db", "postgres", "root")) {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM actors WHERE id=?");
            statement.setInt(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something wrong....");
        }
        return result == 1;
    }
}
