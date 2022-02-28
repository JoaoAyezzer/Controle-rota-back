package br.com.sgsistemas.controlerotabackend.controller;

import br.com.sgsistemas.controlerotabackend.dto.ClienteDTO;
import br.com.sgsistemas.controlerotabackend.dto.ClienteDetailDTO;
import br.com.sgsistemas.controlerotabackend.dto.ClienteNewDTO;
import br.com.sgsistemas.controlerotabackend.models.Cliente;
import br.com.sgsistemas.controlerotabackend.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes(){
        List<ClienteDTO> clienteDTOS = clienteService.getAll()
                .stream()
                .map(ClienteDTO::new)
                .collect( Collectors.toList() );
        return ResponseEntity.ok().body(clienteDTOS);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDetailDTO> getClienteById(@PathVariable Long id){
        ClienteDetailDTO clienteDetailDTO = new ClienteDetailDTO(clienteService.getById(id));
        return ResponseEntity.ok().body(clienteDetailDTO);
    }
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody ClienteNewDTO clienteNewDTO){
        Cliente cliente = clienteService.fromDTO(clienteNewDTO);
        cliente = clienteService.insertCliente( cliente );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateManutencao(@RequestBody ClienteNewDTO clienteNewDTO, @PathVariable Long id){
        Cliente cliente = clienteService.fromDTO(clienteNewDTO);
        cliente.setId(id);
        cliente = clienteService.updateCliente(cliente);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/{id}" )
    public  ResponseEntity<Void> deleteManutencao(@PathVariable Long id){
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
