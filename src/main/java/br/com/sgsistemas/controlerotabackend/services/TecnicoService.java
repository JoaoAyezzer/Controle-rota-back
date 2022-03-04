package br.com.sgsistemas.controlerotabackend.services;
import br.com.sgsistemas.controlerotabackend.dto.TecnicoNewDTO;
import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import br.com.sgsistemas.controlerotabackend.models.enums.TipoTecnico;
import br.com.sgsistemas.controlerotabackend.repositories.TecnicoRepository;
import br.com.sgsistemas.controlerotabackend.security.UserSpringSecurity;
import br.com.sgsistemas.controlerotabackend.services.exceptions.AuthorizationException;
import br.com.sgsistemas.controlerotabackend.services.exceptions.DataIntegrityException;
import br.com.sgsistemas.controlerotabackend.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public List<Tecnico> getAll(){return tecnicoRepository.findAll();
    }

    public Tecnico getById(Long id){
        UserSpringSecurity user = UserService.authenticated();
        if (user == null || !user.hasRole(TipoTecnico.GERENTE) && !user.hasRole(TipoTecnico.SUPERVISOR) && !id.equals(user.getId())){
            throw  new AuthorizationException("Voce esta tentando acessar um tecnico o qual voce nao tem permissão");
        }
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElseThrow(()-> new ObjectNotfoundException(
                "Objeto nao encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()
        ));
    }
    //Insert Tecnico
    @Transactional
    public Tecnico insertTecnico(Tecnico tecnico){
        tecnico.setId(null);
        try{
            return tecnicoRepository.save(tecnico);
        }catch (DataIntegrityViolationException e){
           throw new  DataIntegrityException("ERROR! Nao foi possivel cadastrar o tecnico pois email informado ja consta na base de dados!");
        }

    }

    public static List<String> getNomeTecnidos(List<Tecnico> tecnicos){
        List<String> nomeTecnicos = new ArrayList<>();
        for ( Tecnico tecnico:tecnicos ) {
            nomeTecnicos.add(tecnico.getNome());
            System.out.println(nomeTecnicos);
        }
        return nomeTecnicos;
    }

    //Update do Tecnico
    public Tecnico updateTecnico(Tecnico tecnico){
        getById(tecnico.getId());
        return tecnicoRepository.save( tecnico );
    }
    //Delete Tecnico
    public void deleteTecnico(Long id){
        getById(id);
        try {
            tecnicoRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é poossivel excluir um Tecnico que possui associações!");
        }
    }

    //Converte DTO em Objeto
    public Tecnico fromDTO(TecnicoNewDTO tecnicoNewDTO){
        Tecnico tecnico = new Tecnico(tecnicoNewDTO.getNome(), tecnicoNewDTO.getEmail(), passwordEncoder.encode(tecnicoNewDTO.getSenha()), tecnicoNewDTO.getTipoTecnico());
        tecnico.getTelefones().add(tecnicoNewDTO.getTelefone1());
        tecnico.getTelefones().add(tecnicoNewDTO.getTelefone2());
        return tecnico;
    }

    public Page<Tecnico> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return tecnicoRepository.findAll(pageRequest);
    }
}
