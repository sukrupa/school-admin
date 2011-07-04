package org.sukrupa.app.events;

import org.apache.james.mime4j.io.LimitedInputStream;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hibernate.mapping.Array;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.sukrupa.app.students.StudentsController;
import org.sukrupa.app.events.EventsController;
import org.sukrupa.event.*;
import org.sukrupa.platform.date.Date;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;

import java.security.PrivateKey;
import java.util.*;
import java.io.*;


import java.util.HashMap;
import java.util.Map;

import static java.text.MessageFormat.format;
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
    private EventForm eventForm;

    private Errors errors;
  @Mock
  private Event event;

    private Map<String, List<Event>> eventModel = new HashMap<String, List<Event>>();
    private Map<String, Event> model = new HashMap<String, Event>();
    private Map<String, Object> objectModel = new HashMap<String, Object>();
    private EventsController controller;
    private Student attendee1 = new StudentBuilder().name("TC")
            .dateOfBirth(new LocalDate(1987, 12, 12))
            .studentId("123")
            .build();
    private Student attendee2 = new StudentBuilder().name("Hephzibah")
            .dateOfBirth(new LocalDate(1989, 9, 12))
            .studentId("231")
            .build();

    private Event eventOne = new EventBuilder().title("Spice Girls")
            .attendees(attendee1, attendee2)
            .date(new Date(1, 12, 2011))
            .description("Wahoo Spice Girls")
            .build();
    private Event eventTwo = new EventBuilder().title("Aravind's Event")
            .attendees(attendee1)
            .date(new Date(4, 07, 2011))
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
    public void retrieveEventListToModel() {
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
    public void shouldCreateANewEvent() {
        assertThat(controller.create(), is("events/create"));

    }

    @Test
    public void shouldEditANewEvent() {
        assertThat(controller.edit(eventForm.getId(), model), is("events/edit"));
    }

    @Test
    public void shouldReturnEventForTheId() {
        when(service.getEvent(4)).thenReturn(eventOne);
        assertThat(controller.view(4, model), is("events/view"));
        verify(service).getEvent(4);
        assertThat(model.get("event").getTitle(), is("Spice Girls"));

    }

    @Test
    public void shouldEditEventById() {
        when(service.getEvent(4)).thenReturn(eventOne);
        assertThat(controller.edit(4, model), is("events/edit"));
        verify(service).getEvent(4);
        assertThat(model.get("event").getDate().year(), is(2011));
    }

    @Test
    public void shouldReturnToEditPageForInvalidEventForm() {
        Errors errors = new BeanPropertyBindingResult(eventForm, "EventForm");
        when(eventForm.isInvalid(errors)).thenReturn(true);
        assertThat(controller.update("4", eventForm, objectModel), is("events/edit"));
    }

    @Test
    public void shouldReturnToEditPageForInvalidAttendees() {
        HashSet<String> idList = new HashSet<String>();
        idList.add("111");
        idList.add("123");
        Set<String> studentIdsOfAttendees = eventForm.getStudentIdsOfAttendees();
        when(service.validateStudentIdsOfAttendees(studentIdsOfAttendees)).thenReturn(idList);
        Set<String> invalidAttendees = service.validateStudentIdsOfAttendees(studentIdsOfAttendees);
        assertThat(controller.update("4", eventForm, objectModel), is("events/edit"));



    }

    @Test
    public void shouldRedirectToID() {
        assertThat(controller.update("4", eventForm, objectModel), is("redirect:/events/4"));
        verify(service).update(eventForm);
    }

    @Test
    public void shouldReturntoCreatePageForErrorWhenSaving() {
        Errors errors = new BeanPropertyBindingResult(eventForm, "EventForm");
        when(eventForm.isInvalid(errors)).thenReturn(true);
        assertThat(controller.save(eventForm, objectModel), is("events/create"));


    }

    @Test
    public void shouldReturnToCreatePageForInvalidAttendeesWhenSaved() {
        HashSet<String> idList = new HashSet<String>();
        idList.add("111");
        idList.add("123");

        Set<String> studentIdsOfAttendees = eventForm.getStudentIdsOfAttendees();
        when(service.validateStudentIdsOfAttendees(studentIdsOfAttendees)).thenReturn(idList);
        Set<String> invalidAttendees = service.validateStudentIdsOfAttendees(studentIdsOfAttendees);
        assertThat(controller.save(eventForm, objectModel), is("events/create"));

    }

    @Test
    public void shouldRedirectIDWhenSaved() {
        when(eventForm.isInvalid(errors)).thenReturn(false);
        when(eventForm.createEvent()).thenReturn(event);
        when(event.getId()).thenReturn(4);
        Set<String> studentIdsOfAttendees = new HashSet<String>();
        studentIdsOfAttendees.add("123");
        studentIdsOfAttendees.add("231");
        when(eventForm.getStudentIdsOfAttendees()).thenReturn(studentIdsOfAttendees);
        Set<String> invalidAttendees = new HashSet<String>();
        invalidAttendees.clear();
        when(service.validateStudentIdsOfAttendees(studentIdsOfAttendees)).thenReturn(invalidAttendees);
        assertThat(controller.save(eventForm, objectModel), is("redirect:/events/4"));
        verify(eventForm).createEvent();
        verify(service).save(event, studentIdsOfAttendees.toArray(new String[]{}));

    }



}
