package org.sukrupa.bigneeds;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class BigNeedTest {

    @Test
    public void shouldCreatePowerGeneratorBigNeed(){
        BigNeed powerGeneratorBigNeed = new BigNeed("Power Generator", 50000);
        assertThat(powerGeneratorBigNeed.getItemName(),is("Power Generator"));
        assertThat(powerGeneratorBigNeed.getCost(), is(50000));
    }

    @Test
    public void shouldCreateComputerBigNeed(){
        BigNeed computerBigNeed = new BigNeed("Computer", 120000);
        assertThat(computerBigNeed.getItemName(),is("Computer"));
        assertThat(computerBigNeed.getCost(), is(120000));
    }
}
