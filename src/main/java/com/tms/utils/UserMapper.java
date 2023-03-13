package com.tms.utils;

import com.tms.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setCreated(resultSet.getDate("created"));
        user.setChanged(resultSet.getDate("changed"));
        user.setEmail(resultSet.getString("email"));
        user.setBirthdate(resultSet.getDate("birthday_date"));
        user.setDeleted(resultSet.getBoolean("is_deleted"));
        user.setTelephoneNumber(resultSet.getString("telephone"));
        return user;
    }
}
