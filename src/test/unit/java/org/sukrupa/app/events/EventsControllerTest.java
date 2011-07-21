package org.sukrupa.app.events;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.sukrupa.event.Event;
import org.sukrupa.event.EventForm;
import org.sukrupa.event.EventService;
import org.sukrupa.student.StudentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class EventsControllerTest {

    @Mock
    private EventService service;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private EventForm eventForm;
    @Mock
    private Event event1;
    @Mock
    private Event event2;

    private Map<String, List<Event>> eventModel = new HashMap<String, List<Event>>();
    private Map<String, Event> model = new HashMap<String, Event>();
    private Map<String, Object> objectModel = new HashMap<String, Object>();
    private List<String> listOfStudentIds = new ArrayList<String>();

    private Errors errors = new BeanPropertyBindingResult(eventForm, "EventForm");
    private EventsController controller;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        controller = new EventsController(service, studentRepository);
    }

    @Test
    public void shouldDisplayEventsPage() {
        String list = controller.getListOfEvents(eventModel);
        verify(service).list();
        assertThat("Displays the Events page", list, is("events/list"));

    }

    @Test
    public void shouldAddEventsToListOfEvents() {
        List<Event> ourEventList = new ArrayList<Event>();
        ourEventList.add(event1);
        ourEventList.add(event2);
        when(service.list()).thenReturn(ourEventList);
        controller.getListOfEvents(eventModel);
        List<Event> eventList = eventModel.get("events");
        assertTrue("EventList contains eventOne", eventList.contains(event1));
        assertTrue("EventList contains eventTwo", eventList.contains(event2));
    }

    @Test
    public void shouldDisplayCreateANewEventPage() {
        String actual = controller.createNewEventPage(objectModel);
        assertThat("Display the Create Event Page", actual, is("events/create"));
    }

    @Test
    public void shouldDisplayEditANewEventPage() {
        String edit = controller.getEditEventPage(eventForm.getId(), objectModel);
        assertThat("Display the edit Event Page", edit, is("events/edit"));
    }

    @Test
    public void shouldDisplayGivenEventPage() {
        when(service.getEvent(4)).thenReturn(event1);
        when(event1.getTitle()).thenReturn("Spice Girls");
        String view = controller.getAnEventView(4, objectModel);
        assertThat("Displays the Event Page for the given Event Id", view, is("events/view"));
        verify(service).getEvent(4);
        assertThat("Model contains the given event", ((Event) objectModel.get("event")).getTitle(), is("Spice Girls"));
    }

    @Test
    public void shouldDisplayTheEditEventPageForTheGivenEventId() {
        when(service.getEvent(4)).thenReturn(event1);
        String edit = controller.getEditEventPage(4, objectModel);
        assertThat("Display the Edit Event Page for the Given Id", edit, is("events/edit"));
        verify(service).getEvent(4);
        assertThat("Model contains the given event", ((Event) objectModel.get("event")), is(event1));
    }

    @Test
    public void shouldDisplayTheEditPageWhenUpdatingInvalidEvent() {
        Errors errors = new BeanPropertyBindingResult(eventForm, "EventForm");
        when(eventForm.isInvalid(errors)).thenReturn(true);
        String update = controller.updateAnEvent("4", eventForm, objectModel);
        verify(eventForm).isInvalid(errors);
        assertThat("Display the Edit Event Page", update, is("events/edit"));
    }

    @Test
    public void shouldDisplayTheEditPageWhenUpdatingInvalidEventWithAttendees() {
        Errors errors = new BeanPropertyBindingResult(eventForm, "EventForm");
        when(eventForm.isInvalid(errors)).thenReturn(true);
        when(eventForm.getAttendees()).thenReturn(listOfStudentIds);
        String update = controller.updateAnEvent("4", eventForm, objectModel);
        verify(eventForm).getAttendees();
        assertThat("Display the Edit Event Page", update, is("events/edit"));
    }

    @Test
    public void shouldDisplayTheEventPageWhenUpdatingEventWithAttendees() {
        Errors errors = new BeanPropertyBindingResult(eventForm, "EventForm");
        when(eventForm.isInvalid(errors)).thenReturn(false);
        when(eventForm.getAttendees()).thenReturn(listOfStudentIds);
        String update = controller.updateAnEvent("4", eventForm, objectModel);
        verify(eventForm).getAttendees();
        assertThat("Display the Edit Event Page", update, is("redirect:/events/4"));
    }

    @Test
    public void shouldDisplayTheEventPageWhenUpdatingEventWithoutAttendees() {
        Errors errors = new BeanPropertyBindingResult(eventForm, "EventForm");
        when(eventForm.isInvalid(errors)).thenReturn(false);
        when(eventForm.getAttendees()).thenReturn(null);
        String update = controller.updateAnEvent("4", eventForm, objectModel);
        verify(eventForm).getAttendees();
        assertThat("Display the Edit Event Page", update, is("redirect:/events/4"));
    }

    @Test
    public void shouldDisplayEventPageAfterSuccesfullyUpdating() {
        String update = controller.updateAnEvent("4", eventForm, objectModel);
        assertThat("Display the Event Page", update, is("redirect:/events/4"));
        verify(service).update(eventForm);
    }

    @Test
    public void shouldDisplayCreatePageWhenThereIsErrorInSaving() {
        Errors errors = new BeanPropertyBindingResult(eventForm, "EventForm");
        when(eventForm.isInvalid(errors)).thenReturn(true);
        String save = controller.saveANewEvent(eventForm, objectModel);
        assertThat("display Create Event Page", save, is("events/create"));
    }

    @SuppressWarnings("ToArrayCallWithZeroLengthArrayArgument")
    @Test
    public void shouldDisplayEventsPageAfterSuccesfullySaving() {
        Errors errors = mock(Errors.class);
        when(eventForm.isInvalid(errors)).thenReturn(false);
        when(event1.getId()).thenReturn(4);
        when(service.save(eventForm)).thenReturn(event1);
        String save = controller.saveANewEvent(eventForm, objectModel);
        assertThat("Display Event Page after Successful Save", save, is("redirect:/events/4"));
        verify(service).save(eventForm);
    }
}
