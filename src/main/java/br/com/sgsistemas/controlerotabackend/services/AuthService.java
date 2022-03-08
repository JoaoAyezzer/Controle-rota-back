package br.com.sgsistemas.controlerotabackend.services;

import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import br.com.sgsistemas.controlerotabackend.repositories.TecnicoRepository;
import br.com.sgsistemas.controlerotabackend.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    private Random random = new Random();

    public void sendNewPassword(String email){
        Tecnico tecnico = tecnicoRepository.findByEmail(email);
        if (tecnico == null){
            throw new ObjectNotfoundException("Email não encontrado na base de dados");

        }
        String newPass = newPassword();
        tecnico.setSenha(passwordEncoder.encode(newPass));

        tecnicoRepository.save(tecnico);
        emailService.sendNewPasswordEmail(tecnico, newPass);


    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i=0; i<10; i++){
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = random.nextInt(3);
        if (opt == 0){
            return (char) (random.nextInt(10) + 48);
        }
        else if (opt == 1){
            return (char) (random.nextInt(26) + 65);
        }
        else{
            return (char) (random.nextInt(26) + 97);
        }
    }

}
