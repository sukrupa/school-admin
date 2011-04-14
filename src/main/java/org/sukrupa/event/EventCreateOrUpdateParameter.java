package org.sukrupa.event;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;

import java.util.Set;

public class EventCreateOrUpdateParameter {

    private int id;
    private String title;
    private String date;
    private String venue;
    private String coordinator;
    private String description;
    private String notes;
    private String attendees;
    
    private String endTime;
    private String endTimeAmPm;
    private String startTime;
    private String startTimeAmPm;


    public EventCreateOrUpdateParameter() {
    }

    public EventCreateOrUpdateParameter(int id, String title, String date, String endTime, String venue, String coordinator, String description, String notes, String attendees, String startTime) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.endTime = endTime;
        this.venue = venue;
        this.coordinator = coordinator;
        this.description = description;
        this.notes = notes;
        this.attendees = attendees;
        this.startTime = startTime;
//        this.attendees = Sets.newHashSet(Splitter.on(",").omitEmptyStrings().trimResults().split(attendees));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEndTime() {
        return returnNullIfEmpty(endTime);
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getVenue() {
        return returnNullIfEmpty(venue);
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCoordinator() {
        return returnNullIfEmpty(coordinator);
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
        return returnNullIfEmpty(notes);
    }

	private String returnNullIfEmpty(String value) {
		return (value.isEmpty()) ? null : value;
	}

	public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAttendees() {
        return attendees;
//        return Joiner.on(", ").join(attendees);
    }

    public Set<String> getStudentIdsOfAttendees() {
//        return attendees;
        attendees = attendees.replace(" ","").replace("\n", "").replace("\r", "");
	    return Sets.newHashSet(Splitter.on(",").omitEmptyStrings().trimResults().split(attendees));
    }

    public void setAttendees(String attendees) {
        this.attendees = attendees;
    }

    public void setEndTimeAmPm(String amPm) {
        this.endTimeAmPm = amPm;
    }

    public String getEndTimeAmPm() {
        return endTimeAmPm;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setStartTimeAmPm(String startTimeAmPm) {
        this.startTimeAmPm = startTimeAmPm;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getStartTimeAmPm() {
        return startTimeAmPm;
    }
}
