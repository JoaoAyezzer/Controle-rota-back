package br.com.sgsistemas.controlerotabackend.controller;

import br.com.sgsistemas.controlerotabackend.dto.LimpezaDTO;
import br.com.sgsistemas.controlerotabackend.dto.LimpezaNewDTO;
import br.com.sgsistemas.controlerotabackend.models.Limpeza;
import br.com.sgsistemas.controlerotabackend.services.LimpezaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "limpezas")
public class LimpezaController {

    private final LimpezaService limpezaService;

    public LimpezaController(LimpezaService limpezaService) {
        this.limpezaService = limpezaService;
    }

    @GetMapping
    public ResponseEntity<List<LimpezaDTO>> getAllAbastecimentos(){
        List<LimpezaDTO> limpezaDTOS = limpezaService.getAll()
                .stream()
                .map(LimpezaDTO::new)
                .collect( Collectors.toList() );
        return ResponseEntity.ok().body(limpezaDTOS);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<LimpezaDTO> getTecnicoById(@PathVariable Long id){
        LimpezaDTO limpezaDTO = new LimpezaDTO(limpezaService.getById(id));
        return ResponseEntity.ok().body(limpezaDTO);
    }
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody LimpezaNewDTO limpezaNewDTO){
        Limpeza limpeza = limpezaService.fromDTO(limpezaNewDTO);
        limpeza = limpezaService.insertLimpeza( limpeza );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(limpeza.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateLimpeza(@RequestBody LimpezaNewDTO limpezaNewDTO, @PathVariable Long id){
        Limpeza limpeza = limpezaService.fromDTO(limpezaNewDTO);
        limpeza.setId(id);
        limpeza = limpezaService.updateLimpeza(limpeza);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/{id}" )
    public  ResponseEntity<Void> deleteLimpeza(@PathVariable Long id){
        limpezaService.deleteLimpeza(id);
        return ResponseEntity.noContent().build();
    }
}
