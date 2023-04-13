package com.tms.domain.request;

import com.tms.annotation.FirstCharacter8;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Data
@AllArgsConstructor
public class UserRegistrationRequest {
    private String firstName;
    private String lastName;

    @Pattern(regexp = "[A-Z]*")
    private String login;
    private String password;

    @Email
    private String email;
    private Date birthdate;

    @FirstCharacter8
    private String telephoneNumber;
}
