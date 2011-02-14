package org.sukrupa.event;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;
import org.sukrupa.platform.DoNotRemove;
import org.sukrupa.platform.date.Date;
import org.sukrupa.student.Student;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Event {

    @Id
    @GeneratedValue
    @Column(name = "event_id")
    private Integer id;

    @Column(name = "event_title")
    private String title;

    @Column(name = "event_venue")
    private String venue;

    @Column(name = "event_coordinator")
    private String coordinator;

    @Column(name = "event_description")
    private String description;

    @Column(name = "event_notes")
    private String notes;

    @Type(type = "org.sukrupa.platform.date.PersistentDate")
    private Date date;

    @ManyToMany
    @JoinTable(name = "EVENTATTENDEES",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "id")})
    private Set<Student> attendees;

    @DoNotRemove
    public Event() {
    }


    public Event(String title, Date date, String venue, String coordinator, String description, String notes, Set<Student> attendees) {
        this.title = title;
        this.date = date;
        this.venue = venue;
        this.coordinator = coordinator;
        this.description = description;
        this.notes = notes;
        this.attendees = attendees;
    }

    public static Event from(EventRecord eventRecord) {
        return new EventBuilder().title(eventRecord.getTitle())
                .venue(eventRecord.getVenue())
                .description(eventRecord.getDescription())
                .coordinator(eventRecord.getCoordinator())
                .notes(eventRecord.getNotes())
                .date(parseDateTime(eventRecord))
                .build();
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getDay() {
        return date.getDay();
    }

    public String getTime() {
        return date.getTime();
    }

    private static Date parseDateTime(EventRecord eventRecord) {
        return Date.parse(eventRecord.getDate(), eventRecord.getTime());
    }

    public void addAttendees(Set<Student> attendees) {
        this.attendees.addAll(attendees);
    }

    public Set<Student> getAttendees() {
        return attendees;
    }

    @Transient
    private String[] excludedFields = new String[]{"id"};

    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other, excludedFields);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, excludedFields);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }

    public String getVenue() {
        return venue;
    }

    public String getDescription() {
        return description;
    }

    public String getNotes() {
        return notes;
    }
}
