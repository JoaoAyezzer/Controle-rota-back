package br.com.sgsistemas.controlerotabackend.dto;

import br.com.sgsistemas.controlerotabackend.models.Abastecimento;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class AbastecimentoNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long veiculo_id;
    private Float litros;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date data;
    private Long kilometragem;
    private Long tecnico_id;
    private Double valor;

    public AbastecimentoNewDTO() {
    }

    public AbastecimentoNewDTO(Abastecimento abastecimento) {
        this.id = abastecimento.getId();
        this.veiculo_id = abastecimento.getVeiculo().getId();
        this.litros = abastecimento.getLitrosCombustivel();
        this.data = abastecimento.getData();
        this.kilometragem = abastecimento.getKilometragem();
        this.tecnico_id = abastecimento.getTecnico().getId();
        this.valor = abastecimento.getValor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVeiculo_id() {
        return veiculo_id;
    }

    public void setVeiculo_id(Long veiculo_id) {
        this.veiculo_id = veiculo_id;
    }

    public Float getLitros() {
        return litros;
    }

    public void setLitros(Float litros) {
        this.litros = litros;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getKilometragem() {
        return kilometragem;
    }

    public void setKilometragem(Long kilometragem) {
        this.kilometragem = kilometragem;
    }

    public Long getTecnico_id() {
        return tecnico_id;
    }

    public void setTecnico(Long tecnico) {
        this.tecnico_id = tecnico;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
