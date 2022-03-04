package br.com.sgsistemas.controlerotabackend.services;

import br.com.sgsistemas.controlerotabackend.dto.DespesaDTO;
import br.com.sgsistemas.controlerotabackend.models.*;
import br.com.sgsistemas.controlerotabackend.models.enums.TipoTecnico;
import br.com.sgsistemas.controlerotabackend.repositories.DespesaRepository;
import br.com.sgsistemas.controlerotabackend.security.UserSpringSecurity;
import br.com.sgsistemas.controlerotabackend.services.exceptions.DataIntegrityException;
import br.com.sgsistemas.controlerotabackend.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    private final TecnicoService tecnicoService;

    private final VisitaService visitaService;

    public DespesaService(TecnicoService tecnicoService, VisitaService visitaService) {
        this.tecnicoService = tecnicoService;
        this.visitaService = visitaService;
    }

    //Listar todos as Despesas
    public List<Despesa> getAll(){
        UserSpringSecurity user = UserService.authenticated();
        if (!user.hasRole(TipoTecnico.GERENTE) || !user.hasRole(TipoTecnico.SUPERVISOR)){
            return despesaRepository.findAll().stream()
                    .filter( visita -> visita.getTecnico().getId().equals(user.getId()))
                    .collect(Collectors.toList());
        }
        return despesaRepository.findAll();
    }

    //Listar despesa por id
    public Despesa getById(Long id){
        Optional<Despesa> despesa = despesaRepository.findById(id);
        return despesa.orElseThrow(()-> new ObjectNotfoundException(
                "Objeto nao encontrado! Id: " + id + ", Tipo: " + Despesa.class.getName()
        ));
    }
    //inserir Nova Despesa
    public Despesa insert(Despesa despesa){
        despesa.setId(null);
        return despesaRepository.save(despesa);
    }
    //Update despesa
    public Despesa update(Despesa despesa){
        getById(despesa.getId());
        return despesaRepository.save(despesa);
    }
    //Delete manutenção
    public void delete(Long id){

        getById(id);
        try {
            despesaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é poossivel excluir um Veiculo que possui associações!");
        }
    }
    public Despesa fromDTO(DespesaDTO despesaDTO){
        Date data = (despesaDTO.getData() == null ) ? new Date() : despesaDTO.getData();
        try {
            Tecnico tecnico = tecnicoService.getById(despesaDTO.getTecnicoID());
            Visita visita = visitaService.getById(despesaDTO.getVisitaID());
            return new Despesa( despesaDTO.getTipoDespesa(), data, despesaDTO.getValor(), tecnico, visita );
        }catch (InvalidDataAccessApiUsageException e){
            throw new DataIntegrityException("Objeto nullo, revise os campos: tipoDespesa, tecnicoID, visitaID, valor ");
        }

    }

}
