package org.sukrupa.event;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.sukrupa.student.Student;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

public class EventBuilder {
    private String title;
    private String venue;
    private String coordinator;
    private String description;
    private String notes;
    private Set<Student> attendees;
    private EventDate datetime;

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

    public EventBuilder attendees(Set<Student> attendees) {
        this.attendees = attendees;
        return this;
    }

    public EventBuilder datetime(EventDate dateTime) {
        this.datetime = dateTime;
        return this;
    }

    public Event build() {
        return new Event(title, datetime, venue, coordinator, description, notes, attendees);
    }
}
