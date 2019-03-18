package com.miage.altea.tp.trainer_api.service;

import com.miage.altea.tp.trainer_api.bo.Trainer;
import com.miage.altea.tp.trainer_api.repository.TrainerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {

    private TrainerRepository trainerRepository;

    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Iterable<Trainer> getAllTrainers() {
        Iterable<Trainer> listTrainer = this.trainerRepository.findAll();
        return listTrainer;
    }

    @Override
    public Trainer getTrainer(String name) {
        Optional<Trainer> trainer = this.trainerRepository.findById(name);
        if(trainer.isPresent()){
            return trainer.get();
        }
        else return null;
    }

    @Override
    public Trainer createTrainer(Trainer trainer) {
       Trainer newTrainer = this.trainerRepository.save(trainer);
       return newTrainer;
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
    return username -> Optional.ofNullable((trainerService.getTrainer(username)).map(trainer ->
                    User.withUsername(trainer.getName()).password(trainer.getPassword()).roles("USER").build().orElseThrow(() -> new BadCredentialsException("No such user"))
            ));
    }
}
