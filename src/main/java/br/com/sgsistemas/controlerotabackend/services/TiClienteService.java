package br.com.sgsistemas.controlerotabackend.services;
import br.com.sgsistemas.controlerotabackend.dto.TiClienteDTO;
import br.com.sgsistemas.controlerotabackend.models.TiCliente;
import br.com.sgsistemas.controlerotabackend.repositories.TiClienteRepository;
import br.com.sgsistemas.controlerotabackend.services.exceptions.DataIntegrityException;
import br.com.sgsistemas.controlerotabackend.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TiClienteService {

    @Autowired
    TiClienteRepository tiClienteRepository;

    private final TecnicoService tecnicoService;
    private final VeiculoService veiculoService;

    public TiClienteService(TecnicoService tecnicoService, VeiculoService veiculoService, TiClienteRepository tiClienteRepository) {
        this.tecnicoService = tecnicoService;
        this.veiculoService = veiculoService;
        this.tiClienteRepository = tiClienteRepository;
    }

    //Listar todos os ti relacionados a clientes
    public List<TiCliente> getAll(){
        return tiClienteRepository.findAll();
    }

    //Listar Abastecimentos por id
    public TiCliente getById(Long id){
        Optional<TiCliente> tiCliente = tiClienteRepository.findById(id);
        return tiCliente.orElseThrow(()-> new ObjectNotfoundException(
                "Objeto nao encontrado! Id: " + id + ", Tipo: " + TiCliente.class.getName()
        ));
    }
    //inserir Novo ti do cliente
    public TiCliente insertTiCliente(TiCliente tiCliente){
        tiCliente.setId(null);
        return tiClienteRepository.save(tiCliente);
    }
    //Update ti do cliente
    public TiCliente updateTiCliente(TiCliente tiCliente){
        getById(tiCliente.getId());
        return tiClienteRepository.save( tiCliente );
    }
    //Delete ti do cliente
    public void deleteTiCliente(Long id){
        getById(id);
        try {
            tiClienteRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é poossivel excluir um Veiculo que possui associações!");
        }
    }
    public TiCliente fromDTO(TiClienteDTO tiClienteDTO){
        return new TiCliente(tiClienteDTO.getNome(), tiClienteDTO.getTelefone(), tiClienteDTO.getEmail());
    }
}
