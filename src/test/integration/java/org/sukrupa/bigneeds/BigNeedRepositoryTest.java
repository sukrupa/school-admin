package org.sukrupa.bigneeds;


import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.platform.config.SpringContextLoaderForTesting;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringContextLoaderForTesting.class)
@Transactional
public class BigNeedRepositoryTest {

    @Autowired
    private SessionFactory sessionFactory;

    private BigNeedRepository bigNeedRepository;

    @Test
    public void shouldSaveTheFirstBigNeedObject() {
        BigNeed powerGenerator = new BigNeed("Power Generator", 150000,1);
        bigNeedRepository.addBigNeed(powerGenerator, 1);

        BigNeed retrievedBigNeed = bigNeedRepository.findByName("Power Generator");
        assertThat(retrievedBigNeed.getItemName(), is(powerGenerator.getItemName()));
        assertThat(retrievedBigNeed.getCost(), is(powerGenerator.getCost()));
        assertThat(retrievedBigNeed.getPriority(), is(powerGenerator.getPriority()));
    }

    @Test
    public void shouldInsertRecordInProperPositionAndModifyPriority() {
        BigNeed powerGenerator = new BigNeed("Power Generator", 150000, 1);
        sessionFactory.getCurrentSession().save(powerGenerator);

        BigNeed airConditioner = new BigNeed("Air Conditioner", 20000);
        bigNeedRepository.addBigNeed(airConditioner, 1);

        assertThat(bigNeedRepository.findByName(airConditioner.getItemName()).getPriority(), is(1));
        assertThat(bigNeedRepository.findByName(powerGenerator.getItemName()).getPriority(), is(2));
    }

    @Test
    public void shouldInsertRecordInProperPositionWhenItemsAreUpdatedToHigherPriority() {
        BigNeed powerGenerator = new BigNeed("Power Generator", 50000, 1);
        BigNeed airConditioner = new BigNeed("Air Conditioner", 20000, 2);
        BigNeed largeBed = new BigNeed("Big Large Bed", 20000, 3);

        sessionFactory.getCurrentSession().save(powerGenerator);
        sessionFactory.getCurrentSession().save(airConditioner);
        sessionFactory.getCurrentSession().save(largeBed);

        bigNeedRepository.editBigNeed(largeBed,2);

        assertThat(bigNeedRepository.findByName(powerGenerator.getItemName()).getPriority(), is(1));
        assertThat(bigNeedRepository.findByName(largeBed.getItemName()).getPriority(), is(2));
        assertThat(bigNeedRepository.findByName(airConditioner.getItemName()).getPriority(), is(3));
    }

    @Test
    public void shouldInsertRecordInProperPositionWhenItemsAreUpdatedToHighestPriority() {
        BigNeed powerGenerator = new BigNeed("Power Generator", 50000, 1);
        BigNeed airConditioner = new BigNeed("Air Conditioner", 20000, 2);
        BigNeed largeBed = new BigNeed("Big Large Bed", 20000, 3);

        sessionFactory.getCurrentSession().save(powerGenerator);
        sessionFactory.getCurrentSession().save(airConditioner);
        sessionFactory.getCurrentSession().save(largeBed);

        bigNeedRepository.editBigNeed(largeBed, 1);

        assertThat(bigNeedRepository.findByName(largeBed.getItemName()).getPriority(), is(1));
        assertThat(bigNeedRepository.findByName(powerGenerator.getItemName()).getPriority(), is(2));
        assertThat(bigNeedRepository.findByName(airConditioner.getItemName()).getPriority(), is(3));
    }

    @Test
    public void shouldInsertPriorityCorrectlyIfGreaterThanPrePopulatedPriority(){
        BigNeed powerGenerator = new BigNeed("Power Generator", 50000, 1);
        sessionFactory.getCurrentSession().save(powerGenerator);

        BigNeed airConditioner = new BigNeed("Air Conditioner", 20000);
        bigNeedRepository.addBigNeed(airConditioner,3);

        assertThat(bigNeedRepository.findByName(powerGenerator.getItemName()).getPriority(), is(1));
        assertThat(bigNeedRepository.findByName(airConditioner.getItemName()).getPriority(), is(2));

    }

     @Test
    public void shouldInsertRecordInProperPositionWhenItemsAreUpdatedToGreaterThanMaximumSize() {
        BigNeed powerGenerator = new BigNeed("Power Generator", 50000, 1);
        BigNeed airConditioner = new BigNeed("Air Conditioner", 20000, 2);
        BigNeed largeBed = new BigNeed("Big Large Bed", 20000, 3);

        sessionFactory.getCurrentSession().save(powerGenerator);
        sessionFactory.getCurrentSession().save(airConditioner);
        sessionFactory.getCurrentSession().save(largeBed);

        bigNeedRepository.editBigNeed(powerGenerator, 5);

        assertThat(bigNeedRepository.findByName(airConditioner.getItemName()).getPriority(), is(1));
         assertThat(bigNeedRepository.findByName(largeBed.getItemName()).getPriority(), is(2));
         assertThat(bigNeedRepository.findByName(powerGenerator.getItemName()).getPriority(), is(3));
    }

