package org.sukrupa.student;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: ysembira
 * Date: 29/03/2011
 * Time: 12:14
 * To change this template use File | Settings | File Templates.
 */

@Component
public class StudentFactory {

    public Student create(String studentId, String studentName, LocalDate studentDateOfBirth) {
       Student student = new Student(studentId,studentName,studentDateOfBirth);
       return student;
    }
}
