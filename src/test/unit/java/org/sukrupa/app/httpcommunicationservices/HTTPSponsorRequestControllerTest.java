package org.sukrupa.app.httpcommunicationservices;

import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.student.StudentRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class HTTPSponsorRequestControllerTest {

    @Mock
    private StudentRepository studentRepository;

    @Test
    public void shouldReturnSponsorshipInfoWithTotalStudentSponsoredAs5AndNumberOfStudentsSponsoredAs2() {
        when(studentRepository.getTotalStudentsCount()).thenReturn(5);
        when(studentRepository.getSponsoredStudentsCount()).thenReturn(2);
        HTTPSponsorRequestController httpRequestController = new HTTPSponsorRequestController(studentRepository);
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
        HTTPSponsorRequestController httpRequestController = new HTTPSponsorRequestController(studentRepository);
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
//    /*
//        Example of usage
//     */
//    @Test
//    public void shouldReturnJSONViaHTTP(){
//        String s ="";
//        JSONObject jobj = new JSONObject();
//        jobj.accumulate("numberOfStudents", 400);
//        jobj.accumulate("numberOfStudentsSponsored", 258);
//
//        JSONObject jsonIN = new JSONObject();
//        try {
//            URL url = new URL("http://localhost:8080/getsponsorshipinfo");
//            URLConnection conn = url.openConnection();
//            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            s =rd.readLine();
//            jsonIN = JSONObject.fromObject(s);
//
//        } catch (Exception e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//        assertThat(jsonIN.get("numberOfStudents").toString(),is(jobj.get("numberOfStudents").toString()) );
//    }
}
