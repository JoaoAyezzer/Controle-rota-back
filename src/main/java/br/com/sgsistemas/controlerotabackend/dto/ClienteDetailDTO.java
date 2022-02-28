package br.com.sgsistemas.controlerotabackend.dto;

import br.com.sgsistemas.controlerotabackend.models.Cliente;
import br.com.sgsistemas.controlerotabackend.services.VisitaService;

import java.io.Serializable;
import java.util.List;

public class ClienteDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nomeFantasia;
    private List<String> telefones;
    private String senhaDeRoot;
    private String tiLocalNome;
    private String tiLocalTelefone;
    private String tiLocalEmail;
    private String tecnicoResponsavelNome;
    private List<VisitaDTO> visitas;

    public ClienteDetailDTO() {
    }

    public ClienteDetailDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nomeFantasia = cliente.getNomeFantasia();
        this.senhaDeRoot = cliente.getSenhaDeRoot();
        if (cliente.getTiLocal() != null){
            this.tiLocalNome = cliente.getTiLocal().getNome();
            this.tiLocalTelefone = cliente.getTiLocal().getTelefone();
            this.tiLocalEmail = cliente.getTiLocal().getEmail();
        }
        this.telefones = cliente.getTelefones();
        this.tecnicoResponsavelNome = cliente.getTecnicoResponsavel().getNome();
        this.visitas = VisitaService.fromObject(cliente.getVisitas());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public List<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<String> telefones) {
        this.telefones = telefones;
    }

    public String getSenhaDeRoot() {
        return senhaDeRoot;
    }

    public void setSenhaDeRoot(String senhaDeRoot) {
        this.senhaDeRoot = senhaDeRoot;
    }

    public String getTiLocalNome() {
        return tiLocalNome;
    }

    public void setTiLocalNome(String tiLocalNome) {
        this.tiLocalNome = tiLocalNome;
    }

    public String getTiLocalTelefone() {
        return tiLocalTelefone;
    }

    public void setTiLocalTelefone(String tiLocalTelefone) {
        this.tiLocalTelefone = tiLocalTelefone;
    }

    public String getTiLocalEmail() {
        return tiLocalEmail;
    }

    public void setTiLocalEmail(String tiLocalEmail) {
        this.tiLocalEmail = tiLocalEmail;
    }

    public String getTecnicoResponsavelNome() {
        return tecnicoResponsavelNome;
    }

    public void setTecnicoResponsavelNome(String tecnicoResponsavelNome) {
        this.tecnicoResponsavelNome = tecnicoResponsavelNome;
    }

    public List<VisitaDTO> getVisitas() {
        return visitas;
    }

    public void setVisitas(List<VisitaDTO> visitas) {
        this.visitas = visitas;
    }
}
