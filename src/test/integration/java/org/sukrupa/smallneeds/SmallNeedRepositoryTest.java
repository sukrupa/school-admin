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
import org.sukrupa.needs.Need;
import org.sukrupa.needs.NeedRepository;
import org.sukrupa.needs.NeedRepositoryTestBase;
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
public class SmallNeedRepositoryTest extends NeedRepositoryTestBase{

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
    public void shouldSaveTheFirstSmallNeed() {
        assertSavingFirstNeed();
    }

    @Test
    public void shouldInsertRecordInProperPositionAndModifyPriority() {
        assertInsertNeed();
    }

    @Test
    public void shouldInsertRecordInProperPositionWhenItemsAreUpdatedToHigherPriority() {
        assertUpdateToHigherPriority();
    }

    @Test
    public void shouldInsertRecordInProperPositionWhenItemsAreUpdatedToHighestPriority() {
        assertUpdateToHighestPriority();
    }

      @Test
    public void shouldInsertPriorityCorrectlyIfGreaterThanPrePopulatedPriority() {
        assertInsertWithGreaterThanExistingPriority();
    }

    @Test
    public void shouldUpdatePriorityCorrectlyIfGreaterThanPrePopulatedPriority() {
        assertUpdateWithGreaterThanExistingPriority();
    }

    @Test
    public void shouldHaveTheSamePriorityIfPriorityHasNotChanged() {
        assertHasTheSamePriorityIfPriorityHasNotChanged();

    }

    @Test
    public void shouldInsertRecordInProperPositionWhenItemsAreUpdatedToLowerPriority() {
        assertUpdateToLowerPriority();
    }

    @Test
    public void shouldInsertRecordInProperPositionWhenItemsAreUpdatedToLowestPriority() {
        assertUpdateToLowestPriority();
    }

    @Test
    public void shouldRetrieveSmallNeedList() {
        assertRetrieveNeedList();
    }

    @Test
    public void shouldDeleteRecordAndAdjustThePriorites() {
        assertDeleteAdjustsPriorities();
    }

    @Test
    public void shouldDeleteSmallNeed() {
        assertDelete();
    }

    @Test
    public void shouldGetSmallNeedById() {
        assertGetNeedById();
    }

    
    @Test
    public void shouldRetrieveListOfSmallNeeds() {
        SmallNeed item1 = new SmallNeed("Item 1", 100, "A comment",1);
        SmallNeed item2 = new SmallNeed("Item 2", 200, "Another comment",2);
        smallNeedRepository.addNeed(item1, 1);
        smallNeedRepository.addNeed(item2, 2);

        List<SmallNeed> smallNeeds = smallNeedRepository.getList();
        assertThat(smallNeeds, hasItem(item1));
        assertThat(smallNeeds, hasItem(item2));
    }

    @Test
    public void shouldSaveSmallNeeds() {
        SmallNeed schoolUniformSmallNeed = new SmallNeed("School Uniform", 5000L, "For Aarthi",1);
        smallNeedRepository.addNeed(schoolUniformSmallNeed, 1);
        SmallNeed retrieveSmallNeed = smallNeedRepository.findByName("School Uniform");
        assertThat(retrieveSmallNeed.getItemName(), is(schoolUniformSmallNeed.getItemName()));
        assertThat(retrieveSmallNeed.getCost(), is(schoolUniformSmallNeed.getCost()));
        assertThat(retrieveSmallNeed.getComment(), is(schoolUniformSmallNeed.getComment()));
    }

    @Test
    public void shouldDeleteSmallNeeds(){
        SmallNeed schoolUniformSmallNeed = new SmallNeed("School Uniform", 5000L, "For Aarthi",1);
        smallNeedRepository.addNeed(schoolUniformSmallNeed, 1);
        List<SmallNeed> smallNeedList = smallNeedRepository.getList();
        assertThat(smallNeedList.size(),is(1));
        smallNeedRepository.delete(smallNeedRepository.getNeedById(smallNeedRepository.findByName("School Uniform").getId()));
        smallNeedList=smallNeedRepository.getList();
        assertThat(smallNeedList.size(),is(0));
    }

    @Test
    public void shouldEditSmallNeeds(){
        SmallNeed schoolUniformSmallNeed = new SmallNeed("School Uniform", 5000L, "For Aarthi",1);
        smallNeedRepository.addNeed(schoolUniformSmallNeed, 1);
        schoolUniformSmallNeed.setItemName("Air Cooler");
        smallNeedRepository.addNeed(schoolUniformSmallNeed, 1);
        List<SmallNeed> smallNeedList = smallNeedRepository.getList();
        assertThat(smallNeedList.size(),is(1));
        assertThat(smallNeedList.get(0).getItemName(),is("Air Cooler"));


    }

    @Override
    protected NeedRepository repository() {
        return smallNeedRepository;
    }

    @Override
    protected Need createNeed(String itemName, int cost, int priority) {
        return new SmallNeed(itemName, cost, "comment", priority);
    }


}
