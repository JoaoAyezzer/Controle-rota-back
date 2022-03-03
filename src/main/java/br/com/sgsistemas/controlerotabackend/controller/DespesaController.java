package br.com.sgsistemas.controlerotabackend.controller;

import br.com.sgsistemas.controlerotabackend.dto.DespesaDTO;
import br.com.sgsistemas.controlerotabackend.models.Despesa;
import br.com.sgsistemas.controlerotabackend.services.DespesaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping(value = "/{id}")
    public ResponseEntity<DespesaDTO> getDespesasById(@PathVariable Long id){
        DespesaDTO despesaDTO = new DespesaDTO(despesaService.getById(id));
        return ResponseEntity.ok().body(despesaDTO);
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
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateDespesa(@RequestBody DespesaDTO despesaDTO, @PathVariable Long id){
        Despesa despesa = despesaService.fromDTO(despesaDTO);
        despesa.setId(id);
        despesa = despesaService.update(despesa);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/{id}" )
    public  ResponseEntity<Void> deleteDespesa(@PathVariable Long id){
        despesaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
