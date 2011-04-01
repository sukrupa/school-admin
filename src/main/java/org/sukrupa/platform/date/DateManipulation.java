package org.sukrupa.platform.date;

import org.joda.time.DateMidnight;
import org.joda.time.DateTimeUtils;

public class DateManipulation {
    public static void freezeDateToMidnightOn_31_12_2010() {
        freezeDateToMidnightOn(31, 12, 2010);
    }

    public static void freezeDateToMidnightOn(int day, int month, int year) {
        DateTimeUtils.setCurrentMillisFixed(new DateMidnight(year, month, day).getMillis());
    }

    public static void unfreezeTime() {
        DateTimeUtils.setCurrentMillisSystem();
    }
}
