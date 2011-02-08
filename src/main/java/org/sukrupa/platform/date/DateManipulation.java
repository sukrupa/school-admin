package org.sukrupa.platform.date;

import org.joda.time.DateMidnight;
import org.joda.time.DateTimeUtils;

public class DateManipulation {
    public static void freezeTime() {
        DateTimeUtils.setCurrentMillisFixed(new DateMidnight(2010, 12, 31).getMillis());
    }

    public static void unfreezeTime() {
        DateTimeUtils.setCurrentMillisSystem();
    }
}
