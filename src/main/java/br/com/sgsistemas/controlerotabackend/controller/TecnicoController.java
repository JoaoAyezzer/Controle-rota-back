package br.com.sgsistemas.controlerotabackend.controller;

import br.com.sgsistemas.controlerotabackend.dto.TecnicoDTO;
import br.com.sgsistemas.controlerotabackend.dto.TecnicoNewDTO;
import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import br.com.sgsistemas.controlerotabackend.services.TecnicoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity< List<TecnicoDTO> > getAllTecnicos(){
        List<TecnicoDTO> tecnicoDTOS = tecnicoService.getAll()
                .stream()
                .map( tecnico -> new TecnicoDTO(tecnico) )
                .collect( Collectors.toList() );
        return ResponseEntity.ok().body(tecnicoDTOS);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> getTecnicoById(@PathVariable Long id){
        TecnicoDTO tecnicoDTO = new TecnicoDTO(tecnicoService.getById(id));
        return ResponseEntity.ok().body(tecnicoDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/page")
    public ResponseEntity<Page<TecnicoDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "lines", defaultValue = "24")  Integer lines,
            @RequestParam(value = "orderBy", defaultValue = "id")  String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC")  String direction){
        Page<Tecnico> tecnicos = tecnicoService.findPage(page, lines, orderBy, direction);
        Page<TecnicoDTO> tecnicoDTOS = tecnicos.map(tecnico -> new TecnicoDTO(tecnico));
        return ResponseEntity.ok().body(tecnicoDTOS);
    }
    @GetMapping(value = "/email")
    public ResponseEntity<TecnicoDTO> find(@RequestParam(value="value") String email) {
        TecnicoDTO tecnicoDTO = new TecnicoDTO(tecnicoService.findByEmail(email));
        return ResponseEntity.ok().body(tecnicoDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
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
    @PostMapping(value = "/{id}/picture")
    public ResponseEntity<Void> uploadTicketPicture(@PathVariable Long id, @RequestParam(name = "file") MultipartFile multipartFile){
        URI uri = tecnicoService.uploadTicketPicture(multipartFile, id);
        return ResponseEntity.created(uri).build();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateTecnico(@RequestBody TecnicoNewDTO tecnicoNewDTO, @PathVariable Long id){
        Tecnico tecnico = tecnicoService.fromDTO(tecnicoNewDTO);
        tecnico.setId(id);
        tecnico = tecnicoService.updateTecnico(tecnico);
        return ResponseEntity.noContent().build();

    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}" )
    public  ResponseEntity<Void> deleteTecnico(@PathVariable Long id){
        tecnicoService.deleteTecnico(id);
        return ResponseEntity.noContent().build();
    }

}
