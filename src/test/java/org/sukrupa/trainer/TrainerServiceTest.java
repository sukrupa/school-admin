package org.sukrupa.trainer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TrainerServiceTest {

    @Mock
    private TrainerRepository repository;

    private TrainerService service;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        service = new TrainerService(repository);
    }

    @Test
    public void shouldGetAllTrainersFromRepository() {
        service.findAll();
        verify(repository).findAll();
    }

    @Test
    public void shouldFindTrainerByName() {
        when(repository.findAll()).thenReturn(asList("Fish", "Bird", "Cow"));
        assertThat(service.find("bird"), is("Bird"));
    }
}
