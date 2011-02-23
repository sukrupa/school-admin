package org.sukrupa.student;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.sukrupa.student.db.StudentsSearchCriteriaGenerator;

public class StudentSearchCriteriaGeneratorTest {

    @Autowired
    private SessionFactory sessionFactory;

    private StudentsSearchCriteriaGenerator studentsSearchCriteriaGenerator;

    @Before
    public void setUp(){
        studentsSearchCriteriaGenerator = new StudentsSearchCriteriaGenerator(sessionFactory);
    }

    @Test
    @Ignore("[Karthik,Suhas] Work In Progress")
    public void should() {

    }

}
