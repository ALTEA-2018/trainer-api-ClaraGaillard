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

    @PostMapping("/registerTrainer")
    public Trainer registerNewTrainer(@RequestBody  Trainer trainer){
        return trainerService.createTrainer(trainer);
    }

    /*@Bean
    @Override
    public UserDetailsService userDetailsService() {
        return username -> Optional.ofNullable((trainerService.getTrainer(username)).map(trainer ->
                User.withUsername(trainer.getName()).password(trainer.getPassword()).roles("USER").build().orElseThrow(() -> new BadCredentialsException("No such user"))
        ));
    }*/

}
