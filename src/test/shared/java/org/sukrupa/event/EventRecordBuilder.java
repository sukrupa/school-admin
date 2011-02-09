package org.sukrupa.event;

import com.google.common.base.Joiner;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.sukrupa.student.Student;

import java.util.ArrayList;
import java.util.List;

import static org.sukrupa.event.EventRepository.ATTENDEES_SEPARATOR;

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

    public EventRecordBuilder attendees(String attendees) {
        this.attendees = attendees;
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
