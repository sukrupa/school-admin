package org.sukrupa.twist.example;

import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;

public class Greeter {
	public String greet(String name){
		Student student = new StudentBuilder().name(name).build();
		return "Hello " + student.getName();
	}
}
