package org.sukrupa.event;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.io.Serializable;

public class Date implements Serializable {

    private static final String DATE_TIME_FORMAT = "dd/MM/YY HH:mm";
    private static final String DATE_FORMAT = "dd/MM/YYYY";
    private static final String TIME_FORMAT = "HH:mm";

    private DateTime jodaTime;

    public Date(String date, String time) {
        jodaTime = DateTimeFormat.forPattern(DATE_TIME_FORMAT).withZone(DateTimeZone.UTC).parseDateTime(buildDateTimeText(date, time));
    }

    public Date(int day, int month, int year) {
        this(day, month, year, 0, 0);
    }

    public Date(int day, int month, int year, int hours, int minutes) {
        this(day, month, year, hours, minutes, 0, 0);
    }

    public Date(int day, int month, int year, int hours, int minutes, int seconds, int milliseconds) {
        jodaTime = new DateTime(year, month, day, hours, minutes, seconds, milliseconds, DateTimeZone.UTC);
    }


    public static Date now() {
        return new Date(new DateTime().getMillis());
    }

    public Date(long millis) {
        jodaTime = new DateTime(millis, DateTimeZone.UTC);
    }

    private String buildDateTimeText(String date, String time) {
        return date.trim() + " " + time.trim();
    }

    public String getDay() {
        return jodaTime.dayOfWeek().getAsText();
    }

    public String getTime() {
        return jodaTime.toString(DateTimeFormat.forPattern(TIME_FORMAT));
    }

    public DateTime getJodaDateTime() {
        return jodaTime;
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
        return jodaTime.toString(DateTimeFormat.forPattern(DATE_FORMAT));
    }
}
