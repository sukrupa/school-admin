package org.sukrupa.event;

public class EventRecord {
	private String title;
	private String date;
	private String time;
	private String venue;
	private String coordinator;
	private String description;
	private String notes;
	private String attendees;

	public EventRecord() {}

	public EventRecord(String title, String date, String time, String venue, String coordinator, String description, String notes, String attendees) {
		this.title = title;
		this.date = date;
		this.time = time;
		this.venue = venue;
		this.coordinator = coordinator;
		this.description = description;
		this.notes = notes;
		this.attendees = attendees;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getCoordinator() {
		return coordinator;
	}

	public void setCoordinator(String coordinator) {
		this.coordinator = coordinator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAttendees() {
		return attendees;
	}

	public void setAttendees(String attendees) {
		this.attendees = attendees;
	}
}
