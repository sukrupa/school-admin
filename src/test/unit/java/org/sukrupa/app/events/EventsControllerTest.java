package org.sukrupa.app.events;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.app.students.StudentsController;
import org.sukrupa.app.events.EventsController;
import org.sukrupa.event.*;
import org.sukrupa.platform.date.Date;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;
import java.util.*;
import java.io.*;


import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.MockitoAnnotations.initMocks;

public class EventsControllerTest {

    @Mock
    private EventService service;
    @Mock
    private EventForm eventform;

    private Map<String, List<Event>> eventModel = new HashMap<String, List<Event>>();
    private Map<String, Event> model = new HashMap<String, Event>();
    private EventsController controller;
    private Student attendee1 = new StudentBuilder().name("TC")
                                                    .dateOfBirth(new LocalDate(1987,12,12))
                                                    .studentId("123")
                                                    .build();
    private Event completeEvent = new EventBuilder().title("Spice Girls")
                                                    .attendees(attendee1)
                                                    .date(new Date(1,12,2011))
                                                    .description("Wahoo Spice Girls")
                                                    .build();

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        controller = new EventsController(service);
    }

    @Test
    public void shouldDisplayEventsPage() {
        assertThat(controller.list(eventModel), is("events/list"));
    }

    @Test
    public void shouldCreateANewEvent(){
        assertThat(controller.create(), is("events/create"));

    }

    @Test
    public void shouldEditANewEvent(){
        assertThat(controller.edit(eventform.getId(),model), is("events/edit"));
    }


}
