package br.com.sgsistemas.controlerotabackend.controller;

import br.com.sgsistemas.controlerotabackend.dto.TiClienteDTO;
import br.com.sgsistemas.controlerotabackend.models.TiCliente;
import br.com.sgsistemas.controlerotabackend.services.TiClienteService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "ti_cliente", consumes = MediaType.APPLICATION_JSON_VALUE)
public class TiClienteController {

    private final TiClienteService tiClienteService;

    public TiClienteController(TiClienteService tiClienteService) {
        this.tiClienteService = tiClienteService;
    }

    @GetMapping
    public ResponseEntity<List<TiClienteDTO>> getAllTiCliente(){
        List<TiClienteDTO> tiClienteDTOS = tiClienteService.getAll()
                .stream()
                .map(TiClienteDTO::new)
                .collect( Collectors.toList() );
        return ResponseEntity.ok().body(tiClienteDTOS);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<TiClienteDTO> getTiClienteById(@PathVariable Long id){
        TiClienteDTO tiClienteDTO = new TiClienteDTO(tiClienteService.getById(id));
        return ResponseEntity.ok().body(tiClienteDTO);
    }
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody TiClienteDTO tiClienteDTO){
        TiCliente tiCliente = tiClienteService.fromDTO(tiClienteDTO);
        tiCliente = tiClienteService.insertTiCliente( tiCliente );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tiCliente.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateTiCliente(@RequestBody TiClienteDTO tiClienteDTO, @PathVariable Long id){
        TiCliente tiCliente = tiClienteService.fromDTO(tiClienteDTO);
        tiCliente.setId(id);
        tiCliente = tiClienteService.updateTiCliente(tiCliente);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}" )
    public  ResponseEntity<Void> deleteTiCliente(@PathVariable Long id){
        tiClienteService.deleteTiCliente(id);
        return ResponseEntity.noContent().build();
    }
}
