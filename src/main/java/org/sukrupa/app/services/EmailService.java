package org.sukrupa.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sukrupa.platform.config.AppConfiguration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.PublicKey;
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

}
