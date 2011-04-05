package org.sukrupa.event;

import com.google.common.base.Joiner;
import org.sukrupa.platform.date.Date;
import org.sukrupa.student.Student;
import static com.google.common.collect.Sets.newHashSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: pmurray
 * Date: 4/04/11
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */
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

      public EventCreateOrUpdateParameter build() {
          return new EventCreateOrUpdateParameter(id, title, date.toString(), time, venue, coordinator, description, notes, attendeesIds());
      }

    private String attendeesIds() {
        Set<String> attendeesIds = new HashSet<String>();
        for (Student student:attendees) {
            attendeesIds.add(student.getStudentId());
        }
        return Joiner.on(", ").join(attendeesIds);
    }

    private String attendeeString() {
        return Joiner.on(",").join(attendees);
    }

}
