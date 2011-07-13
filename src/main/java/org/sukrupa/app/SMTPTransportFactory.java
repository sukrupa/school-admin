package org.sukrupa.app;

import com.sun.mail.smtp.SMTPTransport;
import org.springframework.stereotype.Component;

import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;

@Component
public class SMTPTransportFactory {

    public Transport getNewSMTPTransport(Properties applicationProperties) throws NoSuchProviderException {
        return Session.getDefaultInstance(applicationProperties).getTransport("smtp");
    }
}
