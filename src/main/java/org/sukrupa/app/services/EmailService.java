package org.sukrupa.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sukrupa.platform.config.AppConfiguration;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import java.util.Properties;

@Component
public class EmailService {

    private AppConfiguration appConfiguration;

    @Autowired
    public EmailService(AppConfiguration appConfiguration) {

        this.appConfiguration = appConfiguration;
    }

    public void sendEmail(String toAddress, String subject) {
        appConfiguration.properties();

    }

    public InternetAddress convertStringToInternetAddress(String emailAddress) throws AddressException {
        InternetAddress internetAddress = new InternetAddress(emailAddress);
        return internetAddress;
    }
}
