package org.sukrupa.smallneeds;


import org.hamcrest.Matcher;
import org.junit.Test;
import org.sukrupa.smallNeeds.SmallNeed;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class SmallNeedTest {

    @Test
    public void shouldCreateSchoolUniformSmallNeed(){
        SmallNeed schoolUniformSmallNeed=new SmallNeed("School Uniform",5000L,"For Aarthi",1);
        assertThat(schoolUniformSmallNeed.getItemName(), is("School Uniform"));
        assertThat(schoolUniformSmallNeed.getComment(), is("For Aarthi"));
        assertThat(schoolUniformSmallNeed.getPriority(), is(1));
        assertThat(schoolUniformSmallNeed.getId(), is(0L));
    }

}
