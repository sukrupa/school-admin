package org.sukrupa.twist.example;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class MakeAnAssertion {

	public void assertThatIsTrue(boolean input) throws Exception {
		assertTrue(input + " should be true", input);
	}

	public void greetingAStudentCalledShouldSay(String studentName, String expectedGreeting) throws Exception {
		Greeter greeter = new Greeter();
		
		String actualGreeting = greeter.greet(studentName);

		
		assertEquals(expectedGreeting,actualGreeting);
	}

}
