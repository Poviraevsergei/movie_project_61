package com.tms.controller;

import com.tms.domain.Actor;
import com.tms.service.ActorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/actor")
public class ActorController {

    ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    private static final Logger log = LoggerFactory.getLogger(ActorController.class);

    @GetMapping("/{id}")
    public String getActor(@PathVariable int id, Model model) {
        log.info("doing /actor Get method!");
        Actor actor = actorService.getActorById(id);
        if (actor.getId() == 0) {
            log.warn("User is not found! Trying find id=" + id);
        }
        model.addAttribute("actor", actor);
        return "singleActor";
    }

    @PostMapping
    public String createActor(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam int age,
            @RequestParam String biography
    ) {
        log.info("doing /actor Post method!");
        boolean result = actorService.createActor(firstName, lastName, age, biography);
        return result ? "successfully" : "unsuccessfully";
    }

    @PutMapping
    public String updateActor(
            @RequestParam int id,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam int age,
            @RequestParam String biography
    ) {
        boolean result = actorService.updateActor(id, firstName, lastName, age, biography);
        return result ? "successfully" : "unsuccessfully";
    }

    @DeleteMapping("/{id}")
    public String deleteActor(@PathVariable int id){
        log.info("doing /actor Delete method!");
        boolean result = actorService.deleteActor(id);
        return result ? "successfully" : "unsuccessfully";
    }
}