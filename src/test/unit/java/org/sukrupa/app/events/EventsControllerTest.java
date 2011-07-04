package org.sukrupa.app.events;

import org.apache.james.mime4j.io.LimitedInputStream;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hibernate.mapping.Array;
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
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
    private Student attendee2 = new StudentBuilder().name("Hephzibah")
                                                     .dateOfBirth(new LocalDate(1989,9,12))
                                                     .studentId("231")
                                                     .build();

    private Event eventOne = new EventBuilder().title("Spice Girls")
                                                    .attendees(attendee1,attendee2)
                                                    .date(new Date(1,12,2011))
                                                    .description("Wahoo Spice Girls")
                                                    .build();
    private Event eventTwo = new EventBuilder().title("Aravind's Event")
                                                    .attendees(attendee1)
                                                    .date(new Date(4,07,2011))
                                                    .description("Testing Aravind's event")
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
    public void retrieveEventListToModel()
    {
        List<Event> ourEventList = new ArrayList<Event>();
        ourEventList.add(eventOne);
        ourEventList.add(eventTwo);
        when(service.list()).thenReturn(ourEventList);
        controller.list(eventModel);
        List<Event> eventList = (List<Event>) eventModel.get("events");
        assertThat(eventList.contains(eventOne), CoreMatchers.is(true));
        assertThat(eventList.contains(eventTwo), CoreMatchers.is(true));

    }

    @Test
    public void shouldCreateANewEvent(){
        assertThat(controller.create(), is("events/create"));

    }

    @Test
    public void shouldEditANewEvent(){
        assertThat(controller.edit(eventform.getId(),model), is("events/edit"));
    }

    @Test
    public void shouldReturnEventForTheId()
    {
        when(service.getEvent(4)).thenReturn(eventOne);
        assertThat(controller.view(4, model), is("events/view"));
        verify(service).getEvent(4);
        assertThat(model.get("event").getTitle(),is("Spice Girls"));

    }

    @Test
    public void shouldEditEventById()
    {
        when(service.getEvent(4)).thenReturn(eventOne);
        assertThat(controller.edit(4,model),is("events/edit"));
        verify(service).getEvent(4);
        assertThat(model.get("event").getDate().year(),is(2011));
    }



}
