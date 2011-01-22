package org.sukrupa.trainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

    private final TrainerRepository repository;

    @Autowired
    public TrainerService(TrainerRepository repository) {
        this.repository = repository;
    }

    public List<String> findAll() {
        return repository.findAll();
    }

    public String find(String trainer) {
        for (String aTrainer : findAll()) {
            if (aTrainer.equalsIgnoreCase(trainer))
                return aTrainer;
        }
        throw new RuntimeException("boom");
    }
}
