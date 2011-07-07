package org.sukrupa.bigneeds;

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

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
    public void shouldSaveABigNeed() {
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
        List<BigNeed> bigNeedList = bigNeedRepository.getList();
        assertThat(bigNeedList.isEmpty(), is(false));
    }

    @Test
    public void shouldDeleteBigNeed() {
        BigNeed banana = new BigNeed("Banana", 25000);
        bigNeedRepository.put(banana);
        bigNeedRepository.delete(banana);
        assertThat(bigNeedRepository.getList(), not(hasItem(banana)));
    }

    @Test
    public void shouldGetBigNeedById() {
        BigNeed example = new BigNeed("example", 10000);
        bigNeedRepository.put(example);
        long id = example.getId();
        assertThat(bigNeedRepository.getBigNeed(id), is(example));
    }

    @Before
    public void setUp() {
        bigNeedRepository = new BigNeedRepository(sessionFactory);
    }
}