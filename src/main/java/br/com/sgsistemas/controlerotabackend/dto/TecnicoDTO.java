package br.com.sgsistemas.controlerotabackend.dto;

import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import br.com.sgsistemas.controlerotabackend.models.enums.TipoTecnico;
import br.com.sgsistemas.controlerotabackend.services.VisitaService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

public class TecnicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nome;
    private String email;
    private Integer tipoTecnico;
    private List<String> telefones;
    private String imageUrl;

    public TecnicoDTO() {
    }

    public TecnicoDTO(Tecnico tecnico) {
        this.id = tecnico.getId();
        this.nome = tecnico.getNome();
        this.email = tecnico.getEmail();
        this.tipoTecnico = (tecnico.getTipoTecnico() == null) ? null : tecnico.getTipoTecnico().getCod();
        this.telefones = tecnico.getTelefones();
        this.imageUrl = tecnico.getImageUrl();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public TipoTecnico getTipoTecnico() { return TipoTecnico.toEnum(tipoTecnico); }

    public void setTipoTecnico(TipoTecnico tipoTecnico) { this.tipoTecnico = tipoTecnico.getCod(); }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<String> telefones) {
        this.telefones = telefones;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
