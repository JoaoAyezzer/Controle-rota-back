package br.com.sgsistemas.controlerotabackend.services;

import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import br.com.sgsistemas.controlerotabackend.repositories.TecnicoRepository;
import br.com.sgsistemas.controlerotabackend.security.UserSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    TecnicoRepository tecnicoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Tecnico tecnico = tecnicoRepository.findByEmail(email);
//        System.out.println(tecnico);
        if (tecnico == null){
            throw new UsernameNotFoundException(email);
        }

        return new UserSpringSecurity(tecnico.getId(), tecnico.getEmail(), tecnico.getSenha(), tecnico.getTipoTecnico());
    }
}
