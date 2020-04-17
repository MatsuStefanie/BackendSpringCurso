package com.cristal.stefanie.cursomc.config;

import com.cristal.stefanie.cursomc.services.DBService;
import com.cristal.stefanie.cursomc.services.EmailService;
import com.cristal.stefanie.cursomc.services.MockEmailService;
import com.cristal.stefanie.cursomc.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${cristal.gym.control.start-db}")
    private Boolean iniciarBanco;

    @Bean
    public boolean instatiateDatabase() throws ParseException {
        if (Boolean.FALSE.equals(iniciarBanco)) {
            return false;
        }
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }
}
