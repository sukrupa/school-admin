package org.sukrupa.app.httpcommunicationservices;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/getsponsorshipinfo")
public class HTTPRequestController {
    @RequestMapping(method = GET)
    @ResponseBody
    public String getSponsorshipInfo() {
        //Dummy Value for now
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection") // warning: Contents of collection 'jsonSponsorshipInfo' are queried, but never updated
        JSONObject jsonSponsorshipInfo = new JSONObject();
        jsonSponsorshipInfo.accumulate("numberOfStudents", 400);
        jsonSponsorshipInfo.accumulate("numberOfStudentsSponsored", 258);
        return jsonSponsorshipInfo.toString();
    }
}
