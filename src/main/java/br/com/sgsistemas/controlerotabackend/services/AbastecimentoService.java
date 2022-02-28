package br.com.sgsistemas.controlerotabackend.services;

import br.com.sgsistemas.controlerotabackend.dto.AbastecimentoNewDTO;
import br.com.sgsistemas.controlerotabackend.models.Abastecimento;
import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import br.com.sgsistemas.controlerotabackend.models.Veiculo;
import br.com.sgsistemas.controlerotabackend.repositories.AbastecimentoRepository;
import br.com.sgsistemas.controlerotabackend.services.exceptions.DataIntegrityException;
import br.com.sgsistemas.controlerotabackend.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
}
