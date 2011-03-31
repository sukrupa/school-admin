package org.sukrupa.platform.date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.DateTimeComparator;

import java.io.Serializable;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.joda.time.DateTimeZone.UTC;
import static org.joda.time.format.DateTimeFormat.forPattern;

public class Date implements Serializable {

    private static final String DATE_FORMAT = "dd-MM-YYYY";
    private static final String TIME_FORMAT = "HH:mm";
    private static final String DATE_TIME_FORMAT = "dd-MM-YYYY HH:mm";

    private DateTime jodaTime;

    public Date(int day, int month, int year) {
        this(day, month, year, 0, 0);
    }

    public Date(int day, int month, int year, int hours, int minutes) {
        this(day, month, year, hours, minutes, 0, 0);
    }

    public Date(int day, int month, int year, int hours, int minutes, int seconds, int milliseconds) {
        this(new DateTime(year, month, day, hours, minutes, seconds, milliseconds, UTC));
    }

    public Date(long millis) {
        this(new DateTime(millis, UTC));
    }

    private Date(DateTime jodaTime) {
        this.jodaTime = jodaTime;
    }

    public static Date now() {
        return new Date(new DateTime(UTC));
    }

    public static Date parse(String date, String time) {
        return isBlank(time) ? parseDate(date) : parseDateAndTime(date, time);
    }

    private static String buildDateTimeText(String date, String time) {
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

    private static Date parseDate(String date) {
        return parse(date, "00:00");
    }

    private static Date parseDateAndTime(String date, String time) {
        return new Date(forPattern(DATE_TIME_FORMAT).withZone(UTC).parseDateTime(buildDateTimeText(date, time)));
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

    public boolean isInThePast() {
        if (DateTimeComparator.getInstance().compare(jodaTime,Date.now().getJodaDateTime())==-1) {
            return true;
        }else{
            return false;
        }
    }


}
