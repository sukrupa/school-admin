package org.sukrupa.event;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sukrupa.app.config.AppConfigForTestsContextLoader;
import org.sukrupa.platform.DatabaseHelper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigForTestsContextLoader.class)
public class ViewEventTest {

    private WebDriver driver = new HtmlUnitDriver();

    @Autowired
    private DatabaseHelper databaseHelper;
    private Event event;

    @After
    public void tearDown() throws Exception {
        databaseHelper.deleteAllCreatedObjects();
    }

    @Test
    public void shouldDisplayEvent() {
        event = save(new EventBuilder().build());

        ViewEventPage eventPage = new ViewEventPage(driver, event.getId());

        assertThat(eventPage.getTitle(), is(event.getTitle()));
        assertThat(eventPage.getVenue(), is(event.getVenue()));
    }

    private Event save(Event event) {
        databaseHelper.saveAndCommit(event);
        return event;
    }
}
