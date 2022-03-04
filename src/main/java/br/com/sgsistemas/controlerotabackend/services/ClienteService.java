package br.com.sgsistemas.controlerotabackend.services;

import br.com.sgsistemas.controlerotabackend.dto.ClienteNewDTO;
import br.com.sgsistemas.controlerotabackend.models.Cliente;
import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import br.com.sgsistemas.controlerotabackend.models.TiCliente;
import br.com.sgsistemas.controlerotabackend.models.enums.TipoTecnico;
import br.com.sgsistemas.controlerotabackend.repositories.ClienteRepository;
import br.com.sgsistemas.controlerotabackend.security.UserSpringSecurity;
import br.com.sgsistemas.controlerotabackend.services.exceptions.DataIntegrityException;
import br.com.sgsistemas.controlerotabackend.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final TecnicoService tecnicoService;
    private final TiClienteService tiClienteService;

    @Autowired
    ClienteRepository clienteRepository;

    public ClienteService(TecnicoService tecnicoService, TiClienteService tiClienteService) {
        this.tecnicoService = tecnicoService;
        this.tiClienteService = tiClienteService;

    }

    //Busca todos os Clientes
    public List<Cliente> getAll(){
        UserSpringSecurity user = UserService.authenticated();
        if (!user.hasRole(TipoTecnico.GERENTE) || !user.hasRole(TipoTecnico.SUPERVISOR)){
            return clienteRepository.findAll().stream()
                    .filter( visita -> visita.getTecnicoResponsavel().getId().equals(user.getId()))
                    .collect(Collectors.toList());
        }
        return clienteRepository.findAll();
    }

    //busca clientes por id
    public Cliente getById(Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(()-> new ObjectNotfoundException(
                "Objeto nao encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()
        ));
    }


    //Insert Clientes
    @Transactional
    public Cliente insertCliente(Cliente cliente){
        cliente.setId(null);
        return clienteRepository.save(cliente);
    }

    //Update Cliente
    public Cliente updateCliente(Cliente cliente){
        getById(cliente.getId());
        return clienteRepository.save( cliente );
    }
    //Delete Cliente
    public void deleteCliente(Long id){
        getById(id);
        try {
            clienteRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é poossivel excluir um Tecnico que possui associações!");
        }
    }

    //Converte DTO em Objeto
    public Cliente fromDTO(ClienteNewDTO clienteNewDTO){
        Tecnico tecnico = tecnicoService.getById(clienteNewDTO.getTecnicoResponsavelId());
        TiCliente tiCliente = (clienteNewDTO.getTiLocalId() == null) ? null : tiClienteService.getById(clienteNewDTO.getTiLocalId());
        Cliente cliente = new Cliente(clienteNewDTO.getNomeFantasia(), clienteNewDTO.getCnpj(),clienteNewDTO.getSenhaDeRoot(), tecnico, tiCliente);
        cliente.getTelefones().add(clienteNewDTO.getTelefone1());
        cliente.getTelefones().add(clienteNewDTO.getTelefone2());
        return cliente;
    }
}
