package com.tms.repository;

import com.tms.domain.Movie;
import com.tms.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;

@Repository
public class UserRepository {

    private final SessionFactory sessionFactory;

    public UserRepository() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory(); //TODO:to configure class
    }

    public ArrayList<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User");
        ArrayList<User> list = (ArrayList<User>) query.getResultList();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public User getUserById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.getTransaction().commit();
        session.close();
        if (user != null) {
            return user;
        }
        return new User();
    }

    public boolean createUser(User user) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        user.setChanged(new Date(System.currentTimeMillis()));
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public boolean deleteUser(int id) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            User user = session.get(User.class,id);
            user.setDeleted(true);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //TODO: Query Hibernate
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

    //TODO: Query Hibernate
    public boolean addMovieToUser(int userId, int movieId) {
      /*  int result = template.update("INSERT INTO l_user_movie (id, user_id, movie_id) " +
                "VALUES (DEFAULT, ?, ?)", new Object[]{userId, movieId});
        return result == 1;*/
        return true;
    }
}
