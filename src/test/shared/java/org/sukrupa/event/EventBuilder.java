package org.sukrupa.event;

import org.joda.time.DateTime;
import org.sukrupa.student.Student;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ppotter
 * Date: 02/02/2011
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 */
public class EventBuilder {
      private String title;
      private Date date;
      private Time time;
      private String venue;
      private String coordinator;
      private String description;
      private String notes;
      private Set<Student> attendees;
    private DateTime datetime;

    public EventBuilder title(String title){
        this.title = title;
        return this;
    }
    public EventBuilder date(Date date){
        this.date = date;
        return this;
    }
    public EventBuilder time(Time time){
        this.time = time;
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
    public EventBuilder datetime(DateTime dateTime) {
        this.datetime = dateTime;
        return this;
    }
    public Event build(){
        return new Event(title,date,time,datetime,venue,coordinator,description,notes,attendees);
    }
}