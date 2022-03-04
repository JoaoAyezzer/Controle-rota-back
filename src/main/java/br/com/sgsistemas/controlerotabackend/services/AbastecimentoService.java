package br.com.sgsistemas.controlerotabackend.services;

import br.com.sgsistemas.controlerotabackend.dto.AbastecimentoNewDTO;
import br.com.sgsistemas.controlerotabackend.models.Abastecimento;
import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import br.com.sgsistemas.controlerotabackend.models.Veiculo;
import br.com.sgsistemas.controlerotabackend.models.Visita;
import br.com.sgsistemas.controlerotabackend.models.enums.TipoTecnico;
import br.com.sgsistemas.controlerotabackend.repositories.AbastecimentoRepository;
import br.com.sgsistemas.controlerotabackend.security.UserSpringSecurity;
import br.com.sgsistemas.controlerotabackend.services.exceptions.DataIntegrityException;
import br.com.sgsistemas.controlerotabackend.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AbastecimentoService {

    @Autowired
    AbastecimentoRepository abastecimentoRepository;
    ZoneId asiaSingapore = ZoneId.of("America/Sao_Paulo");

    private final TecnicoService tecnicoService;
    private final VeiculoService veiculoService;

    public AbastecimentoService(TecnicoService tecnicoService, VeiculoService veiculoService, AbastecimentoRepository abastecimentoRepository) {
        this.tecnicoService = tecnicoService;
        this.veiculoService = veiculoService;
        this.abastecimentoRepository = abastecimentoRepository;
    }

    //Listar todos os Abastecimentos
    public List<Abastecimento> getAll(){
        UserSpringSecurity user = UserService.authenticated();

        if (!user.hasRole(TipoTecnico.GERENTE) || !user.hasRole(TipoTecnico.SUPERVISOR)){
            return abastecimentoRepository.findAll().stream()
                    .filter( abastecimento -> abastecimento.getTecnico().getId().equals(user.getId()))
                    .collect(Collectors.toList());
        }
        return abastecimentoRepository.findAll();
    }

    //Listar Abastecimentos por id
    public Abastecimento getById(Long id){
        Optional<Abastecimento> abastecimento = abastecimentoRepository.findById(id);
        return abastecimento.orElseThrow(()-> new ObjectNotfoundException(
                "Objeto nao encontrado! Id: " + id + ", Tipo: " + Abastecimento.class.getName()
        ));
    }
    //inserir Novo Abastecimento
    public Abastecimento insertAbastecimento(Abastecimento abastecimento){
        abastecimento.setId(null);
        return abastecimentoRepository.save(abastecimento);
    }
    //Update Abastecimento
    public Abastecimento updateAbastecimento(Abastecimento abastecimento){
        getById(abastecimento.getId());
        return abastecimentoRepository.save( abastecimento );
    }
    //Delete Abastecimento
    public void deleteAbastecimento(Long id){
        getById(id);
        try {
            abastecimentoRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é poossivel excluir um Veiculo que possui associações!");
        }
    }
    public Abastecimento fromDTO(AbastecimentoNewDTO abastecimentoNewDTO){
        Date data = new Date();
        Tecnico tecnico = tecnicoService.getById(abastecimentoNewDTO.getTecnico_id());
        Veiculo veiculo = veiculoService.getById(abastecimentoNewDTO.getVeiculo_id());
        return new Abastecimento(veiculo, abastecimentoNewDTO.getLitros(), veiculo.getKilometragem(), data, tecnico, abastecimentoNewDTO.getValor());
    }

    public Page<Abastecimento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return abastecimentoRepository.findAll(pageRequest);
    }
}
