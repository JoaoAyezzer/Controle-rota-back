package br.com.sgsistemas.controlerotabackend.dto;

import br.com.sgsistemas.controlerotabackend.models.Tecnico;
import br.com.sgsistemas.controlerotabackend.models.enums.TipoTecnico;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TecnicoNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento Obrigatório!")
    private String nome;
    @NotEmpty(message = "Preenchimento Obrigatório!")
    @Email(message = "email invalido")
    private String email;
    @NotEmpty(message = "Preenchimento Obrigatório!")
    private String senha;
    @NotNull(message = "Preenchimento Obrigatório!")
    private Integer tipoTecnico;
    private String telefone1;
    private String telefone2;

    public TecnicoNewDTO() {
    }

    public TecnicoNewDTO(Tecnico tecnico) {
        this.nome = tecnico.getNome();
        this.email = tecnico.getEmail();
        this.senha = tecnico.getSenha();
        this.tipoTecnico = (tecnico.getTipoTecnico() == null) ? null : tecnico.getTipoTecnico().getCod();
        this.telefone1 = (tecnico.getTelefones().size() < 1 || tecnico.getTelefones().get(0) == null ) ? null : tecnico.getTelefones().get(0);
        this.telefone2 = (tecnico.getTelefones().size() < 2 || tecnico.getTelefones().get(1) == null ) ? null : tecnico.getTelefones().get(1);
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
