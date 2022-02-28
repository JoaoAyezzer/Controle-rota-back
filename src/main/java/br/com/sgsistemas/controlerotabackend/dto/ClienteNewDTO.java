package br.com.sgsistemas.controlerotabackend.dto;

import br.com.sgsistemas.controlerotabackend.models.Cliente;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ClienteNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotEmpty(message = "Preenchimento Obrigatorio!")
    private String nomeFantasia;
    @NotEmpty(message = "Preenchimento Obrigatorio!")

    private String cnpj;
    private String telefone1;
    private String telefone2;
    private String senhaDeRoot;
    private Long tiLocalId;
    @NotNull(message = "Preenchimento Obrigatorio!")
    private Long tecnicoResponsavelId;

    public ClienteNewDTO() {
    }

    public ClienteNewDTO(Cliente cliente) {
        this.nomeFantasia = cliente.getNomeFantasia();
        this.cnpj = cliente.getCnpj();
        this.telefone1 = (cliente.getTelefones().size() < 1 ) ? null : cliente.getTelefones().get(0);
        this.telefone2 = (cliente.getTelefones().size() < 2 ) ? null : cliente.getTelefones().get(1);
        this.senhaDeRoot = cliente.getSenhaDeRoot();
        this.tiLocalId = cliente.getTiLocal().getId();
        this.tecnicoResponsavelId = cliente.getTecnicoResponsavel().getId();
    }


    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getSenhaDeRoot() {
        return senhaDeRoot;
    }

    public void setSenhaDeRoot(String senhaDeRoot) {
        this.senhaDeRoot = senhaDeRoot;
    }

    public Long getTiLocalId() {
        return tiLocalId;
    }

    public void setTiLocalId(Long tiLocalId) {
        this.tiLocalId = tiLocalId;
    }

    public Long getTecnicoResponsavelId() {
        return tecnicoResponsavelId;
    }

    public void setTecnicoResponsavelId(Long tecnicoResponsavelId) {
        this.tecnicoResponsavelId = tecnicoResponsavelId;
    }
}
