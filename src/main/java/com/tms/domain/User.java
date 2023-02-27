package com.tms.domain;

import com.tms.annotation.FirstCharacter8;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@Component
public class User {
    private int id;
    private String firstName;
    private String lastName;

    @Pattern(regexp = "[A-Z]*")
    private String login;

    @Size(min = 5, max = 10)
    private String password;
    private Date created;
    private Date changed;

    @Email
    private String email;
    private Date birthdate;
    private boolean isDeleted;

    @FirstCharacter8
    private String telephoneNumber;
}