package org.sukrupa.app.services;


import com.sun.xml.internal.ws.wsdl.writer.document.soap.Body;
import com.sun.xml.internal.ws.wsdl.writer.document.soap.BodyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;
import org.sukrupa.platform.config.AppConfiguration;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.*;
import javax.swing.text.PlainDocument;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class EmailService {

    private AppConfiguration appConfiguration;

    @Autowired
    public EmailService(AppConfiguration appConfiguration) {

        this.appConfiguration = appConfiguration;
    }

    public void sendEmail(String toAddress, String subject, String messageBody) {

    }

    protected InternetAddress convertStringToInternetAddress(String emailAddress) throws AddressException {
        InternetAddress internetAddress = new InternetAddress(emailAddress);
        return internetAddress;
    }

    protected MimeMessage createMimeMessageWithSubjectAndRecipientAsTo(InternetAddress recipient, String subject) throws MessagingException {

        Session session = Session.getInstance(appConfiguration.properties());

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setSubject(subject);
        mimeMessage.setRecipient(MimeMessage.RecipientType.TO, recipient);
        return mimeMessage;
    }

    public MimeMessage createMimeMessageWithSubjectAndRecipientAsTo(InternetAddress recipient,
                                                                    String subject, String comments) throws MessagingException {
        Session session = Session.getInstance(appConfiguration.properties());

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setSubject(subject);
        mimeMessage.setRecipient(MimeMessage.RecipientType.TO, recipient);
        mimeMessage.setText(comments);
        return mimeMessage;
    }
}
