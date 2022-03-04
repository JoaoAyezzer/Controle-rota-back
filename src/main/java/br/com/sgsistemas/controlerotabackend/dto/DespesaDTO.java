package br.com.sgsistemas.controlerotabackend.dto;

import br.com.sgsistemas.controlerotabackend.models.Despesa;
import br.com.sgsistemas.controlerotabackend.models.enums.TipoDespesa;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

public class DespesaDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;
    @NotEmpty(message = "preenchimento do tiṕo de despesa obrigatorio")
    private Integer tipoDespesa;
    @NotEmpty(message = "preenchimento do Id do tecnico Obrigatorio")
    private Long tecnicoID;
    @NotEmpty(message = "preenchimento do Id da visita Obrigatorio")
    private Long visitaID;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date data;
    @NotEmpty(message = "preenchimento do valor da despesa Obrigatorio")
    private Double valor;

    public DespesaDTO() {
    }

    public DespesaDTO(Despesa despesa) {
        this.id = despesa.getId();
        this.tipoDespesa = despesa.getTipoDespesa().getCod();
        this.tecnicoID = despesa.getTecnico().getId();
        this.visitaID = despesa.getVisita().getId();
        this.data = despesa.getData();
        this.valor = despesa.getValor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoDespesa getTipoDespesa() {
        return TipoDespesa.toEnum(tipoDespesa);
    }



        public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa.getCod();
    }

    public Long getTecnicoID() {
        return tecnicoID;
    }

    public void setTecnicoID(Long tecnicoID) {
        this.tecnicoID = tecnicoID;
    }

    public Long getVisitaID() {
        return visitaID;
    }

    public void setVisitaID(Long visitaID) {
        this.visitaID = visitaID;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
