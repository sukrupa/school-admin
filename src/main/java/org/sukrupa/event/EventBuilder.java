package org.sukrupa.event;

import org.sukrupa.student.Student;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class EventBuilder {
    private String title = "dummy.title";
    private String venue = "dummy.venue";
    private String coordinator = "dummy.coordinator";
    private String description = "dummy.description";
    private String notes = "dummy.notes" ;
    private Set<Student> attendees = new HashSet<Student>();
    private Date date = Date.now();

    public EventBuilder title(String title) {
        this.title = title;
        return this;
    }

    public EventBuilder venue(String venue) {
        this.venue = venue;
        return this;
    }

    public EventBuilder coordinator(String coordinator) {
        this.coordinator = coordinator;
        return this;
    }

    public EventBuilder description(String description) {
        this.description = description;
        return this;
    }

    public EventBuilder notes(String notes) {
        this.notes = notes;
        return this;
    }

    public EventBuilder date(Date date) {
        this.date = date;
        return this;
    }

    public EventBuilder attendees(Set<Student> attendees) {
        this.attendees = attendees;
        return this;
    }

    public EventBuilder attendees(Student... students) {
        this.attendees = newHashSet(students);
        return this;
    }

    public Event build() {
        return new Event(title, date, venue, coordinator, description, notes, attendees);
    }


}
