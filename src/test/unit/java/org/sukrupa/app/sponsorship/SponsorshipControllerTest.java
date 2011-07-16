package org.sukrupa.app.sponsorship;

import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.app.sponsorship.SponsorshipController;
import org.sukrupa.student.StudentRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SponsorshipControllerTest {

    @Mock
    private StudentRepository studentRepository;

    @Test
    public void shouldReturnSponsorshipInfoWithTotalStudentSponsoredAs5AndNumberOfStudentsSponsoredAs2() {
        when(studentRepository.getTotalStudentsCount()).thenReturn(5);
        when(studentRepository.getSponsoredStudentsCount()).thenReturn(2);
        SponsorshipController httpRequestController = new SponsorshipController(studentRepository);
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection") // warning: Contents of collection 'jsonSponsorshipInfo' are queried, but never updated
                JSONObject jsonSponsorshipInfo = new JSONObject();
        jsonSponsorshipInfo.accumulate("numberOfStudents", 5);
        jsonSponsorshipInfo.accumulate("numberOfStudentsSponsored", 2);
        String sponsorshipInfo = httpRequestController.getSponsorshipInfo();
        assertThat(sponsorshipInfo, is(jsonSponsorshipInfo.toString()));

    }

    @Test
    public void shouldReturnSponsorshipInfoWithTotalStudentSponsoredAs6AndNumberOfStudentsSponsoredAs3() {
        when(studentRepository.getTotalStudentsCount()).thenReturn(6);
        when(studentRepository.getSponsoredStudentsCount()).thenReturn(3);
        SponsorshipController httpRequestController = new SponsorshipController(studentRepository);
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection") // warning: Contents of collection 'jsonSponsorshipInfo' are queried, but never updated
                JSONObject jsonSponsorshipInfo = new JSONObject();
        jsonSponsorshipInfo.accumulate("numberOfStudents", 6);
        jsonSponsorshipInfo.accumulate("numberOfStudentsSponsored", 3);
        String sponsorshipInfo = httpRequestController.getSponsorshipInfo();
        assertThat(sponsorshipInfo, is(jsonSponsorshipInfo.toString()));

    }

    @Before
    public void setUp() {
        initMocks(this);
    }
}
