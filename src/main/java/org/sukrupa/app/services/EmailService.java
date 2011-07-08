package org.sukrupa.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;
import org.sukrupa.platform.config.AppConfiguration;

import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.text.PlainDocument;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.transaction.Transaction;
import java.net.*;
import java.net.Authenticator;
import java.util.Properties;

@Component
public class EmailService {

    private AppConfiguration appConfiguration;
    private Session session;

    @Autowired
    public EmailService(AppConfiguration appConfiguration) {

        this.appConfiguration = appConfiguration;
    }


    public void sendEmail(String toAddress, String subject, String messageBody) throws MessagingException {

        InternetAddress toRecipientAddress = convertStringToInternetAddress(toAddress);
        MimeMessage emailMessage = createMimeMessageWithSubjectAndRecipientAsTo(toRecipientAddress, subject);
        MimeMessage mimeMessage = createMimeMessageWithSubjectAndRecipientAsTo(toRecipientAddress, subject, messageBody);
        Properties applicationProperties = appConfiguration.properties();

        applicationProperties.put("mail.smtp.auth", "true");
        applicationProperties.put("mail.smtp.starttls.enable", "true");

        Transport transport = Session.getDefaultInstance(applicationProperties).getTransport("smtp");
        transport.connect(applicationProperties.getProperty("mail.smtp.host"),Integer.parseInt(applicationProperties.getProperty("mail.smtp.port")),applicationProperties.getProperty("mail.smtp.user"),applicationProperties.getProperty("mail.smtp.password"));
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
    }

    protected InternetAddress convertStringToInternetAddress(String emailAddress) throws AddressException {
        InternetAddress internetAddress = new InternetAddress(emailAddress);
        return internetAddress;
    }

    protected MimeMessage createMimeMessageWithSubjectAndRecipientAsTo(InternetAddress recipient, String subject) throws MessagingException {

        Properties applicationProperties = appConfiguration.properties();
        Session session = Session.getDefaultInstance(applicationProperties,null);
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setSubject(subject);

        //Sri and Abhi : Change/Modify Content based on needs.
        mimeMessage.setContent("Monthly Newsletter","text/html");
        mimeMessage.setRecipient(MimeMessage.RecipientType.TO, recipient);
        return mimeMessage;
    }

    public MimeMessage createMimeMessageWithSubjectAndRecipientAsTo(InternetAddress recipient,
                                                                    String subject, String comments) throws MessagingException {
        session = Session.getInstance(appConfiguration.properties());

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setSubject(subject);
        mimeMessage.setRecipient(MimeMessage.RecipientType.TO, recipient);
        mimeMessage.setText(comments);
        return mimeMessage;
    }
}
