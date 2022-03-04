package br.com.sgsistemas.controlerotabackend.services;

import br.com.sgsistemas.controlerotabackend.dto.FinalizarVisitaDTO;
import br.com.sgsistemas.controlerotabackend.dto.VisitaDTO;
import br.com.sgsistemas.controlerotabackend.dto.VisitaNewDTO;
import br.com.sgsistemas.controlerotabackend.models.*;
import br.com.sgsistemas.controlerotabackend.models.enums.TipoTecnico;
import br.com.sgsistemas.controlerotabackend.repositories.VisitaRepository;
import br.com.sgsistemas.controlerotabackend.security.UserSpringSecurity;
import br.com.sgsistemas.controlerotabackend.services.exceptions.DataIntegrityException;
import br.com.sgsistemas.controlerotabackend.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitaService {

    @Autowired
    VisitaRepository visitaRepository;

    private final TecnicoService tecnicoService;
    private final VeiculoService veiculoService;
    private final ClienteService clienteService;

    public VisitaService(TecnicoService tecnicoService, VeiculoService veiculoService, ClienteService clienteService) {
        this.tecnicoService = tecnicoService;
        this.veiculoService = veiculoService;
        this.clienteService = clienteService;
    }

    //Listar todos as Visitas
    public List<Visita> getAll(){
        UserSpringSecurity user = UserService.authenticated();
        if (!user.hasRole(TipoTecnico.GERENTE) || !user.hasRole(TipoTecnico.SUPERVISOR)){
            return visitaRepository.findAll().stream()
                    .filter( visita -> visita.getTecnico().getId().equals(user.getId()))
                    .collect(Collectors.toList());
        }
        return visitaRepository.findAll();
    }

    //Listar Visitas por id
    public Visita getById(Long id){
        Optional<Visita> visita = visitaRepository.findById(id);
        return visita.orElseThrow(()-> new ObjectNotfoundException(
                "Objeto nao encontrado! Id: " + id + ", Tipo: " + Visita.class.getName()
        ));
    }
    //inserir Nova Visita
    public Visita insertVisita(Visita visita){
        visita.setId(null);
        Veiculo veiculo = veiculoService.getById(visita.getVeiculo().getId());
        for (Visita v: veiculo.getVisitas()) {
            if (v.getDataFinal() == null || v.getKilometragemFinal() == null){
                throw new DataIntegrityException(
                        "O veiculo " + veiculo.getModelo() +
                        " placa: " + veiculo.getPlaca() +
                        " esta bloqueado pois nao finalizou a visita " +
                        "ID: " + v.getId() + " para o cliente: " + v.getCliente().getNomeFantasia());
            }
        }
        return visitaRepository.save(visita);
    }

    //Update visita
    public Visita updateVisita(Visita visita){
        getById(visita.getId());
        return visitaRepository.save( visita );
    }
    //Delete Visita
    public void deleteVisita(Long id){
        getById(id);
        try {
            visitaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é poossivel excluir um Veiculo que possui associações!");
        }
    }

    //transforma lista de DTO para OBJ
    public static List<VisitaDTO> fromObject(List<Visita> visitas){
        List<VisitaDTO> visitaDTOS = new ArrayList<>();
        for (Visita visita : visitas) {
            visitaDTOS.add(new VisitaDTO(visita));
        }
        return visitaDTOS;
    }
    //converte VisitaNewDTO para OBJ
    public Visita fromDTO(VisitaNewDTO visitaNewDTO){
        Date dataInicial = new Date();
        Veiculo veiculo = veiculoService.getById(visitaNewDTO.getVeiculoId());
        Cliente cliente = clienteService.getById(visitaNewDTO.getClienteId());
        Tecnico tecnico = tecnicoService.getById(visitaNewDTO.getTecnicoId());
        Visita visita = new Visita(visitaNewDTO.getTipoVisita(), cliente, veiculo, tecnico, dataInicial, null, veiculo.getKilometragem(), null );
        return visita;
    }

    //Metodo para finalizar visita
    public Visita finalizaVisita(FinalizarVisitaDTO finalizarVisitaDTO, Long id){
        Date dataFinal = new Date();
        Visita visita = getById(id);
        visita.setKilometragemFinal(finalizarVisitaDTO.getKilometragemFinal());
        visita.setDataFinal(dataFinal);
        Veiculo veiculo = veiculoService.getById(visita.getVeiculo().getId());
        veiculo.setKilometragem(finalizarVisitaDTO.getKilometragemFinal());
        veiculo = veiculoService.updateVeiculo(veiculo);
        return visita;
    }
    //Busca Paginada de Visitas
    public Page<Visita> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return visitaRepository.findAll(pageRequest);
    }

}
