package br.com.sgsistemas.controlerotabackend.controller;

import br.com.sgsistemas.controlerotabackend.dto.TecnicoDTO;
import br.com.sgsistemas.controlerotabackend.dto.TecnicoDetailDTO;
import br.com.sgsistemas.controlerotabackend.dto.TecnicoNewDTO;
import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import br.com.sgsistemas.controlerotabackend.services.TecnicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    private final TecnicoService tecnicoService;
    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @GetMapping
    public ResponseEntity< List<TecnicoDTO> > getAllTecnicos(){
        List<TecnicoDTO> tecnicoDTOS = tecnicoService.getAll()
                .stream()
                .map( tecnico -> new TecnicoDTO(tecnico) )
                .collect( Collectors.toList() );
        return ResponseEntity.ok().body(tecnicoDTOS);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDetailDTO> getTecnicoById(@PathVariable Long id){
        TecnicoDetailDTO tecnicoDetailDTO = new TecnicoDetailDTO(tecnicoService.getById(id));
        return ResponseEntity.ok().body(tecnicoDetailDTO);
    }
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody TecnicoNewDTO tecnicoNewDTO){
        Tecnico tecnico = tecnicoService.fromDTO(tecnicoNewDTO);
        tecnico = tecnicoService.insertTecnico(tecnico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tecnico.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateTecnico(@RequestBody TecnicoNewDTO tecnicoNewDTO, @PathVariable Long id){
        Tecnico tecnico = tecnicoService.fromDTO(tecnicoNewDTO);
        tecnico.setId(id);
        tecnico = tecnicoService.updateTecnico(tecnico);
        return ResponseEntity.noContent().build();

    }
    @DeleteMapping(value = "/{id}" )
    public  ResponseEntity<Void> deleteTecnico(@PathVariable Long id){
        tecnicoService.deleteTecnico(id);
        return ResponseEntity.noContent().build();
    }

}
