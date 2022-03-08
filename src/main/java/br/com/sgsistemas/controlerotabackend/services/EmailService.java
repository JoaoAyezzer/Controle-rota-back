package br.com.sgsistemas.controlerotabackend.services;

import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(Tecnico tecnico, String newPass);
}
