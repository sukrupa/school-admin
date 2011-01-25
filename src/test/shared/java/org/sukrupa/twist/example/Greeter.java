package org.sukrupa.twist.example;

import org.sukrupa.student.Student;

public class Greeter {
	public String greet(String name){
		Student student = new Student(name);
		return "Hello " + student.getName();
	}
}
