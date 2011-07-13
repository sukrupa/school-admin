package org.sukrupa.app.services;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.sukrupa.app.SMTPTransportFactory;
import org.sukrupa.platform.config.AppConfiguration;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.Message;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class EmailServiceTest {

    private EmailService emailService;
    @Mock
    private  AppConfiguration appConfiguration;
    @Mock
    private Transport transport;
    @Mock
    private SMTPTransportFactory transportFactory;
    @Mock
    private Properties properties;


    @Before
    public void setUp() {
        initMocks(this);
        emailService = new EmailService(appConfiguration, transportFactory);
    }

    @Test
    public void shouldSendEmail() throws MessagingException {
        String toAddress = "sukrupa.test@gmail.com";
        String subject="Hai";
        String comments="Thanks";

        when(appConfiguration.properties()).thenReturn(properties);
        when(transportFactory.getNewSMTPTransport(properties)).thenReturn(transport);
        when(properties.getProperty("mail.smtp.host")).thenReturn("host");
        when(properties.getProperty("mail.smtp.port")).thenReturn("1234");
        when(properties.getProperty("mail.smtp.user")).thenReturn("user");
        when(properties.getProperty("mail.smtp.password")).thenReturn("pw");


        boolean result = emailService.sendEmail(toAddress, subject, comments);

        assertThat(result, is(true));

        verify(transport).sendMessage(Matchers.<Message>any(), Matchers.<Address[]>any());
        verify(transport).connect(anyString(),anyInt(),anyString(),anyString());
        verify(transport).close();
    }

    @Test
    public void shouldReturnFalseWhenSendingEmailHasAnError() throws MessagingException {
        String toAddress = "aravindp@thoughtworks.com";
        String subject="Hai";
        String comments="Thanks";

        when(appConfiguration.properties()).thenReturn(properties);
        when(transportFactory.getNewSMTPTransport(properties)).thenReturn(transport);
        when(properties.getProperty("mail.smtp.host")).thenReturn("host");
        when(properties.getProperty("mail.smtp.port")).thenReturn("1234");
        when(properties.getProperty("mail.smtp.user")).thenReturn("user");
        when(properties.getProperty("mail.smtp.password")).thenReturn("pw");
        doThrow(new MessagingException()).when(transport).connect(anyString(),anyInt(),anyString(),anyString());

        boolean result = emailService.sendEmail(toAddress, subject, comments);

        assertThat(result, is(false));
    }

    @Test
    public void shouldExtractAttachmentFileAddress(){
<<<<<<< HEAD
        String actualFilePath = "C:\\Users\\srivathr\\Desktop\\Text.txt";
=======
        String actualFilePath = "C:\\Users\\myuser\\Desktop\\Text.txt";
        emailService = new EmailService(appConfiguration);
>>>>>>> Abhi/Kishore: Story 536 Added cucumber feature for file attach. remove. Pre populated bcc with mailing list
        String extractedAttachmentFileAddress = emailService.extractAttachmentFileAddress(actualFilePath);
        Assert.assertThat(extractedAttachmentFileAddress, is("/Users/myuser/Desktop/Text.txt"));

    }

    @Test
    public void shouldConvertStringToInternetAddress() throws AddressException {
        String testEmailAddress = "sukrupa.test@gmail.com";
        InternetAddress expectedInternetAddress = new InternetAddress(testEmailAddress);

        InternetAddress actualInternetAddress = emailService.convertStringToInternetAddress(testEmailAddress);

        assertThat(actualInternetAddress, is(expectedInternetAddress));
    }

    @Test(expected = AddressException.class)
    public void shouldThrowAnAddressExceptionForInvalidEmailAddress() throws AddressException {
        String invalidEmailAddress = "notvalid@";

        emailService.convertStringToInternetAddress(invalidEmailAddress);
    }

    @Test
    public void shouldCreateMimeMessageAndRecipientAsTo() throws MessagingException, IOException {
        String excpectedSubject = "A subject";
        when(appConfiguration.properties()).thenReturn(new Properties());

        InternetAddress expectedRecipient = new InternetAddress("sukrupa.test@gmail.com");
        MimeMessage mimeMessage = emailService.createMimeMessageWithSubjectAndRecipientAsToAndAttachment(expectedRecipient, excpectedSubject, "", "");

        assertThat(mimeMessage.getSubject(), is(excpectedSubject));
        assertThat(mimeMessage.getRecipients(MimeMessage.RecipientType.TO)[0], is((Address) expectedRecipient));
    }

    @Test
    public void shouldCreateMessageWithCommentsAndRecipientAsTo() throws  MessagingException ,IOException{
        String subject="Hai";
        String comments="Thanks";
        InternetAddress recipient =new InternetAddress("sukrupa.test@gmail.com");
        when(appConfiguration.properties()).thenReturn(new Properties());

        MimeMessage message =emailService.createMimeMessageWithSubjectAndRecipientAsTo(recipient,subject,comments);

        assertThat(message.getRecipients(MimeMessage.RecipientType.TO)[0], is((Address)recipient));
        assertThat(message.getSubject(), is(subject));
        assertThat((String)message.getContent(),is(comments));

    }
}
