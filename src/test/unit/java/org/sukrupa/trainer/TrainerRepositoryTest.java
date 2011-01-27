package org.sukrupa.trainer;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrainerRepositoryTest {

    TrainerRepository repository = new TrainerRepository();

    @Test
    public void shouldReturnAllTrainers() {
        assertThat(repository.findAll(), is(asList("Pat", "Jim", "Jonny", "Pradi", "Nivetha", "Shilpa")));
    }

}
