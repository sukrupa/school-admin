package org.sukrupa.event;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class EventAttendee {
     int eventID;
    String studentID;
    public EventAttendee(int eventID, String studentID) {
        this.eventID = eventID;
        this.studentID = studentID;

    }
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
