package br.com.sgsistemas.controlerotabackend.controller;

import br.com.sgsistemas.controlerotabackend.dto.ClienteDTO;
import br.com.sgsistemas.controlerotabackend.dto.DespesaDTO;
import br.com.sgsistemas.controlerotabackend.dto.DespesaReadDTO;
import br.com.sgsistemas.controlerotabackend.models.Cliente;
import br.com.sgsistemas.controlerotabackend.models.Despesa;
import br.com.sgsistemas.controlerotabackend.services.DespesaService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "despesas")
public class DespesaController {

    private final DespesaService despesaService;

    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    @GetMapping
    public ResponseEntity<List<DespesaDTO>> getAllDespesas(){
        List<DespesaDTO> despesaDTOS = despesaService.getAll()
                .stream()
                .map(DespesaDTO::new)
                .collect( Collectors.toList() );
        return ResponseEntity.ok().body(despesaDTOS);
    }
    @GetMapping(value = "/page")
    public ResponseEntity<Page<DespesaDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "lines", defaultValue = "24")  Integer lines,
            @RequestParam(value = "orderBy", defaultValue = "id")  String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC")  String direction){
        Page<Despesa> despesas = despesaService.findPage(page, lines, orderBy, direction);
        Page<DespesaDTO> despesaDTOS = despesas.map(despesa -> new DespesaDTO(despesa));
        return ResponseEntity.ok().body(despesaDTOS);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<DespesaReadDTO> getDespesasById(@PathVariable Long id){
        DespesaReadDTO despesaReadDTO = new DespesaReadDTO(despesaService.getById(id));
        return ResponseEntity.ok().body(despesaReadDTO);
    }
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody DespesaDTO despesaDTO){
        Despesa despesa = despesaService.fromDTO(despesaDTO);
        despesa = despesaService.insert( despesa );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(despesa.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @PostMapping(value = "/{id}/picture")
    public ResponseEntity<Void> uploadTicketPicture(@PathVariable Long id, @RequestParam(name = "file") MultipartFile multipartFile){
        URI uri = despesaService.uploadTicketPicture(multipartFile, id);
        return ResponseEntity.created(uri).build();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateDespesa(@RequestBody DespesaDTO despesaDTO, @PathVariable Long id){
        Despesa despesa = despesaService.fromDTO(despesaDTO);
        despesa.setId(id);
        despesa = despesaService.update(despesa);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}" )
    public  ResponseEntity<Void> deleteDespesa(@PathVariable Long id){
        despesaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
