package org.sukrupa.event;

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

	public EventRecordBuilder venue(String venue) {
		this.venue = venue;
		return this;
	}

	public EventRecordBuilder coordinator(String coordinator) {
		this.coordinator = coordinator;
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

	public EventRecord build(){
		return new EventRecord(title, date, time, venue, coordinator, description, notes, attendees);
	}
}
