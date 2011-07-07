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

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
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
        BigNeed powerGeneratorBigNeed = new BigNeed("Power Generator", 50000,1);
        bigNeedRepository.put(powerGeneratorBigNeed);
        BigNeed retrievedBigNeed = bigNeedRepository.findByName("Power Generator");
        assertThat(retrievedBigNeed.getItemName(), is(powerGeneratorBigNeed.getItemName()));
        assertThat(retrievedBigNeed.getCost(), is(powerGeneratorBigNeed.getCost()));
        //assertThat(retrievedBigNeed.getPriority(),is(powerGeneratorBigNeed.getPriority()));
    }

    @Test
    public void validatingcheckForPrioritization(){
        BigNeed powerGeneratorBigNeed = new BigNeed("Power Generator", 50000,1);
        BigNeed airConditionerBigNeed = new BigNeed("Air Conditioner", 20000,2);
        bigNeedRepository.put(powerGeneratorBigNeed);
        bigNeedRepository.put(airConditionerBigNeed);
        BigNeed banana = new BigNeed("Banana", 25000,1);
        assertThat(bigNeedRepository.checkForPrioritization(banana),is(true));

    }

     @Test
    public void shouldInsertNewItemInTheProperPosition(){
        BigNeed powerGeneratorBigNeed = new BigNeed("Power Generator", 50000,1);
        BigNeed airConditionerBigNeed = new BigNeed("Air Conditioner", 20000,2);
         BigNeed bigLargeBedBigNeed = new BigNeed("Big Large Bed", 20000,3);
        bigNeedRepository.put(powerGeneratorBigNeed);
        bigNeedRepository.put(airConditionerBigNeed);
        BigNeed banana = new BigNeed("Banana", 25000,2);
        assertThat(bigNeedRepository.checkForPrioritization(banana),is(true));

    }

    @Test
    public void shouldRetrieveBigNeedList() {
        BigNeed computerBigNeed = new BigNeed("Computer", 120000,1);
        BigNeed airConditionerBigNeed = new BigNeed("Air Conditioner", 20000,4);
        bigNeedRepository.put(computerBigNeed);
        bigNeedRepository.put(airConditionerBigNeed);
        List<BigNeed> bigNeedList = bigNeedRepository.getList();
        assertThat(bigNeedList.isEmpty(), is(false));
    }

    @Test
    public void shouldDeleteBigNeed() {
        BigNeed banana = new BigNeed("Banana", 25000,3);
        bigNeedRepository.put(banana);
        assertThat(bigNeedRepository.getList(), hasItem(banana));
        bigNeedRepository.delete(banana);
        assertThat(bigNeedRepository.getList(), not(hasItem(banana)));
    }

    @Test
    public void shouldGetBigNeedById() {
        BigNeed example = new BigNeed("example", 10000,2);
        bigNeedRepository.put(example);
        long id = example.getId();
        assertThat(bigNeedRepository.getBigNeed(id), is(example));
    }

    @Before
    public void setUp() {
        bigNeedRepository = new BigNeedRepository(sessionFactory);
    }
}
