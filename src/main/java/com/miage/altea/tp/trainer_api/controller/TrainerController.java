package com.miage.altea.tp.trainer_api.controller;

import com.miage.altea.tp.trainer_api.bo.Trainer;
import com.miage.altea.tp.trainer_api.service.TrainerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    TrainerController(TrainerService trainerService){
        this.trainerService = trainerService;
    }

    @GetMapping("/")
    Iterable<Trainer> getAllTrainers(){
        return this.trainerService.getAllTrainers();
    }

    @GetMapping("/{name}")
    Trainer getTrainer(@PathVariable String name){
        if(name != null) {
            return this.trainerService.getTrainer(name);
        } else {
            return new Trainer();
        }
    }

    @PostMapping("/")
    public Trainer createTrainer(@RequestBody  Trainer trainer){
        return trainerService.createTrainer(trainer);
    }

    @PutMapping(value="/")
    Trainer updateTrainer(@RequestBody Trainer trainer){
        return trainerService.updateTrainer(trainer);
    }

    @DeleteMapping(value="/{name}")
    void deleteTrainer(@PathVariable String name){
        Trainer t = trainerService.getTrainer(name);
        trainerService.deleteTrainer(t);
    }

}
