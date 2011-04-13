package org.sukrupa.app.students;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.student.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.apache.commons.collections.CollectionUtils.isEqualCollection;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.sukrupa.app.students.StudentFormPresenter.CheckBoxElement;
import static org.sukrupa.platform.hamcrest.CollectionMatchers.hasOnly;

public class StudentFormPresenterTest {

    @Mock TalentRepository talentRepository;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldUseTalentRepositoryToListTalents(){
        StudentFormPresenter studentFormPresenter = new StudentFormPresenter(studentWithNoTalent(), talentRepository);

        when(talentRepository.listAllTalents()).thenReturn(asList(talent("talent1"),
                                                                  talent("talent2"),
                                                                  talent("talent3")));

        List<CheckBoxElement> checkBoxElements = studentFormPresenter.getTalentsCheckBoxList();

        assertThat(checkBoxElements, hasOnlyCheckBoxesWithValues("talent1", "talent2", "talent3"));
    }

    private static Student studentWithNoTalent() {
        return new StudentBuilder().build();
    }

    private static Talent talent(String talentDescription) {
        return new TalentBuilder().description(talentDescription).build();
    }

    private static Matcher<List<CheckBoxElement>> hasOnlyCheckBoxesWithValues(final String... expectedValues) {
        return new TypeSafeMatcher<List<CheckBoxElement>>() {

            @Override
            protected boolean matchesSafely(List<CheckBoxElement> checkBoxElements) {
                List<String> elementValues = new ArrayList<String>();
                for (CheckBoxElement checkBoxElement : checkBoxElements) {
                    elementValues.add(checkBoxElement.getValue());
                }

                return isEqualCollection(elementValues, asList(expectedValues));
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("A collection of checkboxes with values of : ");
                description.appendValueList("<", ", ", ">", expectedValues);
            }
        };
    }
}
