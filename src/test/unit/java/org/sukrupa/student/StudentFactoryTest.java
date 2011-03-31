package org.sukrupa.student;

import org.joda.time.LocalDate;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class StudentFactoryTest {

    @Test
    public void shouldCreateAStudent(){
        StudentFactory studentFactory = new StudentFactory();
        StudentCreateOrUpdateParameter studentParam = new StudentCreateOrUpdateParameter();
        studentParam.setStudentId("SK123");
        studentParam.setName("Frank");
        studentParam.setDateOfBirth("11-10-1982");

        Student student = studentFactory.createBasic(studentParam);
        assertEquals("SK123",student.getStudentId());
        assertEquals("Frank",student.getName());
        assertEquals(new LocalDate(1982, 10, 11), student.getDateOfBirth());
    }

}
