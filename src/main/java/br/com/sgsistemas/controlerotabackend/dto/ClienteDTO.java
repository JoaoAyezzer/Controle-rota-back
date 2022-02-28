package br.com.sgsistemas.controlerotabackend.dto;

import br.com.sgsistemas.controlerotabackend.models.Cliente;

import java.io.Serializable;
import java.util.List;

public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nomeFantasia;
    private List<String> telefones;
    private String senhaDeRoot;
    private String tiLocalNome;
    private String tiLocalTelefone;
    private String tiLocalEmail;
    private String tecnicoResponsavelNome;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nomeFantasia = cliente.getNomeFantasia();
        this.telefones = cliente.getTelefones();
        this.senhaDeRoot = cliente.getSenhaDeRoot();
        if (cliente.getTiLocal() != null){
            this.tiLocalNome = cliente.getTiLocal().getNome();
            this.tiLocalTelefone = cliente.getTiLocal().getTelefone();
            this.tiLocalEmail = cliente.getTiLocal().getEmail();
        }
        this.tecnicoResponsavelNome = cliente.getTecnicoResponsavel().getNome();
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
}
