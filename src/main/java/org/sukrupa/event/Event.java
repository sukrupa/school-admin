package org.sukrupa.event;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.sukrupa.platform.DoNotRemove;
import org.sukrupa.student.Student;

import javax.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue
    @Column ( name = "event_id" )
    private int eventId;

    @Column ( name = "event_title" )
    private String title;

    @Column ( name = "event_date" )
    private Date date;

    @Column ( name = "event_time" )
    private Time time;

    @Column ( name = "event_venue" )
    private String venue;

    @Column ( name = "event_coordinator" )
    private String coordinator;

    @Column ( name = "event_description" )
    private String description;

    @Column ( name = "event_notes" )
    private String notes;

    @Transient
    private Set<Student> attendees;

    @DoNotRemove
    public Event() {
    }

    public Event(String title, Date date, Time time, String venue, String coordinator, String description, String notes, Set<Student> attendees)
     {
        this.title=title;
        this.date=date;
        this.time=time;
        this.venue=venue;
        this.coordinator=coordinator;
        this.description=description;
        this.notes=notes;
        this.attendees=attendees;
    }

    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "EventAttendees", joinColumns = { @JoinColumn(name = "event_id") }, inverseJoinColumns = { @JoinColumn(name = "student_id") })
    public Set<Student> getAttendees() {
        return attendees;
    }
}
