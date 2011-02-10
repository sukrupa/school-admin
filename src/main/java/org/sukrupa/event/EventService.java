package org.sukrupa.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sukrupa.student.StudentRepository;

@Component
public class EventService {

    private final EventRepository eventRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public EventService(EventRepository eventRepository, StudentRepository studentRepository) {
        this.eventRepository = eventRepository;
        this.studentRepository = studentRepository;
    }

    public void save(Event event, String... studentIdsOfAttendees) {
        event.addAttendees(studentRepository.load(studentIdsOfAttendees));
        eventRepository.save(event);
    }

    public Event getEvent(int eventId) {
        return eventRepository.getEvent(eventId);
    }
}
