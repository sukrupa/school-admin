package org.sukrupa.app.httpcommunicationservices;

import net.sf.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HTTPRequestControllerTest {
    @Test
    public void shouldReturnDUMMYSponsorshipInfo(){
        HTTPRequestController httpRequestController = new HTTPRequestController();
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection") // warning: Contents of collection 'jsonSponsorshipInfo' are queried, but never updated
        JSONObject jsonSponsorshipInfo = new JSONObject();
        jsonSponsorshipInfo.accumulate("numberOfStudents", 400);
        jsonSponsorshipInfo.accumulate("numberOfStudentsSponsored", 258);
        String sponsorshipInfo = httpRequestController.getSponsorshipInfo();
        assertThat(sponsorshipInfo, is(jsonSponsorshipInfo.toString()));

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
