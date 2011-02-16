package org.sukrupa.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.platform.DoNotRemove;
import org.sukrupa.student.StudentRepository;

import java.util.List;

@Component
public class EventService {

    private EventRepository eventRepository;
    private StudentRepository studentRepository;

    @DoNotRemove
    EventService() {
    }

    @Autowired
    public EventService(EventRepository eventRepository, StudentRepository studentRepository) {
        this.eventRepository = eventRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void save(Event event, String... studentIdsOfAttendees) {
        event.addAttendees(studentRepository.load(studentIdsOfAttendees));
        eventRepository.save(event);
    }

    public Event getEvent(int eventId) {
        return eventRepository.load(eventId);
    }

    public List<Event> list() {
        return eventRepository.list();
    }
}
