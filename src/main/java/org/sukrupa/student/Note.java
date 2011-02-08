package org.sukrupa.student;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;
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
     @Column(name="MESSAGE")
     private String note;
     @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
     @Column(name = "NOTE_DATE")
     private LocalDate localDate;

    public Note(String note) {
        this.note = note;
    }


     public String getNote() {
         return note;
     }

     public LocalDate getLocalDate() {
         return localDate;
     }


     public void setNote(String note) {
         this.note = note;
     }

     public void setLocalDate(LocalDate localDate) {
         this.localDate = localDate;
     }

     public Note( String note, LocalDate localDate) {

        this.note = note;
        this.localDate = localDate;
    }

    @DoNotRemove
    public Note () {

     }

    private static String[] excludedFields = new String[]{"noteId"};

    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other, excludedFields);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, excludedFields);
    }
}