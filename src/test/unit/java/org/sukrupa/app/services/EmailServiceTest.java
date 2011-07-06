package org.sukrupa.app.services;

import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.platform.config.AppConfiguration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class EmailServiceTest {

    @Mock
    AppConfiguration appConfiguration;


    @Test
    public void shouldSendEmail() {
        initMocks(this);
        EmailService emailService = new EmailService(appConfiguration);
        emailService.sendEmail("sabhinay@thoughtworks.com", "Testing Email service");

        verify(appConfiguration).properties();
    }


}
