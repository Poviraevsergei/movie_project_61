package com.tms.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tms.annotation.FirstCharacter8;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "user_table")
@ToString(exclude = {"subField", "movieList", "commentList"})
@EqualsAndHashCode(exclude = {"subField", "movieList", "commentList"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq_gen")
    @SequenceGenerator(name = "user_id_seq_gen", sequenceName = "user_table_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "login")
    @Pattern(regexp = "[A-Z]*")
    private String login;

    @Column(name = "password")
    @Size(min = 5, max = 10)
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
    private boolean isDeleted;

    @FirstCharacter8
    @Column(name = "telephone")
    private String telephoneNumber;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_id", referencedColumnName = "id")
    private Subscription subField;

    @JsonManagedReference
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "l_user_movie",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "movie_id")}
    )
    private Set<Movie> movieList = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Comment> commentList = new HashSet<>();
}