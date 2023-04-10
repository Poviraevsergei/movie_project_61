package com.tms.repository;

import com.tms.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByLastName(String ln);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE user_table SET is_deleted =true WHERE id = :id", countQuery = "SELECT * from user_table WHERE id = :id")
    void deleteUser(Integer id);

    Optional<User> findUserByLogin(String login);

/*    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO l_user_movie (id, user_id, movie_id) VALUES (DEFAULT, :userId, :movieId)")
    void addMovieToUser(Integer userId, Integer movieId);*/
}
