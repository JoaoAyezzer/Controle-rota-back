package br.com.sgsistemas.controlerotabackend.dto;

import br.com.sgsistemas.controlerotabackend.models.Limpeza;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class LimpezaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date data;
    private Long solicitante_id;
    private String solicitante_nome;
    private String solicitante_tipo;
    private Long veiculo_id;
    private String veiculo_modelo;
    private String veiculo_placa;

    public LimpezaDTO() {
    }

    public LimpezaDTO(Limpeza limpeza) {
        this.id = limpeza.getId();
        this.data = limpeza.getData();
        this.solicitante_id = limpeza.getSolicitante().getId();
        this.solicitante_nome = limpeza.getSolicitante().getNome();
        this.solicitante_tipo = limpeza.getSolicitante().getTipoTecnico().getDescricao();
        this.veiculo_id = limpeza.getVeiculo().getId();
        this.veiculo_modelo = limpeza.getVeiculo().getModelo();
        this.veiculo_placa = limpeza.getVeiculo().getPlaca();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getSolicitante_id() {
        return solicitante_id;
    }

    public void setSolicitante_id(Long solicitante_id) {
        this.solicitante_id = solicitante_id;
    }

    public String getSolicitante_nome() {
        return solicitante_nome;
    }

    public void setSolicitante_nome(String solicitante_nome) {
        this.solicitante_nome = solicitante_nome;
    }

    public String getSolicitante_tipo() {
        return solicitante_tipo;
    }

    public void setSolicitante_tipo(String solicitante_tipo) {
        this.solicitante_tipo = solicitante_tipo;
    }

    public Long getVeiculo_id() {
        return veiculo_id;
    }

    public void setVeiculo_id(Long veiculo_id) {
        this.veiculo_id = veiculo_id;
    }

    public String getVeiculo_modelo() {
        return veiculo_modelo;
    }

    public void setVeiculo_modelo(String veiculo_modelo) {
        this.veiculo_modelo = veiculo_modelo;
    }

    public String getVeiculo_placa() {
        return veiculo_placa;
    }

    public void setVeiculo_placa(String veiculo_placa) {
        this.veiculo_placa = veiculo_placa;
    }
}
