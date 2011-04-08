package org.sukrupa.student;


import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.platform.date.Date;
import org.sukrupa.platform.date.DateManipulation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SystemEventLogTest {


    SystemEventLog eventLog;
    LocalDate oldDate;
    LocalDate newDate;

    @Before
    public void setUp() {


        oldDate = new LocalDate(2011,4,5);
        eventLog = new SystemEventLog("annual class update",oldDate);
    }
    @After
    public void tearDown() {
    }



    @Test
    public void shouldUpdateWhenPassedAMoreRecentDate() {
        newDate = new LocalDate(2011,4,8);
        eventLog.newEntry(newDate);
        assertThat(eventLog.lastHappened(),is(newDate));
    }

    @Test
    public void shouldNotUpdateWhenPassedAPreviousDate() {
        newDate = new LocalDate(2011,4,1);
        eventLog.newEntry(newDate);
        assertThat(eventLog.lastHappened(),is(oldDate));
    }





}
