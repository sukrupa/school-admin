package org.sukrupa.app.students;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.student.StudentReferenceData;
import org.sukrupa.student.Talent;
import org.sukrupa.student.TalentRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.sukrupa.platform.hamcrest.CollectionMatchers.hasOnly;
import static org.sukrupa.student.TalentBuilder.talent;

public class StudentReferenceDataTest {

    @Mock
    TalentRepository talentRepository;

    @Before
    public void setUp() {
        initMocks(this);
    }


    @Test
    public void shouldGetTheTalentsFromTheRepository() {
        StudentReferenceData studentReferenceData = new StudentReferenceData(talentRepository);
        List<Talent> talents = asList(talent("talent1"),
                talent("talent2"),
                talent("talent3"));

        when(talentRepository.findAllTalents()).thenReturn(talents);
        when(talentRepository.returnTalentDescriptionsInList(talents)).thenReturn(asList("talent1", "talent2", "talent3"));

        List<String> talentDescriptions = studentReferenceData.getTalentDescriptions();

        assertThat(talentDescriptions, hasOnly("talent1", "talent2", "talent3"));

    }

}
