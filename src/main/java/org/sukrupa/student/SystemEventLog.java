package org.sukrupa.student;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import java.sql.Timestamp;

public class SystemEventLog {
    @Column(name = "key")
    private String event;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @Column(name = "value")
    private LocalDate dateHappened;




    public SystemEventLog(String event, LocalDate dateHappened) {
        this.event = event;
        this.dateHappened = dateHappened;
    }

    public LocalDate lastHappened() {
        return dateHappened;
    }
}
