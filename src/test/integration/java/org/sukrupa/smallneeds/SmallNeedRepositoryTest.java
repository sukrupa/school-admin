package org.sukrupa.smallneeds;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.platform.config.SpringContextLoaderForTesting;
import org.sukrupa.platform.db.HibernateSession;
import org.sukrupa.smallNeeds.SmallNeed;
import org.sukrupa.smallNeeds.SmallNeedRepository;

import javax.sound.midi.VoiceStatus;
import java.io.ObjectOutputStream;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringContextLoaderForTesting.class)
@Transactional
public class SmallNeedRepositoryTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateSession hibernateSession;
    private SmallNeedRepository smallNeedRepository;

    @Before
    public void setUp() {
        smallNeedRepository = new SmallNeedRepository(sessionFactory);
    }
    
    @Test
    public void shouldRetrieveListOfSmallNeeds() {
        SmallNeed item1 = new SmallNeed("Item 1", 100, "A comment",1);
        SmallNeed item2 = new SmallNeed("Item 2", 200, "Another comment",2);
        smallNeedRepository.put(item1);
        smallNeedRepository.put(item2);

        List<SmallNeed> smallNeeds = smallNeedRepository.getList();
        assertThat(smallNeeds, hasItem(item1));
        assertThat(smallNeeds, hasItem(item2));
    }

    @Test
    public void shouldSaveSmallNeeds() {
        SmallNeed schoolUniformSmallNeed = new SmallNeed("School Uniform", 5000L, "For Aarthi",1);
        smallNeedRepository.put(schoolUniformSmallNeed);
        SmallNeed retrieveSmallNeed = smallNeedRepository.findByName("School Uniform");
        assertThat(retrieveSmallNeed.getItemName(), is(schoolUniformSmallNeed.getItemName()));
        assertThat(retrieveSmallNeed.getCost(), is(schoolUniformSmallNeed.getCost()));
        assertThat(retrieveSmallNeed.getComment(), is(schoolUniformSmallNeed.getComment()));
    }

}
