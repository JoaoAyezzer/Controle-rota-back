package br.com.sgsistemas.controlerotabackend.dto;

import br.com.sgsistemas.controlerotabackend.models.Visita;
import br.com.sgsistemas.controlerotabackend.models.enums.TipoVisita;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class VisitaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date dataInicio;
    private Long kilometragemInicial;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date dataFinal;
    private Long kilometragemFinal;
    private Long veiculoId;
    private String veiculoModelo;
    private String veiculoPlaca;
    private Long tecnicoId;
    private String tecnicoNome;
    private TipoVisita tipoVisita;

    public VisitaDTO() {
    }

    public VisitaDTO(Visita visita) {
        this.id = visita.getId();
        this.dataInicio = visita.getDataIncial();
        this.kilometragemInicial = visita.getKilometragemInicial();
        this.dataFinal = ( visita.getDataFinal() == null ) ? null : visita.getDataFinal();
        this.kilometragemFinal = ( visita.getKilometragemFinal() == null ) ? null : visita.getKilometragemFinal();
        this.veiculoId = visita.getVeiculo().getId();
        this.veiculoModelo = visita.getVeiculo().getModelo();
        this.veiculoPlaca = visita.getVeiculo().getPlaca();
        this.tecnicoId = visita.getTecnico().getId();
        this.tecnicoNome = visita.getTecnico().getNome();
        this.tipoVisita = visita.getTiṕoVisita();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTecnicoId() {
        return tecnicoId;
    }

    public void setTecnicoId(Long tecnicoId) {
        this.tecnicoId = tecnicoId;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Long getKilometragemInicial() {
        return kilometragemInicial;
    }

    public void setKilometragemInicial(Long kilometragemInicial) {
        this.kilometragemInicial = kilometragemInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Long getKilometragemFinal() {
        return kilometragemFinal;
    }

    public void setKilometragemFinal(Long kilometragemFinal) {
        this.kilometragemFinal = kilometragemFinal;
    }

    public Long getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Long veiculoId) {
        this.veiculoId = veiculoId;
    }

    public String getVeiculoModelo() {
        return veiculoModelo;
    }

    public void setVeiculoModelo(String veiculoModelo) {
        this.veiculoModelo = veiculoModelo;
    }

    public String getVeiculoPlaca() {
        return veiculoPlaca;
    }

    public void setVeiculoPlaca(String veiculoPlaca) {
        this.veiculoPlaca = veiculoPlaca;
    }

    public String getTecnico() {
        return tecnicoNome;
    }

    public void setTecnico(String tecnicoNome) {
        this.tecnicoNome = tecnicoNome;
    }

    public TipoVisita getTipoVisita() {
        return tipoVisita;
    }

    public void setTipoVisita(TipoVisita tipoVisita) {
        this.tipoVisita = tipoVisita;
    }
}
