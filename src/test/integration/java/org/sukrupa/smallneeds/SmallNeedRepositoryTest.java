package org.sukrupa.smallneeds;

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
import org.sukrupa.smallNeeds.SmallNeed;
import org.sukrupa.smallNeeds.SmallNeedRepository;

import javax.sound.midi.VoiceStatus;
import java.io.ObjectOutputStream;

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

    @Test
    public void  shouldSaveSmallNeeds(){
        smallNeedRepository=new SmallNeedRepository(sessionFactory);
        SmallNeed schoolUniformSmallNeed= new SmallNeed("School Uniform",5000L,"For Aarthi");
        smallNeedRepository.put(schoolUniformSmallNeed);
        SmallNeed retrieveSmallNeed= smallNeedRepository.findByName("School Uniform");
        assertThat(retrieveSmallNeed.getItemName(),is(schoolUniformSmallNeed.getItemName()));
        assertThat(retrieveSmallNeed.getCost(),is(schoolUniformSmallNeed.getCost()));
        assertThat(retrieveSmallNeed.getComment(),is(schoolUniformSmallNeed.getComment()));
    }

    @Before
    public void setUp(){
      smallNeedRepository = new SmallNeedRepository(sessionFactory);
    }

}
