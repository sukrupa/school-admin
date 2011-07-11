package org.sukrupa.bigneeds;


import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Ignore;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringContextLoaderForTesting.class)
@Transactional
public class BigNeedRepositoryTest {

    @Autowired
    private SessionFactory sessionFactory;

    private BigNeedRepository bigNeedRepository;

    @Test
    public void shouldSaveABigNeed() {
        BigNeed powerGeneratorBigNeed = new BigNeed("Large Power Generatorsgsgs", 150000,12);
        bigNeedRepository.addOrEditBigNeed(powerGeneratorBigNeed);
        BigNeed retrievedBigNeed = bigNeedRepository.findByName("Large Power Generatorsgsgs");
        assertThat(retrievedBigNeed.getItemName(), is(powerGeneratorBigNeed.getItemName()));
        assertThat(retrievedBigNeed.getCost(), is(powerGeneratorBigNeed.getCost()));
        assertThat(retrievedBigNeed.getPriority(),is(powerGeneratorBigNeed.getPriority()));
    }

    @Test
    public void validatingcheckForPrioritization(){
        BigNeed powerGeneratorBigNeed = new BigNeed("Power Generator", 50000,1);
        BigNeed airConditionerBigNeed = new BigNeed("Air Conditioner", 20000,2);
        bigNeedRepository.addOrEditBigNeed(powerGeneratorBigNeed);
        bigNeedRepository.addOrEditBigNeed(airConditionerBigNeed);
        BigNeed banana = new BigNeed("Banana", 25000,1);
        assertThat(bigNeedRepository.checkForPrioritization(banana),is(true));
    }


    //todo fix me!
     @Test
    public void shouldReturnTheCorrectPositionForInsertingTheNewRecord(){
        BigNeed powerGeneratorBigNeed = new BigNeed("Power Generator", 50000,1);
        BigNeed airConditionerBigNeed = new BigNeed("Air Conditioner", 20000,2);
        BigNeed bigLargeBedBigNeed = new BigNeed("Big Large Bed", 20000,3);
        bigNeedRepository.addOrEditBigNeed(powerGeneratorBigNeed);
        bigNeedRepository.addOrEditBigNeed(airConditionerBigNeed);
        bigNeedRepository.addOrEditBigNeed(bigLargeBedBigNeed);
         List<BigNeed> list = bigNeedRepository.getList();
         List<BigNeed> unModifiedBigNeedList=returnUnmodifiedListOfBigNeeds(list,2);
        assertThat(unModifiedBigNeedList.size(),is(2));
        assertThat(unModifiedBigNeedList.get(0).getItemName(), is(airConditionerBigNeed.getItemName()));
        assertThat(unModifiedBigNeedList.get(1).getCost(), is(bigLargeBedBigNeed.getCost()));
        assertThat(unModifiedBigNeedList.get(0).getCost(), not(is(powerGeneratorBigNeed.getCost())));
    }

    @Test
    public void shouldInsertRecordInProperPositionAndModifyPriority(){
        BigNeed powerGeneratorBigNeed = new BigNeed("Large Power Generatorsgsgs", 150000,1);
        bigNeedRepository.addOrEditBigNeed(powerGeneratorBigNeed);
        BigNeed airConditionerBigNeed = new BigNeed("Air Conditioner", 20000,1);
        bigNeedRepository.addOrEditBigNeed(airConditionerBigNeed);
        BigNeed retrievedBigNeed = bigNeedRepository.findByName("Large Power Generatorsgsgs");
        assertThat(retrievedBigNeed.getPriority(),is(2));
    }

    private List<BigNeed> returnUnmodifiedListOfBigNeeds(List<BigNeed> bigNeedList, int index){
         return bigNeedList.subList(index -1,bigNeedList.size());
    }


//     @Test
//    public void shouldInsertRecordInProperPositionWhenItemsAreUpdated(){
//        BigNeed powerGeneratorBigNeed = new BigNeed("Power Generator", 50000,1);
//        BigNeed airConditionerBigNeed = new BigNeed("Air Conditioner", 20000,2);
//        BigNeed bigLargeBedBigNeed = new BigNeed("Big Large Bed", 20000,3);
//        BigNeed tempBigNeed, retrievedBigNeed;
//        bigNeedRepository.addOrEditBigNeed(powerGeneratorBigNeed);
//        bigNeedRepository.addOrEditBigNeed(airConditionerBigNeed);
//        bigNeedRepository.addOrEditBigNeed(bigLargeBedBigNeed);
//        bigLargeBedBigNeed.setPriority(2);
//        bigNeedRepository.addOrEditBigNeed(bigLargeBedBigNeed);
//        List<BigNeed> unModifiedBigNeedList=returnUnmodifiedListOfBigNeeds(bigNeedRepository.getList(),2);
//        retrievedBigNeed = bigNeedRepository.findByName("Air Conditioner");
//        assertThat(retrievedBigNeed.getPriority(),is(3));
//         retrievedBigNeed = bigNeedRepository.findByName("Big Large Bed");
//       assertThat(retrievedBigNeed.getPriority(),is(2));
//
//    }

@Test
 public void previousItemPriorityIsUpdatedAfterEditingWithExistingPriority()
     {
          BigNeed powerGeneratorBigNeed = new BigNeed("Power Generator", 50000,1);
        BigNeed airConditionerBigNeed = new BigNeed("Air Conditioner", 20000,2);

         bigNeedRepository.addOrEditBigNeed(powerGeneratorBigNeed);
        bigNeedRepository.addOrEditBigNeed(airConditionerBigNeed);
         sessionFactory.getCurrentSession().evict(airConditionerBigNeed);
         sessionFactory.getCurrentSession().evict(powerGeneratorBigNeed);
         airConditionerBigNeed.setPriority(1);


         bigNeedRepository.editBigNeed(airConditionerBigNeed);

         assertThat(bigNeedRepository.findByName(airConditionerBigNeed.getItemName()).getPriority(),is(1));
         assertThat(bigNeedRepository.findByName(powerGeneratorBigNeed.getItemName()).getPriority(),is(2));
     }
    @Test
    public void shouldRetrieveBigNeedList() {
        BigNeed computerBigNeed = new BigNeed("Computer", 120000,1);
        BigNeed airConditionerBigNeed = new BigNeed("Air Conditioner", 20000,4);
        bigNeedRepository.addOrEditBigNeed(computerBigNeed);
        bigNeedRepository.addOrEditBigNeed(airConditionerBigNeed);
        List<BigNeed> bigNeedList = bigNeedRepository.getList();
        assertThat(bigNeedList.isEmpty(), is(false));
    }

    @Test
    public void shouldDeleteBigNeed() {
        BigNeed banana = new BigNeed("Banana", 25000,3);
        bigNeedRepository.addOrEditBigNeed(banana);
        assertThat(bigNeedRepository.getList(), hasItem(banana));
        bigNeedRepository.delete(banana);
        assertThat(bigNeedRepository.getList(), not(hasItem(banana)));
    }

    @Test
    public void shouldGetBigNeedById() {
        BigNeed example = new BigNeed("example", 10000,2);
        bigNeedRepository.addOrEditBigNeed(example);
        long id = example.getId();
        assertThat(bigNeedRepository.getBigNeed(id), is(example));
    }

    @Before
    public void setUp() {
        bigNeedRepository = new BigNeedRepository(sessionFactory);
    }
}
