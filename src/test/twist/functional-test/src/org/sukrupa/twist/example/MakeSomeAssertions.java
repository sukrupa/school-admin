package org.sukrupa.twist.example;

import static java.lang.String.format;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class MakeSomeAssertions {
	
	public void assertThatIsFalse(boolean input) throws Exception {
		assertFalse(format("Input [%s] should be true", input), input);
	}

	public void whenIGreetAStudentCalledIShouldGetAsAGreeting(String studentName, String expectedGreeting) throws Exception {
		Greeter greeter = new Greeter();
		
		String greeting = greeter.greet(studentName);
		
		assertEquals(expectedGreeting, greeting);
	}

}
