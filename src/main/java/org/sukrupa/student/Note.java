package org.sukrupa.student;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.LocalDate;
import org.sukrupa.platform.DoNotRemove;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 @Entity
public class Note {
    @Id
    @GeneratedValue
    @Column(name = "NOTE_ID")
    private long noteId;
    @Column(name = "STUDENT_ID")
    int studentId;
    private String note;
    @Column(name = "NOTE_DATE")
    LocalDate localDate;

    public Note(String note) {
        this.note = note;
    }

    public Note(int student_id, String note, LocalDate localDate) {
        this.studentId = student_id;
        this.note = note;
        this.localDate = localDate;
    }

    @DoNotRemove
    public Note () {

     }


    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}