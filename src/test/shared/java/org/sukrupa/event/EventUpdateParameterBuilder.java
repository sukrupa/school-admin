package org.sukrupa.event;

import com.google.common.base.Joiner;
import org.sukrupa.platform.date.Date;
import org.sukrupa.student.Student;
import static com.google.common.collect.Sets.newHashSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EventUpdateParameterBuilder {
    private int id;
    private String title;
    private Date date;
    private String notes;
    private String coordinator;
    private String venue;
    private String description;
    private Set<Student> attendees;
    private String time;
    private String startTime;

    public EventUpdateParameterBuilder id(int id)
    {
        this.id = id;
        return this;
    }

    public EventUpdateParameterBuilder title(String title) {
          this.title = title;
          return this;
      }

    public EventUpdateParameterBuilder time(String time) {
        this.time =time;
        return this;
    }

    public EventUpdateParameterBuilder startTime(String startTime) {
        this.startTime = startTime;
        return this;
    }
      public EventUpdateParameterBuilder venue(String venue) {
          this.venue = venue;
          return this;
      }

      public EventUpdateParameterBuilder coordinator(String coordinator) {
          this.coordinator = coordinator;
          return this;
      }

      public EventUpdateParameterBuilder description(String description) {
          this.description = description;
          return this;
      }

      public EventUpdateParameterBuilder notes(String notes) {
          this.notes = notes;
          return this;
      }

      public EventUpdateParameterBuilder date(Date date) {
          this.date = date;
          return this;
      }

      public EventUpdateParameterBuilder attendees(Set<Student> attendees) {
          this.attendees = attendees;
          return this;
      }

      public EventUpdateParameterBuilder attendees(Student... students) {
          this.attendees = newHashSet(students);
          return this;
      }

      public EventForm build() {
          return new EventForm(id, title, date.toString(), time, venue, coordinator, description, notes, attendeesIds(), startTime);
      }

    private List<String> attendeesIds() {
        List<String> attendeesIds = new ArrayList<String>();
        for (Student student:attendees) {
            attendeesIds.add(student.getStudentId());
        }
        return attendeesIds;


    }

    private String attendeeString() {
        return Joiner.on(",").join(attendees);
    }

}
