package br.com.sgsistemas.controlerotabackend.controller;
import br.com.sgsistemas.controlerotabackend.dto.AbastecimentoDTO;
import br.com.sgsistemas.controlerotabackend.dto.AbastecimentoNewDTO;
import br.com.sgsistemas.controlerotabackend.models.Abastecimento;
import br.com.sgsistemas.controlerotabackend.services.AbastecimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "abastecimentos")
public class AbastecimentoController {

    private final AbastecimentoService abastecimentoService;

    public AbastecimentoController(AbastecimentoService abastecimentoService) {
        this.abastecimentoService = abastecimentoService;
    }

    @GetMapping
    public ResponseEntity<List<AbastecimentoDTO>> getAllAbastecimentos(){
        List<AbastecimentoDTO> abastecimentoDTOS = abastecimentoService.getAll()
                .stream()
                .map(AbastecimentoDTO::new)
                .collect( Collectors.toList() );
        return ResponseEntity.ok().body(abastecimentoDTOS);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<AbastecimentoDTO> getTecnicoById(@PathVariable Long id){
        AbastecimentoDTO abastecimentoDTO = new AbastecimentoDTO(abastecimentoService.getById(id));
        return ResponseEntity.ok().body(abastecimentoDTO);
    }
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody AbastecimentoNewDTO abastecimentoNewDTO){
        Abastecimento abastecimento = abastecimentoService.fromDTO(abastecimentoNewDTO);
        abastecimento = abastecimentoService.insertAbastecimento( abastecimento );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(abastecimento.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateManutencao(@RequestBody AbastecimentoNewDTO abastecimentoNewDTO, @PathVariable Long id){
        Abastecimento abastecimento = abastecimentoService.fromDTO(abastecimentoNewDTO);
        abastecimento.setId(id);
        abastecimento = abastecimentoService.updateAbastecimento(abastecimento);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/{id}" )
    public  ResponseEntity<Void> deleteManutencao(@PathVariable Long id){
        abastecimentoService.deleteAbastecimento(id);
        return ResponseEntity.noContent().build();
    }
}
