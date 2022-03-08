package br.com.sgsistemas.controlerotabackend.config;

import br.com.sgsistemas.controlerotabackend.services.EmailService;
import br.com.sgsistemas.controlerotabackend.services.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }
    
}
