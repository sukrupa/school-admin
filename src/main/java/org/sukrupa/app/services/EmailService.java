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

@Component
public class EmailService {

    private AppConfiguration appConfiguration;
    private Session session;

    @Autowired
    public EmailService(AppConfiguration appConfiguration) {

        this.appConfiguration = appConfiguration;
    }

    public void sendEmail(String toAddress, String subject, String messageBody) throws MessagingException {
        InternetAddress recipient =new InternetAddress(toAddress);
        Properties properties = appConfiguration.properties();

        MimeMessage mimeMessage = createMimeMessageWithSubjectAndRecipientAsTo(recipient, subject, messageBody);
        properties.put("mail.smtp.starttls.enable","true");
        String host=properties.getProperty("mail.smtp.host");
        String userName=properties.getProperty("mail.smtp.user");
        String passWord=properties.getProperty("mail.smtp.password");

//        Transport transport = session.getTransport("smtp");
//        transport.connect(host, userName, passWord);
//        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
//        transport.close();
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
        session = Session.getInstance(appConfiguration.properties());

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setSubject(subject);
        mimeMessage.setRecipient(MimeMessage.RecipientType.TO, recipient);
        mimeMessage.setText(comments);
        return mimeMessage;
    }
}
