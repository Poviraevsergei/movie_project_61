package com.tms.domain;

import lombok.Data;

@Data //POJO (Plain old java object)
public class Movie {
    private int id;
    private String movieName;
    private int year;
    private String genre;
    private double rating;
    private String description;
}
