package org.sukrupa.bigneeds;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.platform.config.SpringContextLoaderForTesting;
import org.sukrupa.platform.db.HibernateSession;
import org.sukrupa.student.StudentRepository;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringContextLoaderForTesting.class)
@Transactional
public class BigNeedRepositoryTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateSession hibernateSession;

    private BigNeedRepository bigNeedRepository;

    @Test
    public void shouldSaveABigNeed(){
        BigNeed powerGeneratorBigNeed = new BigNeed("Power Generator", 50000);
        bigNeedRepository.put(powerGeneratorBigNeed);
        BigNeed retrievedBigNeed = bigNeedRepository.findByName("Power Generator");
        assertThat(retrievedBigNeed.getItemName(), is(powerGeneratorBigNeed.getItemName()));
        assertThat(retrievedBigNeed.getCost(), is(powerGeneratorBigNeed.getCost()));
    }

    @Test
    public void shouldRetrieveBigNeedList() {
        BigNeed computerBigNeed = new BigNeed("Computer", 120000);
        BigNeed airConditionerBigNeed = new BigNeed("Air Conditioner", 20000);
        bigNeedRepository.put(computerBigNeed);
        bigNeedRepository.put(airConditionerBigNeed);
        List<BigNeed> bigNeedList =  bigNeedRepository.getList();
        assertThat(bigNeedList.isEmpty(), is(false));
    }

    @Before
    public void setUp(){
        bigNeedRepository = new BigNeedRepository(sessionFactory);
    }
}
