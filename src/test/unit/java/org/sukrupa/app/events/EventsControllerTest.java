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
import static junit.framework.Assert.assertTrue;
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
        //Act
        String list = controller.list(eventModel);

        //Assert
        assertThat("Displays the Events page",list, is("events/list"));
    }

    @Test
    public void shouldAddEventsToListOfEvents() {

        //Arrange
        List<Event> ourEventList = new ArrayList<Event>();
        ourEventList.add(eventOne);
        ourEventList.add(eventTwo);
        when(service.list()).thenReturn(ourEventList);

        //Act
        controller.list(eventModel);
        List<Event> eventList = eventModel.get("events");

        //Assert
        assertTrue("Returns True if eventList contains eventOne",eventList.contains(eventOne));
        assertTrue("Returns True if eventList contains eventTwo",eventList.contains(eventTwo));

    }

    @Test
    public void shouldCreateANewEvent() {
        //Act
        String actual = controller.create();

        //Assert
        assertThat("Redirects to the Create Event Page",actual, is("events/create"));

    }

    @Test
    public void shouldEditANewEvent() {
        //Act
        String edit = controller.edit(eventForm.getId(), model);

        //Assert
        assertThat("Redirect to edit Event Page",edit, is("events/edit"));
    }

    @Test
    public void shouldDisplayAssosciatedEventGivenTheEventId() {

        //Arrange
        when(service.getEvent(4)).thenReturn(eventOne);

        //Act
        String view = controller.view(4, model);

        //Assert
        assertThat("Displays the Event Page for the given Event Id",view, is("events/view"));
        verify(service).getEvent(4);
        assertThat("Checks whether the model has the concerned event by comparing title",model.get("event").getTitle(), is("Spice Girls"));

    }

    @Test
    public void shouldRedirectToEditEventPageForTheConcernedEventId() {

        //Arrange
        when(service.getEvent(4)).thenReturn(eventOne);

        //Act
        String edit = controller.edit(4, model);

        //Assert
        assertThat("Redirect to the concerned Edit Page based on the Ids",edit, is("events/edit"));
        verify(service).getEvent(4);
        assertThat("Checks whether the model has the concerned event by comparing Date",model.get("event").getDate().year(), is(2011));
    }

    @Test
    public void shouldRedirectBackToEditPageFoInvalidEventForm() {
        //Arrange
        Errors errors = new BeanPropertyBindingResult(eventForm, "EventForm");
        when(eventForm.isInvalid(errors)).thenReturn(true);

        //Act
        String update = controller.update("4", eventForm, objectModel);

        //Assert
        assertThat("Redirect back to edit when event form contains errors",update, is("events/edit"));
    }

    @Test
    public void shouldRedirectToBackEditPageForInvalidAttendees() {

       //Arrange
        HashSet<String> idList = new HashSet<String>();
        idList.add("111");
        idList.add("123");
        Set<String> studentIdsOfAttendees = eventForm.getStudentIdsOfAttendees();
        when(service.validateStudentIdsOfAttendees(studentIdsOfAttendees)).thenReturn(idList);
        Set<String> invalidAttendees = service.validateStudentIdsOfAttendees(studentIdsOfAttendees);

        //Act
        String update = controller.update("4", eventForm, objectModel);

        //Assert
        assertThat("Redirect back to edit when there are invalid Attendees",update, is("events/edit"));

    }

    @Test
    public void shouldRedirectToEventsPageAfterSuccesfullyUpdating() {
        //Act
        String update = controller.update("4", eventForm, objectModel);

        //Assert
        assertThat("Redirect to the concerned Event Page based on Ids After Successful Update",update,is("redirect:/events/4"));
        verify(service).update(eventForm);
    }

    @Test
    public void shouldRedirectBackToCreatePageForErrorWhenSaving() {
        //Arrange
        Errors errors = new BeanPropertyBindingResult(eventForm, "EventForm");
        when(eventForm.isInvalid(errors)).thenReturn(true);

        //Act
        String save = controller.save(eventForm, objectModel);

        //Assert
        assertThat("Redirect back to save when event form contains errors", save, is("events/create"));


    }

    @Test
    public void shouldRedirectBackToCreatePageForInvalidAttendeesWhenSaved() {
        //Arrange
        HashSet<String> idList = new HashSet<String>();
        idList.add("111");
        idList.add("123");

        Set<String> studentIdsOfAttendees = eventForm.getStudentIdsOfAttendees();
        when(service.validateStudentIdsOfAttendees(studentIdsOfAttendees)).thenReturn(idList);
        Set<String> invalidAttendees = service.validateStudentIdsOfAttendees(studentIdsOfAttendees);

        //Act
        String save = controller.save(eventForm, objectModel);

        //Assert
        assertThat("Redirect back to save when there are invalid attendees",save, is("events/create"));

    }

    @Test
    public void shouldRedirectToEventsPageAfterSuccesfullySaving() {

        //Arrange
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

        //Act
        String save = controller.save(eventForm, objectModel);

        //Assert
        assertThat("Redirect to the concerned Event Page based on Ids After Successful Save",save, is("redirect:/events/4"));
        verify(eventForm).createEvent();
        verify(service).save(event, studentIdsOfAttendees.toArray(new String[]{}));







    }



}
