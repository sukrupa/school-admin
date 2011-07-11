package org.sukrupa.app.services;


import com.sun.mail.smtp.SMTPSaslAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sukrupa.platform.config.AppConfiguration;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class EmailService {

    private AppConfiguration appConfiguration;
    private Session session;


    @Autowired
    public EmailService(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }

     public boolean sendNewsLetter(String toAddress, String subject, String comments, String attachment) throws MessagingException {
        try{
        InternetAddress toRecipientAddress = convertStringToInternetAddress(toAddress);
        attachment = extractAttachmentFileAddress(attachment);

        MimeMessage emailMessage = createMimeMessageWithSubjectAndRecipientAsToAndAttachment(toRecipientAddress, subject, comments, attachment);

        Properties applicationProperties = appConfiguration.properties();
        applicationProperties.put("mail.smtp.auth", "true");
        applicationProperties.put("mail.smtp.starttls.enable", "true");

        Transport transport = Session.getDefaultInstance(applicationProperties).getTransport("smtp");
        transport.connect(applicationProperties.getProperty("mail.smtp.host"),Integer.parseInt(applicationProperties.getProperty("mail.smtp.port")),applicationProperties.getProperty("mail.smtp.user"),applicationProperties.getProperty("mail.smtp.password"));
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());

        transport.close();
            return true;
        }
        catch (MessagingException e){
            return false;
        }
    }

    protected String extractAttachmentFileAddress(String attachment) {
        attachment=attachment.substring(attachment.indexOf('\\'));
        attachment=attachment.replace('\\','/');
        return attachment;
    }

    public boolean sendEmail(String toAddress, String subject, String messageBody) throws MessagingException {
        try{
        InternetAddress toRecipientAddress = convertStringToInternetAddress(toAddress);
        MimeMessage emailMessage = createMimeMessageWithSubjectAndRecipientAsTo(toRecipientAddress, subject, "");
        Properties applicationProperties = appConfiguration.properties();

        applicationProperties.put("mail.smtp.auth", "true");
        applicationProperties.put("mail.smtp.starttls.enable", "true");

        Transport transport = Session.getDefaultInstance(applicationProperties).getTransport("smtp");
        transport.connect(applicationProperties.getProperty("mail.smtp.host"),Integer.parseInt(applicationProperties.getProperty("mail.smtp.port")),applicationProperties.getProperty("mail.smtp.user"),applicationProperties.getProperty("mail.smtp.password"));
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
//        transport.send(emailMessage);

        transport.close();
            return true;
        }
        catch (MessagingException e){
            return false;
        }
    }

    protected InternetAddress convertStringToInternetAddress(String emailAddress) throws AddressException {
        InternetAddress internetAddress = new InternetAddress(emailAddress);
        return internetAddress;
    }

    protected MimeMessage createMimeMessageWithSubjectAndRecipientAsToAndAttachment(InternetAddress recipient, String subject, String comments, String attachment) throws MessagingException {

        //Properties applicationProperties = appConfiguration.properties();
        session = Session.getDefaultInstance(appConfiguration.properties());

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setSubject(subject);
        mimeMessage.setRecipient(MimeMessage.RecipientType.TO, recipient);

        MimeBodyPart messageBody = new MimeBodyPart();
        messageBody.setText(comments);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBody);

        MimeBodyPart messageAttachment = new MimeBodyPart();
        FileDataSource fileSource = new FileDataSource(attachment);
        
        messageAttachment.setDataHandler(new DataHandler(fileSource));
        messageAttachment.setFileName(attachment);
        multipart.addBodyPart(messageAttachment);
        mimeMessage.setContent(multipart);
        
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
