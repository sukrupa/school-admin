package org.sukrupa.app.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigForTestsContextLoader.class)
public class AppConfigTest {

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${db.root.dir}")
    private String hsqlDbRoot;

    @Autowired
    private Properties properties;

    @Test
    public void shouldWireUpCorrectly() {
        // nothing to do here
    }

    @Test
    public void shouldWireUpProperties() {
        assertThat(properties, hasKey("jdbc.url"));
    }

    @Test
    public void shouldResolvePropertyValues() {
        assertThat(jdbcUrl, containsString("jdbc:"));
    }

    @Test
    public void shouldNotAuthenticateByDefault() {
        assertThat(new AppConfig().properties().getProperty("web.server.authenticate"), is("false"));
    }

    @Test
    public void shouldAlwaysAuthenticateInProduction() {
        System.setProperty("environment", "production");
        assertThat(new AppConfig().properties().getProperty("web.server.authenticate"), is("true"));
    }

}
