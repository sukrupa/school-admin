package org.sukrupa.app.admin;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.multipart.MultipartFile;
import org.sukrupa.app.admin.subscribers.Subscriber;
import org.sukrupa.app.admin.subscribers.SubscriberRepository;
import org.sukrupa.app.services.EmailService;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentListPage;
import org.sukrupa.student.StudentSearchParameter;
import org.sukrupa.student.StudentService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.sukrupa.platform.hamcrest.CollectionMatchers.hasEntry;

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

    private HashMap<String, Object> model = new HashMap<String, Object>();

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        adminController = new AdminController(studentService, emailService, subscriberRepository);
    }

    @Test
    public void shouldDisplaySendNewsletterForm() {
        Subscriber subscriber1 = mock(Subscriber.class);
        Subscriber subscriber2 = mock(Subscriber.class);
        when(subscriber1.getSubscriberEmail()).thenReturn("sub1@email");
        when(subscriber2.getSubscriberEmail()).thenReturn("sub2@email");
        when(subscriberRepository.getList()).thenReturn(asList(subscriber1, subscriber2));

        String view = adminController.sendNewsletter("to@email", model);

        assertThat(view, is("admin/sendnewsletterPage"));
        assertThat(model, hasEntry("bccList", "sub1@email;sub2@email;"));
        assertThat(model, hasEntry("toEmailAddress", "to@email"));
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

        String view = adminController.monthlyReports(pageNumber, searchParam, model, request);

        assertThat(view, is("admin/monthlyreportsPage"));
        assertThat(model.get("page"), is((Object) students));

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
        Subscriber kishore = mock(Subscriber.class);
        Subscriber abhinaya = mock(Subscriber.class);
        when(abhinaya.getSubscriberName()).thenReturn("Abhinaya");
        when(abhinaya.getSubscriberEmail()).thenReturn("abhinaya@gmail.com");
        when(kishore.getSubscriberName()).thenReturn("Kishore");
        when(kishore.getSubscriberEmail()).thenReturn("kishore@gmail.com");
        subscribers.add((abhinaya));
        subscribers.add((kishore));
        when(subscriberRepository.getList()).thenReturn(subscribers);
        List<Subscriber> subscriberList = subscriberRepository.getList();
        String mailList = adminController.getMailListAsString();
        assertThat(mailList, is("abhinaya@gmail.com;kishore@gmail.com;"));
    }

    @Test
    public void shouldSendEmailWithAttachment() throws MessagingException, IOException {
        String toAddress = "sukrupa.test@gmail.com";
        String subject = "NewsLetter";
        String bcc = "sukrupa.test@gmail.com";
        when(mockAttachment.getOriginalFilename()).thenReturn("Test.txt");

        String view = adminController.sendNewsletterEmail(toAddress, bcc, subject, "", mockAttachment);

        assertThat(view, is("admin/thankYou"));
        if(System.getProperty("os.name").toLowerCase().contains("windows")){
            verify(emailService).sendNewsLetter(toAddress, bcc, subject, "", System.getProperty("user.dir") + "\\Test.txt");
        }
        else{
            verify(emailService).sendNewsLetter(toAddress, bcc, subject, "", System.getProperty("user.dir") + "/Test.txt");
        }
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
