package br.com.sgsistemas.controlerotabackend.dto;

import br.com.sgsistemas.controlerotabackend.models.TiCliente;

import java.io.Serializable;

public class TiClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String telefone;
    private String email;

    public TiClienteDTO() {
    }
    public TiClienteDTO(TiCliente tiCliente) {
        this.id = tiCliente.getId();
        this.nome = tiCliente.getNome();
        this.telefone = tiCliente.getTelefone();
        this.email = tiCliente.getEmail();
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
