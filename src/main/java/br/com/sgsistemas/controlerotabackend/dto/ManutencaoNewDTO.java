package br.com.sgsistemas.controlerotabackend.dto;

import br.com.sgsistemas.controlerotabackend.models.Manutencao;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class ManutencaoNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String descricao;
    @NotNull(message = "Preenchimento Obrigatorio!")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date data;
    @NotNull(message = "Preenchimento Obrigatorio!")
    private Long kilometragem;
    @NotNull(message = "Preenchimento Obrigatorio!")
    private Long solicitante;
    @NotNull(message = "Preenchimento Obrigatorio!")
    private Long veiculo;
    @NotNull(message = "Preenchimento Obrigatorio!")
    private Double valor;

    public ManutencaoNewDTO() {
    }

    public ManutencaoNewDTO(Manutencao manutencao) {
        this.descricao = manutencao.getDescricao();
        this.data = manutencao.getData();
        this.solicitante = manutencao.getSolicitante().getId();
        this.kilometragem = manutencao.getKilometragem();
        this.veiculo = manutencao.getVeiculo().getId();
        this.valor = manutencao.getValor();
    }


    public Long getKilometragem() {
        return kilometragem;
    }

    public void setKilometragem(Long kilometragem) {
        this.kilometragem = kilometragem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Long solicitante) {
        this.solicitante = solicitante;
    }

    public Long getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Long veiculo) {
        this.veiculo = veiculo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
