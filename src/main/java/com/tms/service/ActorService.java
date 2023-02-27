package com.tms.service;

import com.tms.domain.Actor;
import com.tms.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class ActorService {

    ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Actor getActorById(int id) {
        return actorRepository.getActorById(id);
    }

    public boolean createActor(String firstName, String lastName, int age, String biography) {
        return actorRepository.createActor(firstName, lastName, age, biography);
    }

    public boolean updateActor(int id, String firstName, String lastName, int age, String biography) {
        return actorRepository.updateActor(id, firstName, lastName, age, biography);
    }

    public boolean deleteActor(int id) {
        return actorRepository.deleteActor(id);
    }
}