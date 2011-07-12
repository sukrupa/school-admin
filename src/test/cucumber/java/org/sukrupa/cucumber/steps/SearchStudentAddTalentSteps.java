//package org.sukrupa.cucumber.steps;
//
//import cuke4duke.annotation.I18n;
//import cuke4duke.annotation.I18n.EN.Then;
//import cuke4duke.annotation.I18n.EN.When;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;
//import static org.sukrupa.cucumber.SahiFacade.browser;
//
//public class SearchStudentAddTalentSteps extends BasicWebSteps{
//
//    @Then("^\"([^\"]*)\" should now contain \"([^\"]*)\"$")
//    public void shouldNowContain(String objectID , String objectValueToMatch){
//        String value= browser().byId(objectID).getText();
//        assertThat(value,containsString(objectValueToMatch));
//
//    }
//}
