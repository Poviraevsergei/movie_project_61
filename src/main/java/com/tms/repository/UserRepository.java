package com.tms.repository;

import com.tms.domain.Movie;
import com.tms.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

        Query query = session.createQuery("from User");
        ArrayList<User> list = (ArrayList<User>) query.getResultList();
        session.close();
        return list;
    }

    public User getUserById(int id) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root).where(cb.equal(root.get("id"),id));
        Query query = session.createQuery(cr);
        User user = (User) query.getSingleResult();
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
            Query query = session.createNativeQuery("INSERT INTO user_table (id, first_name, last_name, login, password, created, changed,email,birthday_date,is_deleted, telephone)" +
                    "VALUES (DEFAULT, :first_name, :last_name, :login, :password, :created, :changed, :email, :birthday_date, DEFAULT, :telephone)");
            query.setParameter("first_name", user.getFirstName());
            query.setParameter("last_name", user.getLastName());
            query.setParameter("login", user.getLogin());
            query.setParameter("password", user.getPassword());
            query.setParameter("created", user.getCreated());
            query.setParameter("changed", user.getChanged());
            query.setParameter("email", user.getEmail());
            query.setParameter("changed", user.getChanged());
            query.setParameter("birthday_date", user.getBirthdate());
            query.setParameter("telephone", user.getTelephoneNumber());
            query.executeUpdate();
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
        Query query = session.createQuery("UPDATE User set firstName=:firstName, lastName=:lastName, changed=:changed," +
                " login=:login, password=:password, email=:email, birthdate=:birthday_date, telephoneNumber=:telephone WHERE id=:userId");
        query.setParameter("userId", user.getId());
        query.setParameter("firstName", user.getFirstName());
        query.setParameter("lastName", user.getLastName());
        query.setParameter("changed", new Date(System.currentTimeMillis()));
        query.setParameter("login", user.getLogin());
        query.setParameter("password", user.getPassword());
        query.setParameter("email", user.getEmail());
        query.setParameter("birthday_date", user.getBirthdate());
        query.setParameter("telephone", user.getTelephoneNumber());
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public boolean deleteUser(int id) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("UPDATE User set isDeleted=true WHERE id=:userId");
            query.setParameter("userId", id);
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<Movie> getMoviesForSingleUser(int id) {
        Session session = sessionFactory.openSession();
        Query query = session.createNativeQuery("SELECT m.id,m.movie_name,m.year,m.genre,m.rating,m.description FROM l_user_movie JOIN movie_table as m ON l_user_movie.movie_id = m.id WHERE l_user_movie.user_id=:userId");
        query.setParameter("userId", id);
        return (ArrayList<Movie>) query.getResultList();
    }

    public boolean addMovieToUser(int userId, int movieId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createNativeQuery("INSERT INTO l_user_movie (id, user_id, movie_id) " +
                "VALUES (DEFAULT, :userId, :movieId)");
        query.setParameter("userId",userId);
        query.setParameter("movieId",movieId);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        return result == 1;
    }
}