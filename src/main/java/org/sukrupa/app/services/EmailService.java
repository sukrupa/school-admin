package org.sukrupa.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sukrupa.platform.config.AppConfiguration;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

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

    public MimeMessage createMimeMessageWithSubject(String subject) throws MessagingException {
        Session session = Session.getInstance(appConfiguration.properties());

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setSubject(subject);

        return mimeMessage;

    }
}