    @Test
    public void shouldHaveTheSamePriorityIfPriorityHasNotChanged(){
        BigNeed powerGenerator = new BigNeed("Power Generator", 50000, 1);
        BigNeed airConditioner = new BigNeed("Air Conditioner", 20000, 2);
        BigNeed largeBed = new BigNeed("Big Large Bed", 20000, 3);

        sessionFactory.getCurrentSession().save(powerGenerator);
        sessionFactory.getCurrentSession().save(airConditioner);
        sessionFactory.getCurrentSession().save(largeBed);

        airConditioner.setItemName("Projector");

        bigNeedRepository.editBigNeed(airConditioner,2);

         assertThat(bigNeedRepository.findByName(airConditioner.getItemName()).getPriority(), is(2));
         assertThat(bigNeedRepository.findByName(largeBed.getItemName()).getPriority(), is(3));
         assertThat(bigNeedRepository.findByName(powerGenerator.getItemName()).getPriority(), is(1));

    }

    @Test
    public void shouldInsertRecordInProperPositionWhenItemsAreUpdatedToLowerPriority() {
        BigNeed powerGenerator = new BigNeed("Power Generator", 50000, 1);
        BigNeed airConditioner = new BigNeed("Air Conditioner", 20000, 2);
        BigNeed largeBed = new BigNeed("Big Large Bed", 20000, 3);


        sessionFactory.getCurrentSession().save(powerGenerator);
        sessionFactory.getCurrentSession().save(airConditioner);
        sessionFactory.getCurrentSession().save(largeBed);

        bigNeedRepository.editBigNeed(airConditioner, 2);

        assertThat(bigNeedRepository.findByName(airConditioner.getItemName()).getPriority(), is(2));
        assertThat(bigNeedRepository.findByName(powerGenerator.getItemName()).getPriority(), is(1));
        assertThat(bigNeedRepository.findByName(largeBed.getItemName()).getPriority(), is(3));
       
    }

    @Test
    public void shouldInsertRecordInProperPositionWhenItemsAreUpdatedToLowestPriority() {
        BigNeed powerGenerator = new BigNeed("Power Generator", 50000, 1);
        BigNeed airConditioner = new BigNeed("Air Conditioner", 20000, 2);
        BigNeed largeBed = new BigNeed("Big Large Bed", 20000, 3);


        sessionFactory.getCurrentSession().save(powerGenerator);
        sessionFactory.getCurrentSession().save(airConditioner);
        sessionFactory.getCurrentSession().save(largeBed);

        bigNeedRepository.editBigNeed(powerGenerator, 3);

        assertThat(bigNeedRepository.findByName(airConditioner.getItemName()).getPriority(), is(1));
        assertThat(bigNeedRepository.findByName(largeBed.getItemName()).getPriority(), is(2));
        assertThat(bigNeedRepository.findByName(powerGenerator.getItemName()).getPriority(), is(3));
    }

    @Test
    public void shouldRetrieveBigNeedList() {
        BigNeed computerBigNeed = new BigNeed("Computer", 120000, 1);
        BigNeed airConditionerBigNeed = new BigNeed("Air Conditioner", 20000, 4);
        sessionFactory.getCurrentSession().save(computerBigNeed);
        sessionFactory.getCurrentSession().save(airConditionerBigNeed);
        List<BigNeed> bigNeedList = bigNeedRepository.getList();
        assertThat(bigNeedList.isEmpty(), is(false));
    }

     @Test
    public void shouldDeleteRecordAndAdjustThePriorites() {
        BigNeed powerGenerator = new BigNeed("Power Generator", 50000, 1);
        BigNeed airConditioner = new BigNeed("Air Conditioner", 20000, 2);
        BigNeed largeBed = new BigNeed("Big Large Bed", 20000, 3);

        sessionFactory.getCurrentSession().save(powerGenerator);
        sessionFactory.getCurrentSession().save(airConditioner);
        sessionFactory.getCurrentSession().save(largeBed);

        bigNeedRepository.delete(airConditioner);

        assertThat(bigNeedRepository.findByName(powerGenerator.getItemName()).getPriority(), is(1));
        assertThat(bigNeedRepository.findByName(largeBed.getItemName()).getPriority(), is(2));
        assertNull(bigNeedRepository.findByName(airConditioner.getItemName()));
    }

    @Test
    public void shouldDeleteBigNeed() {
        BigNeed banana = new BigNeed("Banana", 25000, 3);
        sessionFactory.getCurrentSession().save(banana);

        assertThat(bigNeedRepository.getList(), hasItem(banana));
        bigNeedRepository.delete(banana);
        assertThat(bigNeedRepository.getList(), not(hasItem(banana)));
    }

    @Test
    public void shouldGetBigNeedById() {
        BigNeed example = new BigNeed("example", 10000, 2);
        sessionFactory.getCurrentSession().save(example);

        long id = example.getId();
        assertThat(bigNeedRepository.getBigNeed(id), is(example));
    }

    @Before
    public void setUp() {
        bigNeedRepository = new BigNeedRepository(sessionFactory);
    }
}
