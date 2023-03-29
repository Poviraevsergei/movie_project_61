package com.tms.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "movie_table")
@ToString(exclude = {"userList"})
@EqualsAndHashCode(exclude = {"userList"})
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mov_id_seq_gen")
    @SequenceGenerator(name="mov_id_seq_gen", sequenceName = "movie_table_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "year")
    private Integer year;

    @Column(name = "genre")
    private String genre;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "description")
    private String description;

    @JsonBackReference
    @ManyToMany(mappedBy = "movieList")
    private Set<User> userList = new HashSet<>();
}
