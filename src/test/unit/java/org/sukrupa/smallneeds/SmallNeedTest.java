package org.sukrupa.smallneeds;


import org.hamcrest.Matcher;
import org.junit.Test;
import org.sukrupa.smallNeeds.SmallNeed;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SmallNeedTest {

    @Test
    public void shouldCreateSchoolUniformSmallNeed(){
        SmallNeed schoolUniformSmallNeed=new SmallNeed("School Uniform",5000L,"For Aarthi");
        assertThat(schoolUniformSmallNeed.getItemName(), is("School Uniform"));
        assertThat(schoolUniformSmallNeed.getCost(), (Matcher<? super Long>) is(5000L));
        assertThat(schoolUniformSmallNeed.getComment(), is("For Aarthi"));
    }

}