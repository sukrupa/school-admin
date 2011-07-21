package org.sukrupa.app.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.sukrupa.app.admin.subscribers.Subscriber;
import org.sukrupa.app.admin.subscribers.SubscriberRepository;
import org.sukrupa.app.services.EmailService;
import org.sukrupa.student.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private StudentService studentService;
    private EmailService emailService;
    private SubscriberRepository subscriberRepository;

    @Autowired
    public AdminController(StudentService studentService, EmailService emailService, SubscriberRepository subscriberRepository) {
        this.studentService = studentService;
        this.emailService = emailService;
        this.subscriberRepository = subscriberRepository;
    }


    @RequestMapping()
    public String list() {
        return "admin/adminPage";
    }

    @RequestMapping("/monthlyreports")
    public String monthlyReports(@RequestParam(required = false, defaultValue = "1", value = "page") int pageNumber,
                                 @ModelAttribute("searchParam") StudentSearchParameter searchParam,
                                 Map<String, Object> model, HttpServletRequest request) {

        model.put("searchCriteria", searchParam.getValidCriteria());

        StudentListPage students = studentService.getPage(searchParam, pageNumber, request.getQueryString());

        model.put("page", students);

        return "admin/monthlyreportsPage";
    }

    @RequestMapping("/sendnewsletter")
    public String sendNewsletter(Map<String, Object> model) {
        model.put("bccList", getMailListAsString());
        return "admin/sendnewsletterPage";
    }

    @RequestMapping(value = "/sendnewsletteremail", method = POST)
    public String sendNewsletterEmail(@RequestParam String to, @RequestParam String bcc, @RequestParam String subject, @RequestParam String comments, @RequestParam("attach") MultipartFile file) throws MessagingException, IOException {
        String findOS= System.getProperty("os.name").toLowerCase();
        String fileAttachmentFilePath;
        if (findOS.contains("mac") || findOS.contains("linux"))
        {
            fileAttachmentFilePath = System.getProperty("user.dir") + "/" + file.getOriginalFilename();
        }
        else{
            fileAttachmentFilePath = System.getProperty("user.dir") + "\\" + file.getOriginalFilename();
        }

        if (file.getOriginalFilename() == "") {
            emailService.sendNewsLetterEmailWithoutAttachment(to, bcc, subject, comments);
        } else {
            File webserverSideCopyOfClientSideFileAttachment = new File(fileAttachmentFilePath);
            file.transferTo(webserverSideCopyOfClientSideFileAttachment);
            emailService.sendNewsLetter(to, bcc, subject, comments, fileAttachmentFilePath);
            webserverSideCopyOfClientSideFileAttachment.delete();
        }
        return "admin/thankYou";
    }

    @RequestMapping("/endofsponsorshipform")
    public String showEndOfSponsorshipForm() {
        return "admin/endofsponsorshipform";
    }

    @RequestMapping(value = "/endofsponsorshipmailsentPage")
    public String sendEndOfSponsorShipEmailAndShowConfirmPage(@RequestParam("to") String toAddress,
                                                              @RequestParam String subject, @RequestParam String message) throws MessagingException {
        emailService.sendEmail(toAddress, subject, message);
        return "/admin/endofsponsorshipmailsentPage";
    }

    public String getMailListAsString() {
        List<Subscriber> subscriberList = subscriberRepository.getList();
        String mailList = "";
        for (Subscriber subscriber : subscriberList) {
            mailList += subscriber.getSubscriberEmail() + ";";
        }
        return mailList;
    }
}
