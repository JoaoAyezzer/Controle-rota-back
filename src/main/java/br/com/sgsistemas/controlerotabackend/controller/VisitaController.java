package br.com.sgsistemas.controlerotabackend.controller;

import br.com.sgsistemas.controlerotabackend.dto.FinalizarVisitaDTO;
import br.com.sgsistemas.controlerotabackend.dto.VisitaReadDTO;
import br.com.sgsistemas.controlerotabackend.dto.VisitaNewDTO;
import br.com.sgsistemas.controlerotabackend.models.Visita;
import br.com.sgsistemas.controlerotabackend.services.VisitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/visitas")
public class VisitaController {

    private final VisitaService visitaService;

    public VisitaController(VisitaService visitaService) {
        this.visitaService = visitaService;
    }

    @GetMapping
    public ResponseEntity<List<VisitaReadDTO>> getAllVisitas(){
        List<VisitaReadDTO> visitaReadDTOS = visitaService.getAll()
                .stream()
                .map(VisitaReadDTO::new)
                .collect( Collectors.toList() );
        return ResponseEntity.ok().body(visitaReadDTOS);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<VisitaReadDTO> getVisitaById(@PathVariable Long id){
        VisitaReadDTO visitaReadDTO = new VisitaReadDTO(visitaService.getById(id));
        return ResponseEntity.ok().body(visitaReadDTO);
    }
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody VisitaNewDTO visitaNewDTO){
        Visita visita = visitaService.fromDTO(visitaNewDTO);
        visita = visitaService.insertVisita( visita );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(visita.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateVisita(@RequestBody VisitaNewDTO visitaNewDTO, @PathVariable Long id){
        Visita visita = visitaService.fromDTO(visitaNewDTO);
        visita.setId(id);
        visita = visitaService.updateVisita(visita);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}/finalizar")
    public ResponseEntity<Void> updateVisita(@RequestBody FinalizarVisitaDTO finalizarVisitaDTO, @PathVariable Long id){
        Visita visita = visitaService.finalizaVisita(finalizarVisitaDTO, id);
        visita = visitaService.updateVisita(visita);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/{id}" )
    public  ResponseEntity<Void> deleteManutencao(@PathVariable Long id){
        visitaService.deleteVisita(id);
        return ResponseEntity.noContent().build();
    }
}
