package org.sukrupa.student;

import org.joda.time.LocalDate;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class StudentFactoryTest {

    @Test
    public void shouldCreateAStudent(){
        StudentFactory studentFactory = new StudentFactory();
        StudentCreateOrUpdateParameters studentParam = new StudentCreateOrUpdateParameters();
        studentParam.setStudentId("SK123");
        studentParam.setName("Frank");
        studentParam.setDateOfBirth("11-10-1982");

        Student student = studentFactory.create(studentParam.getStudentId(), studentParam.getName(), studentParam.getDateOfBirth());
        assertEquals("SK123",student.getStudentId());
        assertEquals("Frank",student.getName());
        assertEquals(new LocalDate(1982, 10, 11), student.getDateOfBirth());
    }

}
