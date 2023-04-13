package com.tms.domain;

import com.tms.annotation.FirstCharacter8;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Data
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq_gen")
    @SequenceGenerator(name="user_id_seq_gen", sequenceName = "user_table_id_seq", allocationSize = 1) //TODO: under config class
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "login")
    @Pattern(regexp = "[A-Z]*")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "created")
    private Date created;

    @Column(name = "changed")
    private Date changed;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "birthday_date")
    private Date birthdate;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @FirstCharacter8
    @Column(name = "telephone")
    private String telephoneNumber;

    @Column(name = "role")
    private String role;
}