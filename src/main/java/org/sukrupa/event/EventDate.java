package org.sukrupa.event;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.io.Serializable;

public class EventDate implements Serializable {

	private static final String DATE_TIME_FORMAT = "dd/MM/YY HH:mm";

	private DateTime jodaTime;

	public EventDate(String date, String time) {
		jodaTime = DateTimeFormat.forPattern(DATE_TIME_FORMAT).withZone(DateTimeZone.UTC).parseDateTime(buildDateTimeText(date, time));
	}

	public EventDate(int year, int month, int day, int hours, int minutes, int seconds, int milliseconds) {
		jodaTime = new DateTime(year, month, day, hours, minutes, seconds, milliseconds, DateTimeZone.UTC);
	}

	public EventDate(long millis) {
		jodaTime = new DateTime(millis, DateTimeZone.UTC);
	}

	private String buildDateTimeText(String date, String time) {
		return date.trim() + " " + time.trim();
	}

	@Override
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return jodaTime.toString();
	}

	public DateTime getJodaDateTime() {
		return jodaTime;
	}

}
