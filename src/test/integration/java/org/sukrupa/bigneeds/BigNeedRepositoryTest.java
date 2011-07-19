package org.sukrupa.bigneeds;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.needs.Need;
import org.sukrupa.needs.NeedRepository;
import org.sukrupa.needs.NeedRepositoryTestBase;
import org.sukrupa.platform.config.SpringContextLoaderForTesting;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringContextLoaderForTesting.class)
@Transactional
public class BigNeedRepositoryTest extends NeedRepositoryTestBase {

    private BigNeedRepository bigNeedRepository;

    @Override
    protected NeedRepository repository() {
        return bigNeedRepository;
    }

    @Override
    protected Need createNeed(String itemName, int cost, int priority) {
        return new BigNeed(itemName, cost, priority);
    }


    @Before
    public void setUp() {
        bigNeedRepository = new BigNeedRepository(sessionFactory);
    }

    @Test
    public void shouldSaveTheFirstBigNeed() {
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
    public void shouldRetrieveBigNeedList() {
        assertRetrieveNeedList();
    }

    @Test
    public void shouldDeleteRecordAndAdjustThePriorites() {
        assertDeleteAdjustsPriorities();
    }

    @Test
    public void shouldDeleteBigNeed() {
        assertDelete();
    }

    @Test
    public void shouldGetBigNeedById() {
        assertGetNeedById();
        BigNeed example = new BigNeed("example", 10000, 2);
        sessionFactory.getCurrentSession().save(example);
        long id = example.getId();
        assertThat(bigNeedRepository.getNeedById(id), is(example));
    }

    @Test
    public void shouldCheckIfDonatedAmountEqualsCost(){
        BigNeed powerGenerator = new BigNeed("Power Generator", 50000, 1, 0.0);
        BigNeed airConditioner = new BigNeed("Air Conditioner", 20000, 2, 20000);
        assertThat(bigNeedRepository.isDonatedAmountFulfilled(powerGenerator), is(false));
        assertThat(bigNeedRepository.isDonatedAmountFulfilled(airConditioner), is(true));
    }
}

