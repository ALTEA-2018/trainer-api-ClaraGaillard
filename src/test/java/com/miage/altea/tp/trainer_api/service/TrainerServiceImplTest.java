package com.miage.altea.tp.trainer_api.service;

import com.miage.altea.tp.trainer_api.repository.TrainerRepository;
import org.junit.jupiter.api.Test;
import com.miage.altea.tp.trainer_api.bo.Trainer;
import org.mockito.Mockito;

class TrainerServiceImplTest {

    @Test
    void getAllTrainers_shouldCallTheRepository() {
        var trainerRepo = Mockito.mock(TrainerRepository.class);
        var trainerService = new TrainerServiceImpl(trainerRepo);

        trainerService.getAllTrainers();

        Mockito.verify(trainerRepo).findAll();
    }

    @Test
    void getTrainer_shouldCallTheRepository() {
        var trainerRepo = Mockito.mock(TrainerRepository.class);
        var trainerService = new TrainerServiceImpl(trainerRepo);

        trainerService.getTrainer("Ash");

        Mockito.verify(trainerRepo).findById("Ash");
    }

    @Test
    void createTrainer_shouldCallTheRepository() {
        var trainerRepo = Mockito.mock(TrainerRepository.class);
        var trainerService = new TrainerServiceImpl(trainerRepo);

        var ash = new Trainer();
        trainerService.createTrainer(ash);

        Mockito.verify(trainerRepo).save(ash);
    }

}