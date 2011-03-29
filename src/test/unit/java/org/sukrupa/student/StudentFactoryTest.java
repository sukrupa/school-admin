package org.sukrupa.student;

import javassist.util.proxy.ProxyFactory;
import org.joda.time.LocalDate;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: ysembira
 * Date: 29/03/2011
 * Time: 12:15
 * To change this template use File | Settings | File Templates.
 */
public class StudentFactoryTest {

    @Test
    public void shouldCreateAStudent(){
        StudentFactory studentFactory = new StudentFactory();
        Student student = studentFactory.create("SK123", "Frank", "11-10-1982");
        assertEquals("SK123",student.getStudentId());
        assertEquals("Frank",student.getName());
        assertEquals(new LocalDate(1982, 10, 11), student.getDateOfBirth());
    }

}
