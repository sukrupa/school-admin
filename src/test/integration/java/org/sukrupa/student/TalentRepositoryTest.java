package org.sukrupa.student;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.platform.config.SpringContextLoaderForTesting;
import org.sukrupa.platform.db.HibernateSession;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

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
    private HibernateSession hibernateSession;

    private TalentRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new TalentRepository(sessionFactory);
    }

    @Test
    public void shouldReturnListOfTalents() {
	    hibernateSession.save(music, sport, cooking);
        Set<String> talentsDecriptions = new HashSet<String>();
        talentsDecriptions.add(MUSIC);
        talentsDecriptions.add(SPORT);
        talentsDecriptions.add(COOKING);
        Set<Talent> talents = repository.findTalents(talentsDecriptions);
        assertThat(talents, hasItems(music, sport, cooking));
    }

}
