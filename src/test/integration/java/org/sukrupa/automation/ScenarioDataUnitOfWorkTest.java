package org.sukrupa.automation;

import org.junit.*;

public class ScenarioDataUnitOfWorkTest {

    @Test
    public void shouldDeleteAStudentWhenAskedTo() {

        ScenarioDataUnitOfWork unitOfWork = new ScenarioDataUnitOfWork();

        unitOfWork.addedStudentWithId("SK10000");

        unitOfWork.removeAllCreatedObjects();
    }
}