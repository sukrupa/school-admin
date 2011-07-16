package org.sukrupa.app.sponsorship;

import org.apache.jasper.tagplugins.jstl.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sukrupa.student.StudentRepository;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/getsponsorshipinfo")
public class SponsorshipController {

    private StudentRepository studentRepository;

    @Autowired
    public SponsorshipController(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    @RequestMapping(method = GET)
    @ResponseBody
    public String getSponsorshipInfo() {

        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection") // warning: Contents of collection 'jsonSponsorshipInfo' are queried, but never updated
        JSONObject jsonSponsorshipInfo = new JSONObject();
        jsonSponsorshipInfo.accumulate("numberOfStudents", studentRepository.getTotalStudentsCount());
        jsonSponsorshipInfo.accumulate("numberOfStudentsSponsored", studentRepository.getSponsoredStudentsCount());
        return jsonSponsorshipInfo.toString();
    }
}
