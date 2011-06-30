package org.sukrupa.cucumber.steps;

import org.sukrupa.cucumber.context.OnStudentBhavaniProfileEditPage;
import cuke4duke.annotation.Before;
import cuke4duke.annotation.I18n.EN.*;
import cuke4duke.annotation.Pending;
import org.sukrupa.cucumber.context.Login;
import org.sukrupa.cucumber.context.OnAddNewTalentPage;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.cucumber.SahiFacade.browser;

/**
 * Created by IntelliJ IDEA.
 * User: Thoughtworks
 * Date: 6/29/11
 * Time: 6:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class StudentFamilyStatusSteps extends OnStudentBhavaniProfileEditPage {

    @When("^I choose family status \"([^\"]*)\"$")
    public void chooseFamilyStatus(String status){
        browser().select("familyStatus").choose(status);
    }
}
