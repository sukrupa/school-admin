package org.sukrupa.event;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.sukrupa.platform.DoNotRemove;
import org.sukrupa.student.Student;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Event {

	private static final String DATE_TIME_FORMAT = "dd/MM/YY HH:mm";

	@Id
	@GeneratedValue
	@Column(name = "event_id")
	private int eventId;

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


	@ManyToMany
	@JoinTable(name = "EventAttendees",
			joinColumns = {@JoinColumn(name = "event_id")},
			inverseJoinColumns = {@JoinColumn(name = "id")})
	private Set<Student> attendees;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime datetime;

	public Set<Student> getAttendees() {
		return this.attendees;
	}

	@DoNotRemove
	public Event() {
	}

	public Event(String title, DateTime datetime, String venue, String coordinator, String description, String notes, Set<Student> attendees) {
		this.title = title;
		this.datetime = datetime;
		this.venue = venue;
		this.coordinator = coordinator;
		this.description = description;
		this.notes = notes;
		this.attendees = attendees;
	}

	@Transient
	private String[] excludedFields = new String[] {"eventId"};

	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other, excludedFields);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, excludedFields);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}

	public static Event createFrom(EventRecord eventRecord, Set<Student> attendees) {
		return new EventBuilder().title(eventRecord.getTitle())
			    .venue(eventRecord.getVenue())
			    .description(eventRecord.getDescription())
			    .coordinator(eventRecord.getCoordinator())
			    .notes(eventRecord.getNotes())
			    .datetime(parseDateTime(eventRecord))
			    .attendees(attendees)
			    .build();
	}


	private static DateTime parseDateTime(EventRecord eventRecord) {
		return DateTimeFormat.forPattern(DATE_TIME_FORMAT).withZone(DateTimeZone.UTC).parseDateTime(buildDateTimeText(eventRecord));
	}

	private static String buildDateTimeText(EventRecord eventRecord) {
		return eventRecord.getDate().trim() + " " + eventRecord.getTime().trim();
	}
}
