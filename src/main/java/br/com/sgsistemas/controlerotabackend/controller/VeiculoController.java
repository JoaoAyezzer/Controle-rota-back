package br.com.sgsistemas.controlerotabackend.controller;

import br.com.sgsistemas.controlerotabackend.dto.VeiculoDTO;
import br.com.sgsistemas.controlerotabackend.dto.VeiculoDetailDTO;
import br.com.sgsistemas.controlerotabackend.models.Veiculo;
import br.com.sgsistemas.controlerotabackend.services.VeiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public ResponseEntity< List<VeiculoDTO> > getAllVeiculos(){
        List<VeiculoDTO> veiculoDTOS = veiculoService.getAll()
                .stream()
                .map( veiculo -> new VeiculoDTO(veiculo) )
                .collect( Collectors.toList() );
        return ResponseEntity.ok().body(veiculoDTOS);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VeiculoDetailDTO> getVeiculoById(@PathVariable Long id){
        VeiculoDetailDTO veiculoDetailDTO = new VeiculoDetailDTO(veiculoService.getById(id));
        return ResponseEntity.ok().body(veiculoDetailDTO);
    }
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody VeiculoDTO veiculoDTO){
        Veiculo veiculo = veiculoService.fromDTO(veiculoDTO);
        veiculo = veiculoService.insertVeiculo(veiculo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(veiculo.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateVeiculo(@RequestBody Veiculo veiculo, @PathVariable Long id){
        veiculo.setId(id);
        veiculo = veiculoService.updateVeiculo(veiculo);
        return ResponseEntity.noContent().build();

    }
    @DeleteMapping(value = "/{id}" )
    public  ResponseEntity<Void> deleteVeiculo(@PathVariable Long id){
        veiculoService.deleteVeiculo(id);
        return ResponseEntity.noContent().build();
    }

}
