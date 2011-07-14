package org.sukrupa.app.admin;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.multipart.MultipartFile;
import org.sukrupa.app.admin.subscribers.Subscriber;
import org.sukrupa.app.admin.subscribers.SubscriberRepository;
import org.sukrupa.app.services.EmailService;
import org.sukrupa.student.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.beans.beancontext.BeanContextChild;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdminControllerTest {

    @Mock
    private StudentService studentService;
    @Mock
    private EmailService emailService;
    @Mock
    private MultipartFile mockAttachment;
    @Mock
    private SubscriberRepository subscriberRepository;

    private AdminController adminController;

    private HashMap<String, Object> studentModel = new HashMap<String, Object>();

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        adminController = new AdminController(studentService, emailService, subscriberRepository);
    }

    @Test
    public void shouldDisplayMonthlyReportListOfSponsors() {
        StudentSearchParameter searchParam = mock(StudentSearchParameter.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        StudentListPage students = mock(StudentListPage.class);
        List<String> validCriteria = asList("someCriteria");
        Student student = mock(Student.class);
        int pageNumber = 23;

        when(searchParam.getValidCriteria()).thenReturn(validCriteria);
        when(request.getQueryString()).thenReturn("TestQueryString");
        when(studentService.getPage(searchParam, pageNumber, "TestQueryString")).thenReturn(students);
        when(students.getStudents()).thenReturn(asList(student));

        String view = adminController.monthlyReports(pageNumber, searchParam, studentModel, request);

        assertThat(view, is("admin/monthlyreportsPage"));
        assertThat(studentModel.get("page"), is((Object) students));

    }

    @Test
    public void shouldDisplayEndOfSponsorshipForm() {
        String view = adminController.showEndOfSponsorshipForm();
        assertThat(view, is("admin/endofsponsorshipform"));
    }

    @Test
    public void shouldShowEndOfSponsorshipConfirmPage() throws MessagingException {
        String view = adminController.sendEndOfSponsorShipEmailAndShowConfirmPage("", "", "");
        assertThat(view, is("/admin/endofsponsorshipmailsentPage"));
    }

    @Test
    public void shouldSentEndOfSponsorshipEmail() throws MessagingException {
        String toAddress = "sukrupa.test@gmail.com";
        String subject = "end of sponsor";
        String comments = "Thanks for Sponsorship";
        String view = adminController.sendEndOfSponsorShipEmailAndShowConfirmPage(toAddress, subject, comments);
        verify(emailService).sendEmail(toAddress, subject, comments);

    }

    @Test
    public void shouldConvertSubscribersMailListTOAString() {
        ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>();
        subscribers.add(new Subscriber("Abhinaya", "abhijan90@gmail.com"));
        subscribers.add(new Subscriber("Kishore", "abyu.kishore@aol.in"));
        when(subscriberRepository.getList()).thenReturn(subscribers);
        List<Subscriber> subscriberList = subscriberRepository.getList();
        String mailList = adminController.getMailListAsString();
        assertThat(mailList, is("abhijan90@gmail.com;abyu.kishore@aol.in;"));
    }

    @Test
    public void shouldSendEmailWithAttachment() throws MessagingException, IOException {
        String toAddress = "sukrupa.test@gmail.com";
        String subject = "NewsLetter";
        String bcc = "sukrupa.test@gmail.com";
        when(mockAttachment.getOriginalFilename()).thenReturn("Test.txt");

        String view = adminController.sendNewsletterEmail(toAddress, bcc, subject, "", mockAttachment);

        assertThat(view, is("admin/thankYou"));
        verify(emailService).sendNewsLetter(toAddress, bcc, subject, "", System.getProperty("user.dir") + "\\Test.txt");
    }

    @Test
    public void shouldSendEmailWithoutAttachment() throws MessagingException, IOException {
        String toAddress = "sukrupa.test@gmail.com";
        String subject = "NewsLetter";
        String bcc = "sukrupa.test@gmail.com";
        when(mockAttachment.getOriginalFilename()).thenReturn("");

        String view = adminController.sendNewsletterEmail(toAddress, bcc, subject, "", mockAttachment);

        assertThat(view, is("admin/thankYou"));
        verify(emailService).sendNewsLetterEmailWithoutAttachment(toAddress, bcc, subject, "");
    }
}
