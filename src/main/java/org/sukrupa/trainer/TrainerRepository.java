package org.sukrupa.trainer;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Repository
public class TrainerRepository {

    private static final List<String> TRAINERS = asList("Pat", "Jim", "Jonny", "Pradi", "Nivetha", "Shilpa");

    public List<String> findAll() {
        return TRAINERS;
    }
}
