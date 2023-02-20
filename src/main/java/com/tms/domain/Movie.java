package com.tms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Data
@Scope
public class Movie {
    private int id;
    private String movieName;
    private int year;
    private String genre;
    private double rating;
    private String description;

    @Autowired//DI
    private Actor actor;
}
