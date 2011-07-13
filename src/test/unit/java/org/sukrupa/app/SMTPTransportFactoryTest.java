package org.sukrupa.app;


import com.sun.mail.smtp.SMTPTransport;
import org.junit.Test;

import javax.mail.NoSuchProviderException;
import javax.mail.Transport;

import java.util.Properties;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SMTPTransportFactoryTest {

    @Test
    public void shouldCreateATransportInstants() throws NoSuchProviderException {
        SMTPTransportFactory factory = new SMTPTransportFactory();
        Transport transport = factory.getNewSMTPTransport(new Properties());

        assertEquals(SMTPTransport.class, transport.getClass());
    }
}
