package br.com.sgsistemas.controlerotabackend.services;

import br.com.sgsistemas.controlerotabackend.dto.VeiculoDTO;
import br.com.sgsistemas.controlerotabackend.models.Veiculo;
import br.com.sgsistemas.controlerotabackend.repositories.VeiculoRepository;
import br.com.sgsistemas.controlerotabackend.services.exceptions.DataIntegrityException;
import br.com.sgsistemas.controlerotabackend.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    VeiculoRepository veiculoRepository;

    //Listar todos os veiculos
    public List<Veiculo> getAll(){
        return veiculoRepository.findAll();
    }

    //Listar veiculos por id
    public Veiculo getById(Long id){
        Optional<Veiculo> veiculo = veiculoRepository.findById(id);
        return veiculo.orElseThrow(()-> new ObjectNotfoundException(
                "Objeto nao encontrado! Id: " + id + ", Tipo: " + Veiculo.class.getName()
        ));
    }
    //inserir Novo Veiculo
    public Veiculo insertVeiculo(Veiculo veiculo){
        veiculo.setId(null);
        return veiculoRepository.save(veiculo);
    }
    //Update dos veiculos
    public Veiculo updateVeiculo(Veiculo veiculo){
        getById(veiculo.getId());
        return veiculoRepository.save( veiculo );
    }
    //Delete Veiculo
    public void deleteVeiculo(Long id){
        getById(id);
        try {
            veiculoRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é poossivel excluir um Veiculo que possui associações!");
        }
    }
    //Converte DTO em Veiculo
    public Veiculo fromDTO(VeiculoDTO veiculoDTO){
        return new Veiculo(veiculoDTO.getPlaca(), veiculoDTO.getModelo(), veiculoDTO.getKilometragem());
    }

}
