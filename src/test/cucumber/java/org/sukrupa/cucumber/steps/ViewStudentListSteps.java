package org.sukrupa.cucumber.steps;
import cuke4duke.annotation.I18n.EN.Then;



public class ViewStudentListSteps extends EditAndViewStudentProfile{

    @Then("^\"([^\"]*)\" is displayed in the list of students$")
    public  void  isDisplayedInListOfStudents(String text){
        isDisplayedUnderDivId(text,"studentListPages");
    }
}
