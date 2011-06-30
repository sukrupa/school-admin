package org.sukrupa.bigneeds;


import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BigNeedFormDataTest {

    @Test
    public void shouldCreateABigNeedFromFormData(){
        BigNeedFormData formData = new BigNeedFormData("Sample Big Need", "10000");
        BigNeed mySampleBigNeed = new BigNeed("Sample Big Need", 10000);
        BigNeed sampleBigNeed = formData.createBigNeed();
        assertThat(mySampleBigNeed.getItemName(), is(sampleBigNeed.getItemName()));
        assertThat(mySampleBigNeed.getCost(), is(sampleBigNeed.getCost()));
    }
}
