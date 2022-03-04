package br.com.sgsistemas.controlerotabackend.services;
import br.com.sgsistemas.controlerotabackend.dto.LimpezaNewDTO;
import br.com.sgsistemas.controlerotabackend.models.Limpeza;
import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import br.com.sgsistemas.controlerotabackend.models.Veiculo;
import br.com.sgsistemas.controlerotabackend.models.enums.TipoTecnico;
import br.com.sgsistemas.controlerotabackend.repositories.LimpezaRepository;
import br.com.sgsistemas.controlerotabackend.security.UserSpringSecurity;
import br.com.sgsistemas.controlerotabackend.services.exceptions.DataIntegrityException;
import br.com.sgsistemas.controlerotabackend.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LimpezaService {

    @Autowired
    LimpezaRepository limpezaRepository;
    private final TecnicoService tecnicoService;
    private final VeiculoService veiculoService;

    public LimpezaService(TecnicoService tecnicoService, VeiculoService veiculoService) {
        this.tecnicoService = tecnicoService;
        this.veiculoService = veiculoService;
    }

    //Listar todos as Limpezas
    public List<Limpeza> getAll(){
        UserSpringSecurity user = UserService.authenticated();
        if (!user.hasRole(TipoTecnico.GERENTE) || !user.hasRole(TipoTecnico.SUPERVISOR)){
            return limpezaRepository.findAll().stream()
                    .filter( visita -> visita.getSolicitante().getId().equals(user.getId()))
                    .collect(Collectors.toList());
        }
        return limpezaRepository.findAll();
    }

    //Listar Limpeza por id
    public Limpeza getById(Long id){
        Optional<Limpeza> manutencao = limpezaRepository.findById(id);
        return manutencao.orElseThrow(()-> new ObjectNotfoundException(
                "Objeto nao encontrado! Id: " + id + ", Tipo: " + Limpeza.class.getName()
        ));
    }
    //inserir Nova Limpeza
    public Limpeza insertLimpeza(Limpeza limpeza){
        limpeza.setId(null);
        return limpezaRepository.save(limpeza);
    }
    //Update Limpeza
    public Limpeza updateLimpeza(Limpeza limpeza){
        getById(limpeza.getId());
        return limpezaRepository.save( limpeza );
    }
    //Delete Limpeza
    public void deleteLimpeza(Long id){
        getById(id);
        try {
            limpezaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é poossivel excluir um Veiculo que possui associações!");
        }
    }
    public Limpeza fromDTO(LimpezaNewDTO limpezaNewDTO){
        Date data = new Date();
        Tecnico solicitane = tecnicoService.getById(limpezaNewDTO.getSolicitante_id());
        Veiculo veiculo = veiculoService.getById(limpezaNewDTO.getVeiculo_id());
        return new Limpeza(solicitane, data, veiculo);
    }
}
