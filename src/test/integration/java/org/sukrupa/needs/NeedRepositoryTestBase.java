package org.sukrupa.needs;


import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public abstract class NeedRepositoryTestBase {
    @Autowired
    protected SessionFactory sessionFactory;

    protected abstract NeedRepository repository();

    protected abstract Need createNeed(String itemName, int cost, int priority);

    protected SessionFactory session() {
        return sessionFactory;
    }

    protected void assertSavingFirstNeed() {
        Need powerGenerator = createNeed("Power Generator", 150000,1);
        repository().addNeed(powerGenerator, 1);

        Need retrievedNeed = repository().findByName("Power Generator");
        assertThat(retrievedNeed.getItemName(), is(powerGenerator.getItemName()));
        assertThat(retrievedNeed.getCost(), is(powerGenerator.getCost()));
        assertThat(retrievedNeed.getPriority(), is(powerGenerator.getPriority()));
    }

    protected void assertInsertNeed() {
        Need powerGenerator = createNeed("Power Generator", 150000,1);
        repository().addNeed(powerGenerator, 1);

        Need airConditioner = createNeed("Air Conditioner", 20000,1);
        repository().addNeed(airConditioner, 1);

        MatcherAssert.assertThat(repository().findByName(airConditioner.getItemName()).getPriority(), is(1));
        MatcherAssert.assertThat(repository().findByName(powerGenerator.getItemName()).getPriority(), is(2));
    }

    protected void assertUpdateToHigherPriority() {
        Need powerGenerator = createNeed("Power Generator", 50000, 1);
        Need airConditioner = createNeed("Air Conditioner", 20000, 2);
        Need largeBed = createNeed("Big Large Bed", 20000, 3);

        session().getCurrentSession().save(powerGenerator);
        session().getCurrentSession().save(airConditioner);
        session().getCurrentSession().save(largeBed);

        repository().editNeed(largeBed, 2);

        MatcherAssert.assertThat(repository().findByName(powerGenerator.getItemName()).getPriority(), is(1));
        MatcherAssert.assertThat(repository().findByName(largeBed.getItemName()).getPriority(), is(2));
        MatcherAssert.assertThat(repository().findByName(airConditioner.getItemName()).getPriority(), is(3));
    }

    protected void assertUpdateToHighestPriority() {
        Need powerGenerator = createNeed("Power Generator", 50000, 1);
        Need airConditioner = createNeed("Air Conditioner", 20000, 2);
        Need largeBed = createNeed("Big Large Bed", 20000, 3);

        session().getCurrentSession().save(powerGenerator);
        session().getCurrentSession().save(airConditioner);
        session().getCurrentSession().save(largeBed);

        repository().editNeed(largeBed, 1);

        MatcherAssert.assertThat(repository().findByName(largeBed.getItemName()).getPriority(), is(1));
        MatcherAssert.assertThat(repository().findByName(powerGenerator.getItemName()).getPriority(), is(2));
        MatcherAssert.assertThat(repository().findByName(airConditioner.getItemName()).getPriority(), is(3));
    }

    protected void assertInsertWithGreaterThanExistingPriority() {
        Need powerGenerator = createNeed("Power Generator", 50000, 1);
        session().getCurrentSession().save(powerGenerator);

        Need airConditioner = createNeed("Air Conditioner", 20000,1);
        repository().addNeed(airConditioner, 3);

        MatcherAssert.assertThat(1, Matchers.is(repository().findByName(powerGenerator.getItemName()).getPriority()));
        MatcherAssert.assertThat(2, Matchers.is(repository().findByName(airConditioner.getItemName()).getPriority()));
    }

    protected void assertUpdateWithGreaterThanExistingPriority() {
        Need powerGenerator = createNeed("Power Generator", 50000, 1);
        Need airConditioner = createNeed("Air Conditioner", 20000, 2);
        Need largeBed = createNeed("Big Large Bed", 20000, 3);

        session().getCurrentSession().save(powerGenerator);
        session().getCurrentSession().save(airConditioner);
        session().getCurrentSession().save(largeBed);

        repository().editNeed(powerGenerator, 5);

        MatcherAssert.assertThat(repository().findByName(airConditioner.getItemName()).getPriority(), is(1));
        MatcherAssert.assertThat(repository().findByName(largeBed.getItemName()).getPriority(), is(2));
        MatcherAssert.assertThat(repository().findByName(powerGenerator.getItemName()).getPriority(), is(3));
    }

    protected void assertHasTheSamePriorityIfPriorityHasNotChanged() {
        Need powerGenerator = createNeed("Power Generator", 50000, 1);
        Need airConditioner = createNeed("Air Conditioner", 20000, 2);
        Need largeBed = createNeed("Big Large Bed", 20000, 3);

        session().getCurrentSession().save(powerGenerator);
        session().getCurrentSession().save(airConditioner);
        session().getCurrentSession().save(largeBed);

        airConditioner.setItemName("Projector");

        repository().editNeed(airConditioner, 2);

        MatcherAssert.assertThat(repository().findByName(airConditioner.getItemName()).getPriority(), is(2));
        MatcherAssert.assertThat(repository().findByName(largeBed.getItemName()).getPriority(), is(3));
        MatcherAssert.assertThat(repository().findByName(powerGenerator.getItemName()).getPriority(), is(1));
    }

    protected void assertUpdateToLowerPriority() {
        Need powerGenerator = createNeed("Power Generator", 50000, 1);
        Need airConditioner = createNeed("Air Conditioner", 20000, 2);
        Need largeBed = createNeed("Big Large Bed", 20000, 3);


        session().getCurrentSession().save(powerGenerator);
        session().getCurrentSession().save(airConditioner);
        session().getCurrentSession().save(largeBed);

        repository().editNeed(airConditioner, 2);

        MatcherAssert.assertThat(repository().findByName(airConditioner.getItemName()).getPriority(), is(2));
        MatcherAssert.assertThat(repository().findByName(powerGenerator.getItemName()).getPriority(), is(1));
        MatcherAssert.assertThat(repository().findByName(largeBed.getItemName()).getPriority(), is(3));
    }

    protected void assertUpdateToLowestPriority() {
        Need powerGenerator = createNeed("Power Generator", 50000, 1);
        Need airConditioner = createNeed("Air Conditioner", 20000, 2);
        Need largeBed = createNeed("Big Large Bed", 20000, 3);

        session().getCurrentSession().save(powerGenerator);
        session().getCurrentSession().save(airConditioner);
        session().getCurrentSession().save(largeBed);

        repository().editNeed(powerGenerator, 3);

        MatcherAssert.assertThat(repository().findByName(airConditioner.getItemName()).getPriority(), is(1));
        MatcherAssert.assertThat(repository().findByName(largeBed.getItemName()).getPriority(), is(2));
        MatcherAssert.assertThat(repository().findByName(powerGenerator.getItemName()).getPriority(), is(3));
    }

    protected void assertRetrieveNeedList() {
        Need computerNeed = createNeed("Computer", 120000, 1);
        Need airConditionerNeed = createNeed("Air Conditioner", 20000, 4);
        session().getCurrentSession().save(computerNeed);
        session().getCurrentSession().save(airConditionerNeed);
        List<Need> needList = repository().getList();
        assertThat(needList.isEmpty(), is(false));
    }

    protected void assertDeleteAdjustsPriorities() {
        Need powerGenerator = createNeed("Power Generator", 50000, 1);
        Need airConditioner = createNeed("Air Conditioner", 20000, 2);
        Need largeBed = createNeed("Big Large Bed", 20000, 3);

        session().getCurrentSession().save(powerGenerator);
        session().getCurrentSession().save(airConditioner);
        session().getCurrentSession().save(largeBed);

        repository().delete(airConditioner);

        MatcherAssert.assertThat(repository().findByName(powerGenerator.getItemName()).getPriority(), is(1));
        MatcherAssert.assertThat(repository().findByName(largeBed.getItemName()).getPriority(), is(2));
        assertNull(repository().findByName(airConditioner.getItemName()));
    }

    protected void assertDelete() {
        Need banana = createNeed("Banana", 25000, 3);
        session().getCurrentSession().save(banana);

        MatcherAssert.assertThat((List<Need>) repository().getList(), hasItem(banana));
        repository().delete(banana);
        MatcherAssert.assertThat((List<Need>)repository().getList(), not(hasItem(banana)));
    }

    protected void assertGetNeedById() {
        Need example = createNeed("example", 10000, 2);
        session().getCurrentSession().save(example);

        long id = example.getId();
        MatcherAssert.assertThat(repository().getNeedById(id), is(example));
    }

    @Test
    public void placeholder(){
        assertTrue(true);
    }
}
