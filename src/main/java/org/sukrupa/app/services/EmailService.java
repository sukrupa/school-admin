package org.sukrupa.app.services;


import com.sun.mail.smtp.SMTPTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sukrupa.app.SMTPTransportFactory;
import org.sukrupa.platform.config.AppConfiguration;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.sound.midi.MidiMessage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

@Component
public class EmailService {

    private AppConfiguration appConfiguration;
    private SMTPTransportFactory transportFactory;
    private Transport transportMock;
    private Session session;

    @Autowired
    public EmailService(AppConfiguration appConfiguration, SMTPTransportFactory transportFactory) {
        this.appConfiguration = appConfiguration;
        this.transportFactory = transportFactory;
    }

     public boolean sendNewsLetter(String toAddress, String bcc, String subject, String comments, String attachment) throws MessagingException, IOException {
        InternetAddress toRecipientAddress = convertStringToInternetAddress(toAddress);
         attachment = extractAttachmentFileAddress(attachment);
         File tempFile = new File(attachment);

          Message emailMessage;
         if(bcc.equals("")){
           emailMessage = createMimeMessageWithSubjectAndRecipientAsToAndAttachment(toRecipientAddress,subject,comments,attachment);
         }
         else {
        Address[] bccRecipients= convertStringToAddressArray(bcc);
         emailMessage = createMimeMessageWithSubjectAndRecipientAsToBccAndAttachment(toRecipientAddress,bccRecipients,subject,comments,attachment);
         }

        Properties applicationProperties = appConfiguration.properties();
        applicationProperties.put("mail.smtp.auth", "true");
        applicationProperties.put("mail.smtp.starttls.enable", "true");

        Transport transport = Session.getDefaultInstance(applicationProperties).getTransport("smtp");

        transport.connect(applicationProperties.getProperty("mail.smtp.host"),Integer.parseInt(applicationProperties.getProperty("mail.smtp.port")),applicationProperties.getProperty("mail.smtp.user"),applicationProperties.getProperty("mail.smtp.password"));

         transport.sendMessage(emailMessage, emailMessage.getAllRecipients());

        transport.close();
            return true;
     }

    protected String extractAttachmentFileAddress(String attachment) {
        attachment = attachment.substring(attachment.indexOf('\\'));
        attachment = attachment.replace('\\', '/');
        return attachment;
    }


    public boolean sendEmail(String toAddress, String subject, String messageBody) {
        try {
            InternetAddress toRecipientAddress = convertStringToInternetAddress(toAddress);
            MimeMessage emailMessage = createMimeMessageWithSubjectAndRecipientAsTo(toRecipientAddress, subject, messageBody);
            Properties applicationProperties = appConfiguration.properties();

            applicationProperties.put("mail.smtp.auth", "true");
            applicationProperties.put("mail.smtp.starttls.enable", "true");

            Transport transport = transportFactory.getNewSMTPTransport(applicationProperties);
            transport.connect(applicationProperties.getProperty("mail.smtp.host"), Integer.parseInt(applicationProperties.getProperty("mail.smtp.port")), applicationProperties.getProperty("mail.smtp.user"), applicationProperties.getProperty("mail.smtp.password"));
            transport.sendMessage(emailMessage, emailMessage.getAllRecipients());

            transport.close();

            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    protected InternetAddress convertStringToInternetAddress(String emailAddress) throws AddressException {
        InternetAddress internetAddress = new InternetAddress(emailAddress);
        return internetAddress;
    }
    protected Address[] convertStringToAddressArray(String bcc) throws AddressException {
        String[] individualEmailAddressArray=bcc.split(";");
        Address[] addresses = new Address[individualEmailAddressArray.length];
        for(int i=0; i< individualEmailAddressArray.length;i++){
            InternetAddress internetAddress = new InternetAddress(individualEmailAddressArray[i]);
            addresses[i]=internetAddress;
        }
        return addresses;
    }

    protected MimeMessage createMimeMessageWithSubjectAndRecipientAsToAndAttachment(InternetAddress recipient, String subject, String comments, String attachment) throws MessagingException, IOException {

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
     protected MimeMessage createMimeMessageWithSubjectAndRecipientAsToBccAndAttachment(InternetAddress recipient,Address[] bcc, String subject, String comments, String attachment) throws MessagingException, IOException {

        session = Session.getDefaultInstance(appConfiguration.properties());
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setSubject(subject);
        mimeMessage.setRecipient(MimeMessage.RecipientType.TO, recipient);
        mimeMessage.setRecipients(MimeMessage.RecipientType.BCC,bcc);

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
        mimeMessage.setContent(comments, "text/html");
        return mimeMessage;
    }

public MimeMessage createMimeMessageWithBccSubjectAndRecipientAsTo(InternetAddress recipient,
                                                                    Address[] bcc,String subject, String comments) throws MessagingException {
        session = Session.getInstance(appConfiguration.properties());

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setSubject(subject);
        mimeMessage.setRecipient(MimeMessage.RecipientType.TO, recipient);
        mimeMessage.setRecipients(MimeMessage.RecipientType.BCC,bcc);
        mimeMessage.setContent(comments, "text/html");
        return mimeMessage;
    }


    public boolean sendNewsLetterEmailWithoutAttachment(String to, String bcc, String subject, String comments) {
        try{
        InternetAddress toRecipientAddress = convertStringToInternetAddress(to);
        Address[] bccRecipients= convertStringToAddressArray(bcc);
        MimeMessage emailMessage = createMimeMessageWithBccSubjectAndRecipientAsTo(toRecipientAddress,bccRecipients,subject,comments);
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
}
