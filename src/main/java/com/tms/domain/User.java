package com.tms.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Data
@Component
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Date created;
    private Date changed;
    private String email;
    private Date birthdate;
    private boolean isDeleted;
    private String telephoneNumber;
}