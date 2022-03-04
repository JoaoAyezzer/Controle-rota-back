package br.com.sgsistemas.controlerotabackend.controller;
import br.com.sgsistemas.controlerotabackend.dto.ManutencaoDTO;
import br.com.sgsistemas.controlerotabackend.dto.ManutencaoNewDTO;
import br.com.sgsistemas.controlerotabackend.models.Manutencao;
import br.com.sgsistemas.controlerotabackend.services.ManutencaoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "manutencoes", consumes = MediaType.APPLICATION_JSON_VALUE)
public class ManutencaoController {

    private final ManutencaoService manutencaoService;

    public ManutencaoController(ManutencaoService manutencaoService) {
        this.manutencaoService = manutencaoService;
    }

    @GetMapping
    public ResponseEntity<List<ManutencaoDTO>> getAllMantencoes(){
        List<ManutencaoDTO> manutencaoDTO = manutencaoService.getAll()
                .stream()
                .map(ManutencaoDTO::new)
                .collect( Collectors.toList() );
        return ResponseEntity.ok().body(manutencaoDTO);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ManutencaoDTO> getManutencaoById(@PathVariable Long id){
        ManutencaoDTO manutencaoDTO = new ManutencaoDTO(manutencaoService.getById(id));
        return ResponseEntity.ok().body(manutencaoDTO);
    }
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody ManutencaoNewDTO manutencaoDTO){
        Manutencao manutencao = manutencaoService.fromDTO(manutencaoDTO);
        manutencao = manutencaoService.insertManutencao( manutencao );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(manutencao.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateManutencao(@RequestBody ManutencaoNewDTO manutencaoNewDTO, @PathVariable Long id){
        Manutencao manutencao = manutencaoService.fromDTO(manutencaoNewDTO);
        manutencao.setId(id);
        manutencao = manutencaoService.updateManutencao(manutencao);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}" )
    public  ResponseEntity<Void> deleteManutencao(@PathVariable Long id){
        manutencaoService.deleteManutencao(id);
        return ResponseEntity.noContent().build();
    }
}
