package org.sukrupa.event;

import org.apache.commons.lang.StringUtils;
import org.sukrupa.student.Student;

import java.util.ArrayList;
import java.util.List;

public class EventRecordBuilder {
    private String title;
    private String date;
    private String time;
    private String venue;
    private String coordinator;
    private String description;
    private String notes;
    private String attendees;

    public EventRecordBuilder title(String title) {
        this.title = title;
        return this;
    }

    public EventRecordBuilder date(String date) {
        this.date = date;
        return this;
    }

    public EventRecordBuilder time(String time) {
        this.time = time;
        return this;
    }

    public EventRecordBuilder description(String description) {
        this.description = description;
        return this;
    }

    public EventRecordBuilder notes(String notes) {
        this.notes = notes;
        return this;
    }

    public EventRecordBuilder attendees(String... studentIds) {
        this.attendees = StringUtils.join(studentIds, ",");
        return this;
    }

    public EventRecordBuilder attendees(Student... attendees) {
        List<String> studentIds = new ArrayList<String>();
        for (Student attendee : attendees) {
            studentIds.add(attendee.getStudentId());
        }

        this.attendees = StringUtils.join(studentIds, ",");
        return this;
    }

    public EventRecord build() {
        return new EventRecord(title, date, time, venue, coordinator, description, notes, attendees);
    }
}
