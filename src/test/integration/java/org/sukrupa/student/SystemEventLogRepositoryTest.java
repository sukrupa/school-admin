package org.sukrupa.student;

import org.hibernate.SessionFactory;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.platform.config.SpringContextLoaderForTesting;
import org.sukrupa.platform.date.DateManipulation;
import org.sukrupa.platform.db.HibernateSession;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringContextLoaderForTesting.class)
@Transactional
public class SystemEventLogRepositoryTest {

    static final String ANNUAL_UPDATE = "annual class update";

    private final SystemEventLog annualUpdate = new SystemEventLog(ANNUAL_UPDATE, new LocalDate(2009, 10, 01));


    @Autowired
    private HibernateSession hibernateSession;

    @Autowired
    private SessionFactory sessionFactory;

    private SystemEventLogRepository repository;

    @Before
    public void setUp() {
        repository = new SystemEventLogRepository(sessionFactory);
    }

    @Test
    public void shouldFindAnnualUpdate() {
        hibernateSession.save(annualUpdate);
        SystemEventLog systemEventLog = repository.find(ANNUAL_UPDATE);
        assertThat(systemEventLog, is(annualUpdate));
    }

    @Test
    public void shouldCreateLogs(){
        SystemEventLog newLog = new SystemEventLog("new log",new LocalDate(2011,01,01));
        repository.put(newLog);
        assertThat(repository.find("new log"),is(newLog));

    }



}
