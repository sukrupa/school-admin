package org.sukrupa.student;

import com.google.common.collect.Sets;
import org.hamcrest.Matchers;
import org.hibernate.SessionFactory;
import org.joda.time.LocalDate;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.platform.config.SpringContextLoaderForTesting;
import org.sukrupa.platform.date.Date;
import org.sukrupa.platform.db.DatabaseHelper;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.sukrupa.platform.date.DateManipulation.freezeTime;
import static org.sukrupa.platform.date.DateManipulation.unfreezeTime;
import static org.sukrupa.platform.hamcrest.Matchers.hasOnly;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringContextLoaderForTesting.class)
@Transactional
public class TalentRepositoryTest {

    static final String MUSIC = "Music";
    static final String SPORT = "Sport";
    static final String COOKING = "Cooking";

    private final Talent music = new Talent(MUSIC);
    private final Talent sport = new Talent(SPORT);
    private final Talent cooking = new Talent(COOKING);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DatabaseHelper databaseHelper;

    private TalentRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new TalentRepository(sessionFactory);
    }

    @Test
    public void shouldReturnListOfTalents() {
	    databaseHelper.save(music, sport, cooking);
        Set<String> talentsDecriptions = new HashSet<String>();
        talentsDecriptions.add(MUSIC);
        talentsDecriptions.add(SPORT);
        talentsDecriptions.add(COOKING);
        Set<Talent> talents = repository.findTalents(talentsDecriptions);
        assertThat(talents, hasItems(music, sport, cooking));
    }

}
