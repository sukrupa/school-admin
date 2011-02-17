package org.sukrupa.event;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;
import org.sukrupa.platform.db.HibernateConstructor;
import org.sukrupa.platform.date.Date;
import org.sukrupa.student.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Proxy(lazy = false)
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
    @JoinTable(name = "EVENT_ATTENDEES",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "STUDENT_ID")})
    private Set<Student> attendees;

    @HibernateConstructor
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

    public Event(String title, Date date, String venue, String coordinator, String description, String notes) {
        this(title, date, venue, coordinator, description, notes, new HashSet<Student>());
    }

    public static Event createFrom(EventCreateParameter eventCreateParameter) {
        return new Event(eventCreateParameter.getTitle(),
                Date.parse(eventCreateParameter.getDate(),
		        eventCreateParameter.getTime()),
                eventCreateParameter.getVenue(),
                eventCreateParameter.getCoordinator(),
                eventCreateParameter.getDescription(),
                eventCreateParameter.getNotes());
    }

    public static Event from(EventCreateParameter eventCreateParameter) {
        String venue = nullIfEmpty(eventCreateParameter.getVenue());
        String coordinator = nullIfEmpty(eventCreateParameter.getCoordinator());
        String notes = nullIfEmpty(eventCreateParameter.getNotes());

        return new Event(eventCreateParameter.getTitle(), parseDateTime(eventCreateParameter),
                venue, coordinator, eventCreateParameter.getDescription(), notes, null);
    }

    private static String nullIfEmpty(String value) {
        return (value.isEmpty()) ? null : value;
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
        return date.getTime().equals("00:00") ? null: date.getTime();
    }

    private static Date parseDateTime(EventCreateParameter eventCreateParameter) {
        return Date.parse(eventCreateParameter.getDate(), eventCreateParameter.getTime());
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

    public String getAttendeesForDisplay() {
        return StringUtils.join(getAttendeeNames(), ", ");
    }

    private List<String> getAttendeeNames() {
        List<String> attendeeNameList = new ArrayList<String>();
        for (Student attendee : attendees) {
            attendeeNameList.add(attendee.getName());
        }
        return attendeeNameList;
    }

	public String getCoordinator() {
		return coordinator;
	}

}
