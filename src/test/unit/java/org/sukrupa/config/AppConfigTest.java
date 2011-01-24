package org.sukrupa.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigContextLoader.class)
public class AppConfigTest {

    @Test
    public void shouldNotFailIfSpringIsWiredUpCorrectly() {
        // nothing to do here
    }
}
