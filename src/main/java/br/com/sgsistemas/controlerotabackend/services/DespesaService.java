package br.com.sgsistemas.controlerotabackend.services;

import br.com.sgsistemas.controlerotabackend.dto.DespesaDTO;
import br.com.sgsistemas.controlerotabackend.models.*;
import br.com.sgsistemas.controlerotabackend.models.enums.TipoTecnico;
import br.com.sgsistemas.controlerotabackend.repositories.DespesaRepository;
import br.com.sgsistemas.controlerotabackend.security.UserSpringSecurity;
import br.com.sgsistemas.controlerotabackend.services.exceptions.AuthorizationException;
import br.com.sgsistemas.controlerotabackend.services.exceptions.DataIntegrityException;
import br.com.sgsistemas.controlerotabackend.services.exceptions.ObjectNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private VisitaService visitaService;

    @Value("${img.prefix.despesa}")
    private String prefix;

    @Value("${img.desp.size}")
    private Integer size;



    //Listar todos as Despesas
    public List<Despesa> getAll(){
        UserSpringSecurity user = UserService.authenticated();
        if (!user.hasRole(TipoTecnico.GERENTE) || !user.hasRole(TipoTecnico.SUPERVISOR)){
            return despesaRepository.findAll().stream()
                    .filter( visita -> visita.getTecnico().getId().equals(user.getId()))
                    .collect(Collectors.toList());
        }
        return despesaRepository.findAll();
    }

    //Listar despesa por id
    public Despesa getById(Long id){
        Optional<Despesa> despesa = despesaRepository.findById(id);
        return despesa.orElseThrow(()-> new ObjectNotfoundException(
                "Objeto nao encontrado! Id: " + id + ", Tipo: " + Despesa.class.getName()
        ));
    }
    //inserir Nova Despesa
    public Despesa insert(Despesa despesa){
        despesa.setId(null);
        return despesaRepository.save(despesa);
    }
    //Update despesa
    public Despesa update(Despesa despesa){
        getById(despesa.getId());
        return despesaRepository.save(despesa);
    }
    //Delete manuten????o
    public void delete(Long id){

        getById(id);
        try {
            despesaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("N??o ?? poossivel excluir um Veiculo que possui associa????es!");
        }
    }
    public Despesa fromDTO(DespesaDTO despesaDTO){
        Date data = (despesaDTO.getData() == null ) ? new Date() : despesaDTO.getData();
        try {
            Tecnico tecnico = tecnicoService.getById(despesaDTO.getTecnicoID());
            Visita visita = visitaService.getById(despesaDTO.getVisitaID());
            return new Despesa( despesaDTO.getTipoDespesa(), data, despesaDTO.getValor(), tecnico, visita );
        }catch (InvalidDataAccessApiUsageException e){
            throw new DataIntegrityException("Objeto nullo, revise os campos: tipoDespesa, tecnicoID, visitaID, valor ");
        }

    }

    public Page<Despesa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSpringSecurity user = UserService.authenticated();
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        if (user == null){
            throw  new AuthorizationException("Acesso negado! Usuario n??o autenticado");
        }
        if (!user.hasRole(TipoTecnico.GERENTE) || !user.hasRole(TipoTecnico.SUPERVISOR)){
            Tecnico tecnico = tecnicoService.getById(user.getId());
            return despesaRepository.findByTecnico(tecnico, pageRequest);
        }
        return despesaRepository.findAll(pageRequest);
    }

    public URI uploadTicketPicture(MultipartFile multipartFile, Long id){
        BufferedImage jpgImage = imageService.getJpjImageFromFile(multipartFile);
        jpgImage = imageService.resize(jpgImage, size);
        String fileName = prefix + id + ".jpg";
        URI uri = s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
        Despesa despesa = getById(id);
        despesa.setImageUrl(uri.toString());
        despesaRepository.save(despesa);
        return uri;
    }

}
