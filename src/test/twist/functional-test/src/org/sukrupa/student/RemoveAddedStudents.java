package org.sukrupa.student;

import org.sukrupa.twist.ScenarioDataUnitOfWork;

public class RemoveAddedStudents {

	private final ScenarioDataUnitOfWork scenarioDataUnitOfWork;

	public RemoveAddedStudents(ScenarioDataUnitOfWork scenarioDataUnitOfWork) {
		this.scenarioDataUnitOfWork = scenarioDataUnitOfWork;
	}

	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
		scenarioDataUnitOfWork.removeAllCreatedObjects();
	}

}
