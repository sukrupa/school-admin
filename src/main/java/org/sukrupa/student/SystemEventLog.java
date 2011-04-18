package org.sukrupa.student;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.sukrupa.platform.RequiredByFramework;
import org.sukrupa.platform.date.Date;

import javax.persistence.*;

@Entity
@Table(name = "SYSTEM_EVENT_LOG")
public class SystemEventLog {

    @Id
    @Column(name = "EVENT")
    private String event;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @Column(name = "LAST_HAPPENED")
    private LocalDate dateHappened;

    @RequiredByFramework
    public SystemEventLog() {}


    public SystemEventLog(String event, LocalDate dateHappened) {
        this.event = event;
        this.dateHappened = dateHappened;
    }

    public LocalDate lastHappened() {
        return dateHappened;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemEventLog that = (SystemEventLog) o;

        if (dateHappened != null ? !dateHappened.equals(that.dateHappened) : that.dateHappened != null) return false;
        if (event != null ? !event.equals(that.event) : that.event != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = event != null ? event.hashCode() : 0;
        result = 31 * result + (dateHappened != null ? dateHappened.hashCode() : 0);
        return result;
    }

    public SystemEventLog newEntry(LocalDate newDate) {
        if (dateHappened.compareTo(newDate)== -1) {
            this.dateHappened = newDate;
        }
        return this;
    }

    boolean happenedThisYear() {
        return dateHappened.getValue(0) == Date.now().year();
    }
}
