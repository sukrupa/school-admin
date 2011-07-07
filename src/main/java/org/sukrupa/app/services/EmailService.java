package org.sukrupa.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sukrupa.platform.config.AppConfiguration;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class EmailService {

    private AppConfiguration appConfiguration;

    @Autowired
    public EmailService(AppConfiguration appConfiguration) {

        this.appConfiguration = appConfiguration;
    }

    public void sendEmail(String toAddress, String subject) {
 
    }

    protected InternetAddress convertStringToInternetAddress(String emailAddress) throws AddressException {
        InternetAddress internetAddress = new InternetAddress(emailAddress);
        return internetAddress;
    }

    public MimeMessage createMimeMessageWithSubjectAndRecipientAsTo(String subject, InternetAddress recipient) throws MessagingException {

        Session session = Session.getInstance(appConfiguration.properties());

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setSubject(subject);
        mimeMessage.setRecipient(MimeMessage.RecipientType.TO, recipient);
        return mimeMessage;

    }
}
