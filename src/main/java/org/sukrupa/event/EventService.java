package org.sukrupa.event;

import com.google.common.collect.Sets;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.platform.RequiredByFramework;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;

@Component
public class EventService {

    private EventRepository eventRepository;
    private StudentRepository studentRepository;

	private static final Logger LOG = Logger.getLogger(EventService.class);

    @RequiredByFramework
    EventService() {
    }

    @Autowired
    public EventService(EventRepository eventRepository, StudentRepository studentRepository) {
        this.eventRepository = eventRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Event save(EventForm eventForm) {
        Event event = eventForm.createEvent();
        try {
            String[] attendeesIDs = eventForm.getStudentIdsOfAttendees().toArray(new String[]{});
            if (attendeesIDs.length < 1){
                throw new Exception();
            }
            event.addAttendees(studentRepository.findByStudentIds(attendeesIDs));
//            invesegat diff
//            String[] arrayOfAttendees = eventParam.getAttendees().toArray(new String[]{});
//            setOfAttendees = studentRepository.findByStudentIds(arrayOfAttendees);
        } catch (Exception e) {
            // there aint no attendees
        }
        return eventRepository.save(event);
    }

    public Event getEvent(int eventId) {
            return eventRepository.load(eventId);
    }

    public List<Event> list() {
        return eventRepository.list();
    }

    @SuppressWarnings("ToArrayCallWithZeroLengthArrayArgument")
    public Event update(EventForm eventParam) {
        Event event = eventRepository.load(eventParam.getId());
        Set<Student> setOfAttendees = new HashSet<Student>();
        try{
            String[] arrayOfAttendees = eventParam.getAttendees().toArray(new String[]{});
            setOfAttendees = studentRepository.findByStudentIds(arrayOfAttendees);
        } catch (NullPointerException e){}
        event.updateFrom(eventParam, setOfAttendees);
        return eventRepository.update(event);

    }
	
}
