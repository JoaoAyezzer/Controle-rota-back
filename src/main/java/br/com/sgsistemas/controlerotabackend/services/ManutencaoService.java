package br.com.sgsistemas.controlerotabackend.services;
import br.com.sgsistemas.controlerotabackend.dto.ManutencaoNewDTO;
import br.com.sgsistemas.controlerotabackend.models.Manutencao;
import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import br.com.sgsistemas.controlerotabackend.models.Veiculo;
import br.com.sgsistemas.controlerotabackend.models.enums.TipoTecnico;
import br.com.sgsistemas.controlerotabackend.repositories.ManutencaoRepository;
import br.com.sgsistemas.controlerotabackend.security.UserSpringSecurity;
import br.com.sgsistemas.controlerotabackend.services.exceptions.DataIntegrityException;
import br.com.sgsistemas.controlerotabackend.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManutencaoService {

    @Autowired
    ManutencaoRepository manutencaoRepository;
    private final TecnicoService tecnicoService;
    private final VeiculoService veiculoService;

    public ManutencaoService(TecnicoService tecnicoService, VeiculoService veiculoService) {
        this.tecnicoService = tecnicoService;
        this.veiculoService = veiculoService;
    }

    //Listar todos as Manutençoes
    public List<Manutencao> getAll(){
        UserSpringSecurity user = UserService.authenticated();
        if (!user.hasRole(TipoTecnico.GERENTE) || !user.hasRole(TipoTecnico.SUPERVISOR)){
            return manutencaoRepository.findAll().stream()
                    .filter( manutencao -> manutencao.getSolicitante().getId().equals(user.getId()))
                    .collect(Collectors.toList());
        }
        return manutencaoRepository.findAll();
    }

    //Listar mmanutenções por id
    public Manutencao getById(Long id){
        Optional<Manutencao> manutencao = manutencaoRepository.findById(id);
        return manutencao.orElseThrow(()-> new ObjectNotfoundException(
                "Objeto nao encontrado! Id: " + id + ", Tipo: " + Manutencao.class.getName()
        ));
    }
    //inserir Nova manutenção
    public Manutencao insertManutencao(Manutencao manutencao){
        manutencao.setId(null);
        return manutencaoRepository.save(manutencao);
    }
    //Update manutenção
    public Manutencao updateManutencao(Manutencao manutencao){
        getById(manutencao.getId());
        return manutencaoRepository.save( manutencao );
    }
    //Delete manutenção
    public void deleteManutencao(Long id){
        getById(id);
        try {
            manutencaoRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é poossivel excluir um Veiculo que possui associações!");
        }
    }
    public Manutencao fromDTO(ManutencaoNewDTO manutencaoNewDTO){
        Date data = new Date();
        Tecnico solicitane = tecnicoService.getById(manutencaoNewDTO.getSolicitante());
        Veiculo veiculo = veiculoService.getById(manutencaoNewDTO.getVeiculo());
        return new Manutencao(manutencaoNewDTO.getDescricao(),data, solicitane, veiculo.getKilometragem(), veiculo, manutencaoNewDTO.getValor());
    }

    public Page<Manutencao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return manutencaoRepository.findAll(pageRequest);
    }
}
