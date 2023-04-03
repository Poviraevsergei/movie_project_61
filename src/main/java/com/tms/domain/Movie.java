package com.tms.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Movie {
    private int id;
    private String movieName;
    private Integer year;
    private String genre;
    private Double rating;
    private String description;
    private Set<User> userList = new HashSet<>();
}
