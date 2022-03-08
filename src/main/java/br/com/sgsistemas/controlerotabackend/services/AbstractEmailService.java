package br.com.sgsistemas.controlerotabackend.services;

import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.sender}")
    private String sender;
    @Override
    public void sendNewPasswordEmail(Tecnico tecnico, String newPass) {
        SimpleMailMessage simpleMailMessage = prepareNewPasswordEmail(tecnico, newPass);
        sendEmail(simpleMailMessage);
    }

    private SimpleMailMessage prepareNewPasswordEmail(Tecnico tecnico, String newPass) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(tecnico.getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Solicitação de nova senha");
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText("Nova senha: " + newPass);
        return simpleMailMessage;
    }
}
