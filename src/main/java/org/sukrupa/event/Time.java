package org.sukrupa.event;

import static org.apache.commons.lang.StringUtils.isBlank;

public class Time {

    private String time;
    private String amPm;

    public Time (String time, String amPm) {
        this.time = time;
        this.amPm = amPm;
    }

    public boolean exists() {
        return !isBlank(time);
    }

    public String twelveHourClock() {
        if (!isBlank(amPm)) {
        return time + " " + amPm;
        }
        return time + " am";
    }
}
