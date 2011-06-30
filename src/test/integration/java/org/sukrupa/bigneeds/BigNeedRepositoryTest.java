package org.sukrupa.bigneeds;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.sukrupa.student.StudentRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BigNeedRepositoryTest {

    @Autowired
    private SessionFactory sessionFactory;

    private BigNeedRepository bigNeedRepository = new BigNeedRepository(sessionFactory);

    @Test
    public void shouldHaveCountZero() {
        assertThat(bigNeedRepository.getCount(), is(0));
    }
}
